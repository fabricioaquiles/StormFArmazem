package com.dustdev.stormfarmazem.listener;

import com.dustdev.stormfarmazem.StormFArmazem;
import com.dustdev.stormfarmazem.integration.Integration;
import com.dustdev.stormfarmazem.model.Faction;
import com.dustdev.stormfarmazem.storage.FactionStorage;
import com.dustdev.stormfarmazem.utils.misc.config.values.MensagensValue;
import com.dustdev.stormfarmazem.view.MainView;
import com.dustdev.stormfarmazem.view.PermissionsView;
import com.dustdev.stormfarmazem.view.StorageView;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        Integration integration = StormFArmazem.getInstance().getIntegration();

        if(e.getInventory().getName().equalsIgnoreCase("["+integration.getTag(p)+"] Armazem")) {
            e.setCancelled(true);

            switch (e.getRawSlot()) {
                case 11:
                    StorageView.openInventory(p);
                    break;
                case 15:
                    PermissionsView.openInventory(p);
                    break;
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase("["+integration.getTag(p)+"] Armazem | Permissões")) {
            e.setCancelled(true);

            FactionStorage factionStorage = StormFArmazem.getInstance().getFactionStorage();
            Faction faction = factionStorage.getFaction(integration.getTag(p));
            List<Integer> slots = Arrays.asList(21,22,23);

            if(slots.contains(e.getSlot())) {

                String role = (e.getSlot() == 21 ? "capitao" : e.getSlot() == 22 ? "membro" : "recruta");

                if(faction.hasPerm(role)) {
                    faction.setPerm(role, false);
                } else {
                    faction.setPerm(role, true);
                }
                PermissionsView.openInventory(p);
            }

            if(e.getSlot() == 27) {
                MainView.openInventory(p);
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase("["+integration.getTag(p)+"] Armazem | Blocos")) {
            e.setCancelled(true);

            FactionStorage factionStorage = StormFArmazem.getInstance().getFactionStorage();
            Faction faction = factionStorage.getFaction(integration.getTag(p));

            List<Integer> slots = Arrays.asList(12,13,14);

            if(!faction.hasPerm(integration.getRole(p))) {
                p.sendMessage(MensagensValue.get(MensagensValue::semPermissao));
                return;
            }

            if(e.getSlot() == 27) {
                MainView.openInventory(p);
            }

            if(e.getSlot() == 31) {
                int bedrock = 0, endstone = 0, obsidian = 0;
                for (ItemStack item : p.getInventory().getContents()) {
                    if (item == null) continue;
                    if (item.getType() == Material.BEDROCK) {
                        bedrock += item.getAmount();
                        item.setType(Material.AIR);
                    }
                    if (item.getType() == Material.ENDER_STONE) {
                        endstone += item.getAmount();
                        item.setType(Material.AIR);
                    }
                    if (item.getType() == Material.OBSIDIAN) {
                        obsidian += item.getAmount();
                        item.setType(Material.AIR);
                    }
                }

                p.getInventory().remove(Material.BEDROCK);
                p.getInventory().remove(Material.ENDER_STONE);
                p.getInventory().remove(Material.OBSIDIAN);

                faction.setBlock("BEDROCK", faction.getBlock("BEDROCK")+bedrock);
                faction.setBlock("ENDER_STONE", faction.getBlock("ENDER_STONE")+endstone);
                faction.setBlock("OBSIDIAN", faction.getBlock("OBSIDIAN")+obsidian);

                StorageView.openInventory(p);
                return;
            }

            if(slots.contains(e.getSlot())) {

                Material material = (e.getSlot() == 12 ? Material.BEDROCK : e.getSlot() == 13 ? Material.ENDER_STONE : Material.OBSIDIAN);
                int amount = (e.getClick().isRightClick() ? 2301 : e.getClick().isLeftClick() ? 64 : 0);

                if(amount == 0) return;

                if (!(faction.getBlock(material.name().toUpperCase()) >= amount)) {
                    p.sendMessage(MensagensValue.get(MensagensValue::semBlocos));
                    return;
                }

                faction.setBlock(material.name().toUpperCase(), faction.getBlock(material.name().toUpperCase())-amount);
                p.getInventory().addItem(new ItemStack(material, amount));
                StorageView.openInventory(p);
            }
        }
    }
}