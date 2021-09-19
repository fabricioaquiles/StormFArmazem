package com.dustdev.stormfarmazem.integration;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.function.Consumer;

public interface Integration {

    String getTag(Player p);
    Boolean hasFaction(Player p);
    String getRole(Player p);

    Consumer<PlayerLoginEvent> onLogin();
    Consumer<PlayerQuitEvent> onQuit();

    Listener onFactionCreate();
    Listener onFactionDisband();

}
