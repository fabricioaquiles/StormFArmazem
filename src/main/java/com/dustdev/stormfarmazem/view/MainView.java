package com.dustdev.stormfarmazem.view;

import com.dustdev.stormfarmazem.StormFArmazem;
import com.dustdev.stormfarmazem.integration.Integration;
import com.dustdev.stormfarmazem.utils.ItemBuilder;
import com.dustdev.stormfarmazem.utils.misc.config.values.MenusValue;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainView {
    
    public static void openInventory(
            Player p
    ) {
        Integration integration =  StormFArmazem.getInstance().getIntegration();

        Inventory inv = Bukkit.createInventory(null, 3*9, "["+integration.getTag(p)+"] Armazem");

        inv.setItem(11, new ItemBuilder(Material.BEDROCK)
                .setName(MenusValue.get(MenusValue::armazemName))
                .setLore(MenusValue.get(MenusValue::armazemLore))
                .toItemStack());

        inv.setItem(15, new ItemBuilder(Material.REDSTONE_COMPARATOR)
                .setName(MenusValue.get(MenusValue::permName))
                .setLore(MenusValue.get(MenusValue::permLore))
                .toItemStack());

        p.openInventory(inv);
    }
    
}