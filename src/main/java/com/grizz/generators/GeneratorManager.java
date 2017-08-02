package com.grizz.generators;

import com.grizz.EggWars;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gbtank.
 */
public class GeneratorManager {

    private EggWars ew;
    @Getter private Set<Generator> generators = new HashSet<>();

    // Singleton Structure

    private static GeneratorManager gm = new GeneratorManager();

    protected GeneratorManager() {  }

    public static GeneratorManager get() {
        return gm;
    }

    public Generator createFromFile(File file) {
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);

        Location location = new Location(
                Bukkit.getWorld(conf.getString("generator.world")),
                conf.getInt("generator.x"),
                conf.getInt("generator.y"),
                conf.getInt("generator.z"));
        int level = conf.getInt("generator.start_level");
        String basePath = conf.getString("generator.base_file");
        File base = new File(EggWars.get().getDataFolder().getAbsolutePath() + "/" + (basePath.endsWith(".yml") ? basePath : basePath + ".yml"));

        GeneratorSettings settings = new GeneratorSettings(base);
        Generator gen =  new Generator(location, settings, settings.getUpgradeMap().get(level));
        generators.add(gen);
        return gen;
    }

    public GeneratorSign createSign() {
        // TODO: Write code.
        return null;
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
