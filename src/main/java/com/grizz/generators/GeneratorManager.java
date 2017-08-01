package com.grizz.generators;

import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gbtank.
 */
public class GeneratorManager {

    private Set<Generator> generators = new HashSet<>();

    // Singleton Structure

    private static GeneratorManager gm = new GeneratorManager();

    protected GeneratorManager() {}

    public static GeneratorManager get() {
        return gm;
    }

    public Generator getGeneratorByLocation(Location location) {
        for(Generator gen : generators) {
            if(gen.getLocation().equals(location)) {
                return gen;
            }
        }
        return null;
    }

}
