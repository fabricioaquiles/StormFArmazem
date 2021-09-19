package com.dustdev.stormfarmazem.model.adapter;

import com.dustdev.stormfarmazem.model.Faction;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.henryfabio.sqlprovider.executor.adapter.SQLResultAdapter;
import com.henryfabio.sqlprovider.executor.result.SimpleResultSet;

import java.lang.reflect.Type;
import java.util.Map;

public class FactionAdapter implements SQLResultAdapter<Faction> {

    private Gson gson = new GsonBuilder().create();

    @Override
    public Faction adaptResult(SimpleResultSet resultSet) {

        String factionTag = resultSet.get("factionTag");
        String factionPermissions = resultSet.get("factionPermissions");
        String factionBlocks = resultSet.get("factionBlocks");

        return Faction
                .builder()
                .factionTag(factionTag)
                .factionPermissions(convertPermissionsFromJson(factionPermissions))
                .factionBlocks(convertBlocksFromJson(factionBlocks))
                .build();
    }

    public Map<String, Boolean> convertPermissionsFromJson(String data) {

        final Type typeOfHashmap = new TypeToken<Map<String, Boolean>>(){}.getType();

        return gson.fromJson(data, typeOfHashmap);

    }

    public Map<String, Integer> convertBlocksFromJson(String data) {

        final Type typeOfHashmap = new TypeToken<Map<String, Integer>>(){}.getType();

        return gson.fromJson(data, typeOfHashmap);

    }
}