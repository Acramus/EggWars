package com.grizz.generators;

import com.grizz.EggWars;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;

import java.util.Collection;

/**
 * Created by Gbtank.
 */
public class Generator {

    private EggWars ew;
    @Getter private Location location;
    @Getter private GeneratorSettings settings;
    @Getter @Setter private GeneratorLevel genLevel;

    @Getter @Setter protected int runId;

    public Generator(EggWars ew, Location location, GeneratorSettings settings, GeneratorLevel genLevel) {
        this.ew = ew;
        this.location = location;
        this.settings = settings;
        this.genLevel = genLevel;
    }

    public boolean tryStart() {
        if(genLevel.getLevel() > 0) {
            start();
            return true;
        }
        return false;
    }

    /*
     * Start runs whenever a generated is upgraded from broken state to lvl 1 or when a map is loaded.
     * TODO: Run start.
     * TODO: Change height values to make sure the "Generator" is defined under the location where items drop!!
     */
    private void start() {
        this.runId = Bukkit.getScheduler().scheduleSyncRepeatingTask(ew, new Runnable() {
            @Override
            public void run() {
                /*
                 * Check if items on generator have exceeded the maximum
                 */
                Collection<Entity> nearby = location.getWorld().getNearbyEntities(location.clone().add(0.5, 0.25, 0.5), 0.25, 0.25, 0.25);
                for(Entity entity : nearby) {
                    if(entity instanceof Item) {
                        Item itemEnt = (Item) entity;
                        if(itemEnt.getItemStack().getType().equals(settings.getItem().getType())) {
                            if(itemEnt.getItemStack().getAmount() >= genLevel.getMaxDrops()) {
                                itemEnt.getItemStack().setAmount(itemEnt.getItemStack().getAmount() - 1);
                            }
                        }
                    }
                }

                Item drop = location.getWorld().dropItemNaturally(location.clone().add(0.5, 0.25, 0.5), settings.getItem());
                // Low velocity stops items from falling off the edge.
                drop.setVelocity(new Vector(0, 0.25, 0));
            }
        }, 0L, genLevel.getGenCooldown());
    }

    // TODO: Start upgrade code.
    public void upgrade() {
        // Check if the current level is the highest level by making sure the next level does not appear
        if(!settings.getUpgradeMap().containsKey(genLevel.getLevel() + 1)) return;

        Bukkit.getScheduler().cancelTask(runId);
        // TODO: Write more code!
    }

}