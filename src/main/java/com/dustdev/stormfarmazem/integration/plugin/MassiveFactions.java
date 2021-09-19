package com.dustdev.stormfarmazem.integration.plugin;

import com.dustdev.stormfarmazem.StormFArmazem;
import com.dustdev.stormfarmazem.integration.Integration;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.event.EventFactionsCreate;
import com.massivecraft.factions.event.EventFactionsDisband;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.function.Consumer;

public class MassiveFactions implements Integration {

    @Override
    public String getTag(Player p) {
        MPlayer mp = MPlayer.get(p);
        return mp.getFactionTag();
    }

    @Override
    public Boolean hasFaction(Player p) {
        MPlayer mp = MPlayer.get(p);
        return mp.hasFaction();
    }

    @Override
    public String getRole(Player p) {
        MPlayer mp = MPlayer.get(p);
        return translateRole(mp.getRole());
    }

    @Override
    public Consumer<PlayerLoginEvent> onLogin() {
        return (e -> {

            Player p = e.getPlayer();
            if(hasFaction(p)) {
                StormFArmazem.getInstance().getFactionStorage().getFaction(getTag(p));
            }

        });
    }

    @Override
    public Consumer<PlayerQuitEvent> onQuit() {
        return (e -> {

            Player p = e.getPlayer();
            if(hasFaction(p)) {
                StormFArmazem.getInstance().getFactionStorage().purge(getTag(p));
            }

        });
    }

    @Override
    public Listener onFactionCreate() {
        return new Listener() {

            @EventHandler
            public void onCreate(EventFactionsCreate e) {

                StormFArmazem.getInstance().getFactionStorage().getFaction(e.getFactionTag());

            }
        };
    }

    @Override
    public Listener onFactionDisband() {
        return new Listener() {

            @EventHandler
            public void onDisband(EventFactionsDisband e) {

                Faction faction = e.getFaction();
                StormFArmazem.getInstance().getFactionStorage().getFactions().remove(faction.getTag());
                StormFArmazem.getInstance().getFactionManager().deleteFaction(faction.getTag());

            }
        };
    }

    public String translateRole(Rel rel) {
        switch (rel) {
            case LEADER: {
                return "lider";
            }
            case OFFICER: {
                return "capitao";
            }
            case MEMBER: {
                return "membro";
            }
            case RECRUIT: {
                return "recruta";
            }
            default: {
                return null;
            }
        }
    }
}