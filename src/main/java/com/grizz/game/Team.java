package com.grizz.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Gbtank.
 */
public class Team {

    @Getter private String name;
    @Getter private ChatColor colour;

    @Getter private boolean cages;
    @Getter private int maxPlayers;
    @Getter private List<Player> players = new ArrayList<>();
    @Getter private List<Location> spawns = new ArrayList<>();

    @Getter @Setter private boolean egg;
    @Getter private Location eggLocation;

    public void tryJoin(Player player) {
        if(players.size() < maxPlayers) {
            players.add(player);
        }
    }

    private void join(Player player) {
        this.players.add(player);
    }

    private void leave(Player player) {
        this.players.remove(player);
    }

    public boolean containsUUID(UUID id) {
        return this.players.contains(id);
    }

    public void spawnPlayers() {
        if(players.isEmpty()) return;
        // Start throwing errors if there are no spawns.
        if(spawns.size() == 1) {
            players.forEach(player -> player.teleport(spawns.get(0)));
        } else if(spawns.size() > 1) {
            for(int index = 0; index < players.size(); index++) {

            }
        }
    }
}
