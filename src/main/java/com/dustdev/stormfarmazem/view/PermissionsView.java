package com.dustdev.stormfarmazem.view;

import com.dustdev.stormfarmazem.StormFArmazem;
import com.dustdev.stormfarmazem.integration.Integration;
import com.dustdev.stormfarmazem.model.Faction;
import com.dustdev.stormfarmazem.storage.FactionStorage;
import com.dustdev.stormfarmazem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PermissionsView {

    public static void openInventory(
            Player p
    ) {
        Integration integration = StormFArmazem.getInstance().getIntegration();
        Inventory inv = Bukkit.createInventory(null, 4*9, "["+integration.getTag(p)+"] Armazem | Permissões");
        FactionStorage factionStorage = StormFArmazem.getInstance().getFactionStorage();
        Faction faction = factionStorage.getFaction(integration.getTag(p));

        inv.setItem(12, new ItemBuilder(Material.DIAMOND_SWORD).setName("§eCapitão").toItemStack());
        inv.setItem(13, new ItemBuilder(Material.IRON_SWORD).setName("§eMembro").toItemStack());
        inv.setItem(14, new ItemBuilder(Material.WOOD_SWORD).setName("§eRecruta").toItemStack());

        inv.setItem(21, insertItem(faction.hasPerm("capitao")));
        inv.setItem(22, insertItem(faction.hasPerm("membro")));
        inv.setItem(23, insertItem(faction.hasPerm("recruta")));

        inv.setItem(27, new ItemBuilder(Material.ARROW).setName("§aVoltar").toItemStack());

        p.openInventory(inv);

    }

    public static ItemStack insertItem(Boolean value) {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        if(value) {
            item.setDurability((short) 5);
            meta.setDisplayName("§aLiberado");
        } else {
            item.setDurability((short) 14);
            meta.setDisplayName("§cNão liberado");
        }
        item.setItemMeta(meta);
        return item;

    }
}