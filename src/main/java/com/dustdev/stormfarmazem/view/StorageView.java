package com.dustdev.stormfarmazem.view;

import com.dustdev.stormfarmazem.StormFArmazem;
import com.dustdev.stormfarmazem.integration.Integration;
import com.dustdev.stormfarmazem.model.Faction;
import com.dustdev.stormfarmazem.storage.FactionStorage;
import com.dustdev.stormfarmazem.utils.Formatter;
import com.dustdev.stormfarmazem.utils.ItemBuilder;
import com.dustdev.stormfarmazem.utils.misc.config.values.MenusValue;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class StorageView {
    
    
    public static void openInventory(
            Player p
    ) {
        Integration integration = StormFArmazem.getInstance().getIntegration();
        Inventory inv = Bukkit.createInventory(null, 4*9, "["+integration.getTag(p)+"] Armazem | Blocos");
        FactionStorage factionStorage = StormFArmazem.getInstance().getFactionStorage();
        Faction faction = factionStorage.getFaction(integration.getTag(p));

        inv.setItem(12, new ItemBuilder(faction.hasPerm(integration.getRole(p)) ? Material.BEDROCK : Material.BARRIER)
                .setName(MenusValue.get(MenusValue::bedrockName))
                .setLore(getLore(MenusValue.get(MenusValue::bedrockLore), faction.getBlock("BEDROCK"))).toItemStack());

        inv.setItem(13, new ItemBuilder(faction.hasPerm(integration.getRole(p)) ? Material.ENDER_STONE : Material.BARRIER)
                .setName(MenusValue.get(MenusValue::endstoneName))
                .setLore(getLore(MenusValue.get(MenusValue::endstoneLore), faction.getBlock("ENDER_STONE"))).toItemStack());

        inv.setItem(14, new ItemBuilder(faction.hasPerm(integration.getRole(p)) ? Material.OBSIDIAN : Material.BARRIER)
                .setName(MenusValue.get(MenusValue::obsidianName))
                .setLore(getLore(MenusValue.get(MenusValue::obsidianLore), faction.getBlock("OBSIDIAN"))).toItemStack());

        inv.setItem(31, new ItemBuilder(Material.CHEST)
                .setName(MenusValue.get(MenusValue::depositarName))
                .setLore(MenusValue.get(MenusValue::depositarLore)).toItemStack());

        inv.setItem(27, new ItemBuilder(Material.ARROW)
                .setName("§aVoltar").toItemStack());

        p.openInventory(inv);
        
    }

    public static List<String> getLore(List<String> value, Integer blocos) {
        List<String> lore = new ArrayList<>();
        for(String key : value) {
            lore.add(key.replace("%estoque%", Formatter.formatPunctuating(blocos)));
        }
        return lore;
    }
}
