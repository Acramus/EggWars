package com.grizz.generators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Gbtank.
 */
@AllArgsConstructor
public class GeneratorLevel {

    @Getter @Setter private int level;
    @Getter @Setter private int maxDrops;

    /*
     * Note to self:
     * 20L is 1 second
     * 10L is 0.5 second
     * 5L is 0.25 second
     * 1L is 0.05 second
     */
    @Getter @Setter private long genCooldown;

}
