package com.dustdev.stormfarmazem.integration.plugin;

import com.atlasplugins.atlasfactionsv2.apiv2.AtlasFactionsAPI;
import com.atlasplugins.atlasfactionsv2.enums.FactionRole;
import com.atlasplugins.atlasfactionsv2.events.faction.AtlasFactionCreateEvent;
import com.atlasplugins.atlasfactionsv2.events.faction.AtlasFactionDeleteEvent;
import com.atlasplugins.atlasfactionsv2.objects.AtlasFaction;
import com.atlasplugins.atlasfactionsv2.objects.AtlasFactionUser;
import com.dustdev.stormfarmazem.StormFArmazem;
import com.dustdev.stormfarmazem.integration.Integration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;
import java.util.function.Consumer;

public class AtlasFactionsV2 implements Integration {

    @Override
    public String getTag(Player p) {

        AtlasFactionUser atlasFactionUser = AtlasFactionsAPI.getFactionsHandler().getAtlasUser(p).get();
        Optional<AtlasFaction> optional = atlasFactionUser.getFaction();

        if(!optional.isPresent()) return "";

        AtlasFaction faction = optional.get();

        return faction.getTag();
    }

    @Override
    public Boolean hasFaction(Player p) {
        Optional<AtlasFactionUser> optional = AtlasFactionsAPI.getFactionsHandler().getAtlasUser(p);

        if(!optional.isPresent()) return false;

        AtlasFactionUser user = optional.get();
        return user.hasFaction();
    }

    @Override
    public String getRole(Player p) {
        AtlasFactionUser atlasFactionUser = AtlasFactionsAPI.getFactionsHandler().getAtlasUser(p).get();
        return translateRole(atlasFactionUser.getFactionRole());
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
            public void onCreate(AtlasFactionCreateEvent e) {

                AtlasFaction faction = e.getFaction();
                StormFArmazem.getInstance().getFactionStorage().getFaction(faction.getTag());

            }
        };
    }

    @Override
    public Listener onFactionDisband() {
        return new Listener() {

            @EventHandler
            public void onDisband(AtlasFactionDeleteEvent e) {

                AtlasFaction faction = e.getFaction();
                StormFArmazem.getInstance().getFactionStorage().getFactions().remove(faction.getTag());
                StormFArmazem.getInstance().getFactionManager().deleteFaction(faction.getTag());

            }
        };
    }

    public String translateRole(FactionRole factionRole) {
        switch (factionRole) {
            case LEADER: {
                return "lider";
            }
            case CAPTAIN: {
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