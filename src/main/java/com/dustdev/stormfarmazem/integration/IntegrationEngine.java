package com.dustdev.stormfarmazem.integration;

import com.dustdev.stormfarmazem.StormFArmazem;
import com.dustdev.stormfarmazem.integration.plugin.AtlasFactionsV2;
import com.dustdev.stormfarmazem.integration.plugin.MassiveFactions;
import com.dustdev.stormfarmazem.utils.misc.config.values.GeneralValue;
import org.bukkit.Bukkit;

public class IntegrationEngine {

    public void init() {

        IntegrationType type = IntegrationType.valueOf(GeneralValue.get(GeneralValue::factiontype).toUpperCase());

        switch (type) {
            case ATLASFACTIONSV2: {
                StormFArmazem.getInstance().setIntegration(new AtlasFactionsV2());
                Bukkit.getPluginManager().registerEvents(StormFArmazem.getInstance().getIntegration().onFactionCreate(), StormFArmazem.getInstance());
                Bukkit.getPluginManager().registerEvents(StormFArmazem.getInstance().getIntegration().onFactionDisband(), StormFArmazem.getInstance());
                Bukkit.getConsoleSender().sendMessage("§b[StormFArmazem] [Hook] §fAtlasFactionsV2 selecionado, hook realizado com sucesso.");
            }
            case MASSIVEFACTIONS: {
                StormFArmazem.getInstance().setIntegration(new MassiveFactions());
                Bukkit.getPluginManager().registerEvents(StormFArmazem.getInstance().getIntegration().onFactionCreate(), StormFArmazem.getInstance());
                Bukkit.getPluginManager().registerEvents(StormFArmazem.getInstance().getIntegration().onFactionDisband(), StormFArmazem.getInstance());
                Bukkit.getConsoleSender().sendMessage("§b[StormFArmazem] [Hook] §fMassiveFactions selecionado, hook realizado com sucesso.");
            }
        }

    }

}