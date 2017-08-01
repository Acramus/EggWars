package com.grizz.generators;

import com.grizz.Eggwars;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Created by Gbtank.
 */
@AllArgsConstructor
public class Generator {

    @Getter private Eggwars ew;
    @Getter private Location loc;
    @Getter @Setter private ItemStack item;
    @Getter @Setter private GeneratorLevel level;

    @Getter @Setter protected int runId;

    public void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(ew, new Runnable() {
            @Override
            public void run() {

            }
        }, 0L, level.getGenCooldown());
    }

    public void upgrade() {
        // TODO: Upgrade code.
    }

}