package com.dustdev.stormfarmazem.listener.registry;

import com.dustdev.stormfarmazem.StormFArmazem;
import com.dustdev.stormfarmazem.listener.InventoryClickListener;
import com.dustdev.stormfarmazem.listener.PlayerCommandPreprocessListener;
import com.dustdev.stormfarmazem.listener.PlayerLoginListener;
import lombok.Data;
import org.bukkit.Bukkit;

@Data(staticConstructor = "of")
public class ListenerRegistry {

    private final StormFArmazem instance;

    public void register() {
        try {

            Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), instance);
            Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocessListener(), instance);
            Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), instance);

            Bukkit.getConsoleSender().sendMessage("§b[StormFArmazem] [Eventos]§f eventos carregados com sucesso.");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§b[StormFArmazem] [Eventos]§f não foi possível fazer o carregamento dos eventos.");
        }
    }

}