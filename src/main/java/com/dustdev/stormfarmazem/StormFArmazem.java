package com.dustdev.stormfarmazem;

import com.dustdev.stormfarmazem.components.FactionManager;
import com.dustdev.stormfarmazem.integration.Integration;
import com.dustdev.stormfarmazem.integration.IntegrationEngine;
import com.dustdev.stormfarmazem.listener.registry.ListenerRegistry;
import com.dustdev.stormfarmazem.storage.FactionStorage;
import com.dustdev.stormfarmazem.utils.misc.config.registry.ConfigurationRegistry;
import com.dustdev.stormfarmazem.utils.misc.sql.SQLProvider;
import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class StormFArmazem extends JavaPlugin {

    @Getter private static StormFArmazem instance;

    private Integration integration;

    private FactionManager factionManager;
    private FactionStorage factionStorage;

    private SQLConnector sqlConnector;
    public SQLExecutor sqlExecutor;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        ConfigurationRegistry.of(instance).register();

        sqlConnector = SQLProvider.of(instance).setup();
        sqlExecutor = new SQLExecutor(sqlConnector);

        factionManager = new FactionManager(sqlExecutor);
        factionStorage = new FactionStorage(factionManager);
        factionStorage.init();

        new IntegrationEngine().init();

        ListenerRegistry.of(instance).register();

        Bukkit.getConsoleSender().sendMessage("§b[StormFArmazem]§f plugin iniciado com sucesso!");
    }

}