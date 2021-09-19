package com.dustdev.stormfarmazem.utils.misc.config.registry;

import com.dustdev.stormfarmazem.StormFArmazem;
import com.dustdev.stormfarmazem.utils.misc.config.values.GeneralValue;
import com.dustdev.stormfarmazem.utils.misc.config.values.MensagensValue;
import com.dustdev.stormfarmazem.utils.misc.config.values.MenusValue;
import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import lombok.Data;

@Data(staticConstructor = "of")
public class ConfigurationRegistry {

    private final StormFArmazem plugin;

    public void register() {

        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(plugin);

        configurationInjector.saveDefaultConfiguration(plugin,
                "mensagens.yml"
        );

        configurationInjector.saveDefaultConfiguration(plugin,
                "menus.yml"
        );

        configurationInjector.injectConfiguration(
                GeneralValue.instance(),
                MensagensValue.instance(),
                MenusValue.instance()
        );

    }

}