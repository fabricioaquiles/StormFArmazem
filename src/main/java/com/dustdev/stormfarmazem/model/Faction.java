package com.dustdev.stormfarmazem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter @Builder
public class Faction {

    private String factionTag;
    private Map<String, Boolean> factionPermissions;
    private Map<String, Integer> factionBlocks;

    public Integer getBlock(String block) {
        return getFactionBlocks().get(block);
    }

    public void setBlock(String block, Integer amount) {
        getFactionBlocks().replace(block, amount);
    }

    public Boolean hasPerm(String role) {
        return getFactionPermissions().get(role);
    }

    public void setPerm(String role, Boolean is) {
        getFactionPermissions().replace(role, is);
    }

}