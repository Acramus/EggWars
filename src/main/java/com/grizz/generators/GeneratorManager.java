package com.grizz.generators;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
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

    public Generator createFromFile(File file, Location location) {
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
        int level = conf.getInt("level");
        File base = new File(conf.getString("base_file"));
        YamlConfiguration baseConf = YamlConfiguration.loadConfiguration(base);
        return new Generator(location, new GeneratorSettings(base), new GeneratorLevel(level,
                baseConf.getInt("upgrades." + level + ".max_drops"),
                baseConf.getLong("upgrades." + level + ".ticks")));
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
