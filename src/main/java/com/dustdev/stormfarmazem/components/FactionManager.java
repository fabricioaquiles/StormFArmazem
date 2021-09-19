package com.dustdev.stormfarmazem.components;

import com.dustdev.stormfarmazem.model.Faction;
import com.dustdev.stormfarmazem.model.adapter.FactionAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class FactionManager {

    private Gson gson = new GsonBuilder().create();

    private final String TABLE = "StormFArmazem";

    private final SQLExecutor sqlExecutor;


    public void createTable() {
        sqlExecutor.updateQuery("CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                "factionTag VARCHAR(3)," +
                "factionPermissions TEXT," +
                "factionBlocks TEXT" +
                ");"
        );
    }

    public void createFaction(Faction faction) {
        sqlExecutor.updateQuery(
                "INSERT INTO " + TABLE + " VALUES(?,?,?);",
                statement -> {
                    statement.set(1, faction.getFactionTag());
                }
        );
    }

    public Faction getFacion(String factionTag) {
        return sqlExecutor.resultOneQuery(
                "SELECT * FROM " + TABLE + " WHERE factionTag = ?",
                statement -> statement.set(1, factionTag),
                FactionAdapter.class

        );
    }

    public Set<Faction> getFactions() {
        return sqlExecutor.resultManyQuery(
                "SELECT * FROM " + TABLE,
                statement -> {
                },
                FactionAdapter.class
        );
    }

    public void saveFaction(Faction faction) {
        sqlExecutor.updateQuery(
                "UPDATE " + TABLE + " SET factionPermissions = ?, factionBlocks = ? WHERE factionTag = ?",
                statement -> {
                    statement.set(1, conventPermissionsToJson(faction.getFactionPermissions()));
                    statement.set(2, conventBlocksToJson(faction.getFactionBlocks()));
                    statement.set(3, faction.getFactionTag());
                }
        );
    }

    public void deleteFaction(String tag) {
        sqlExecutor.updateQuery(
                "DELETE FROM "+TABLE+" WHERE `"+TABLE+"`.`factionTag` = ?",
                statement -> {
                    statement.set(1, tag);
                }
        );
    }

    public String conventPermissionsToJson(Map<String, Boolean> upgrades) {

        return gson.toJson(upgrades);

    }

    public String conventBlocksToJson(Map<String, Integer> upgrades) {

        return gson.toJson(upgrades);

    }

}