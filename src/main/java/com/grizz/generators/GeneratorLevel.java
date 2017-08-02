package com.grizz.generators;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Gbtank.
 */
public class GeneratorLevel {

    @Getter @Setter private ItemStack upgradeItem;

    @Getter @Setter private int level;
    @Getter @Setter private int maxDrops;

    /*
     * Note to self:
     * 20L is 1 second
     * 10L is 0.5 second
     * 5L is 0.25 second
     * 1L is 0.05 second
     *
     */
    @Getter @Setter private long genCooldown;

    public GeneratorLevel(ItemStack upgradeItem, int level, int maxDrops, long genCooldown) {
        this.upgradeItem = upgradeItem;
        this.level = level;
        this.maxDrops = maxDrops;
        this.genCooldown = genCooldown;
    }

}
