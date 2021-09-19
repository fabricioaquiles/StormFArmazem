package com.dustdev.stormfarmazem.listener;

import com.dustdev.stormfarmazem.StormFArmazem;
import com.dustdev.stormfarmazem.utils.misc.config.values.MensagensValue;
import com.dustdev.stormfarmazem.view.MainView;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent e) {

        String cmd = e.getMessage();
        Player p = e.getPlayer();

        if(cmd.equalsIgnoreCase("/f armazem")) {

            e.setCancelled(true);

            if(!StormFArmazem.getInstance().getIntegration().hasFaction(p)) {
                p.sendMessage(MensagensValue.get(MensagensValue::semFaccao));
                return;
            }

            MainView.openInventory(p);
        }
    }
}