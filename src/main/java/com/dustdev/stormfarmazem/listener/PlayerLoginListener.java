package com.dustdev.stormfarmazem.listener;

import com.dustdev.stormfarmazem.StormFArmazem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {

        StormFArmazem.getInstance().getIntegration().onLogin().accept(e);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        StormFArmazem.getInstance().getIntegration().onQuit().accept(e);

    }
}