package com.grizz.game;

import com.grizz.EggWars;
import com.grizz.merchant.MerchantWrapper;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Gbtank.
 */
public class Arena {

    @Getter private List<Location> villagers = new ArrayList<>();

    @Getter private List<Team> teams = new ArrayList<>();

    @Getter private int maxPlayers;
    @Getter private List<Player> players = new ArrayList<>();
    @Getter private List<Player> spectators = new ArrayList<>();
    @Getter private Set<MerchantWrapper> merchants = new HashSet<>();

    @Getter @Setter private long respawnTimer;

    public Arena(List<Team> teams, List<Player> players, int maxPlayers) {
        int teamMax = 0;
        for(Team team : teams) {
            teamMax += team.getMaxPlayers();
        }
        // Start throwing errors if the maximum team capacity exceeds maximum arena capacity or vice versa.
        if(teamMax == maxPlayers) {
            this.teams = teams;
            this.players = players;
            this.maxPlayers = maxPlayers;
        }
    }

    public Optional<Team> getTeamByPlayer(Player player) {
        return teams.stream().filter(team -> team.containsUUID(player.getUniqueId())).findFirst();
    }

    public boolean containsUUID(UUID id) {
        return this.players.contains(id);
    }

    public void spawnPlayers() {
        teams.forEach(Team::spawnPlayers);
    }

    public void spawnVillagers() {
        merchants.forEach(MerchantWrapper::spawnVillager);
    }

    public void start() {
    }

    /*
     * Resets arena 3 seconds after game end, non-configurable.
     */
    public void end() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(EggWars.get(), this::reset, 60L);
    }

    public void reset() {
        for(Team team : teams) {
            team.getPlayers().clear();
            team.getSpawns().clear();
        }
    }

}
