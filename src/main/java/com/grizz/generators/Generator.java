package com.grizz.generators;

import com.grizz.EggWars;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

import java.util.Collection;

/**
 * Created by Gbtank.
 */
public class Generator {

    private EggWars ew;
    @Getter private Location location;
    @Getter private GeneratorSettings settings;
    @Getter @Setter private GeneratorLevel level;

    @Getter @Setter protected int runId;

    public Generator(EggWars ew, Location location, GeneratorSettings settings, GeneratorLevel level) {
        this.ew = ew;
        this.location = location;
        this.settings = settings;
        this.level = level;
    }

    public boolean tryStart() {
        if(level.getLevel() > 0) {
            start();
            return true;
        }
        return false;
    }

    /*
     * Start runs whenever a generated is upgraded from broken state to lvl 1 or when a map is loaded.
     * TODO: Run start.
     * TODO: Fix visual bugs by using teleport and smaller dropItem height.
     */
    private void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(ew, new Runnable() {
            @Override
            public void run() {
                /*
                 * Check if items on generator have exceeded the maximum
                 */
                Collection<Entity> nearby = location.getWorld().getNearbyEntities(location.clone().add(0.5, 0.5, 0.5), 0.5, 0.5, 0.5);
                for(Entity entity : nearby) {
                    if(entity instanceof Item) {
                        Item i = (Item) entity;
                        if(i.getItemStack().getType().equals(settings.getItem().getType())) {
                            if(i.getItemStack().getAmount() >= level.getMaxDrops()) {
                                i.getItemStack().setAmount(i.getItemStack().getAmount() - 1);
                            }
                        }
                    }
                }

                location.getWorld().dropItem(location.clone().add(0.5, 0.5, 0.5), settings.getItem());
            }
        }, 0L, level.getGenCooldown());
    }

    public void upgrade() {
        // TODO: Start upgrade code.
    }

}