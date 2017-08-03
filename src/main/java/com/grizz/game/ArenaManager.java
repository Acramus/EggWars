package com.grizz.game;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Gbtank.
 */
public class ArenaManager {

    @Getter private Set<Arena> arenas = new HashSet<>();

    // Singleton Structure

    private static ArenaManager am = new ArenaManager();

    protected ArenaManager() {  }

    public static ArenaManager get() {
        return am;
    }

    public Optional<Arena> getArenaByPlayer(Player player) {
        return getArenaByUUID(player.getUniqueId());
    }

    public Optional<Arena> getArenaByUUID(UUID id) {
        return arenas.stream().filter(arena -> arena.containsUUID(id)).findFirst();
    }

}
