package com.dustdev.stormfarmazem.storage;

import com.dustdev.stormfarmazem.components.FactionManager;
import com.dustdev.stormfarmazem.model.Faction;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class FactionStorage {

    @Getter
    private final Map<String, Faction> factions = Maps.newLinkedHashMap();

    private final FactionManager factionManager;

    public void init() {
        factionManager.createTable();
    }

    public Faction getFaction(String factionTag) {

        Faction faction = factions.getOrDefault(factionTag, null);

        if (faction == null) {

            faction = factionManager.getFacion(factionTag);

            if(faction == null) {

                Map<String, Boolean> permissions = Maps.newLinkedHashMap();
                Map<String, Integer> blocks = Maps.newLinkedHashMap();

                permissions.put("lider", true);
                permissions.put("capitao", true);
                permissions.put("membro", false);
                permissions.put("recruta", false);

                blocks.put("BEDROCK", 0);
                blocks.put("ENDER_STONE", 0);
                blocks.put("OBSIDIAN", 0);

                faction = Faction
                        .builder()
                        .factionTag(factionTag)
                        .factionPermissions(permissions)
                        .factionBlocks(blocks)
                        .build();

                factionManager.createFaction(faction);

            }

            factions.put(factionTag, faction);

        }

        return faction;

    }

    public void purge(String factionTag) {
        Faction faction = factions.getOrDefault(factionTag, null);

        if (faction == null) return;

        factionManager.saveFaction(faction);
        factions.remove(faction.getFactionTag());
    }

}