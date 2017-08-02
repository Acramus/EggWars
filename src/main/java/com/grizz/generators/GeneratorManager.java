package com.grizz.generators;

import com.grizz.EggWars;
import com.grizz.utils.StringUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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

    public Generator createFromPath(Location location, String basePath, int level) {
        if(this.getGeneratorByLocation(location) != null) {
            return null;
        }
        File baseFile = new File(EggWars.get().getDataFolder().getAbsolutePath() + "/base/" + (basePath.endsWith(".yml") ? basePath : basePath + ".yml"));

        GeneratorSettings settings = new GeneratorSettings(baseFile);
        Generator gen =  new Generator(location, settings, settings.getUpgradeMap().get(level));
        generators.add(gen);
        return gen;
    }

    public Generator createFromFile(String genPath) {
        File genFile = new File(EggWars.get().getDataFolder().getAbsolutePath() + "/generators/" + (genPath.endsWith(".yml") ? genPath : genPath + ".yml"));

        YamlConfiguration conf = YamlConfiguration.loadConfiguration(genFile);

        Location location = new Location(
                Bukkit.getWorld(conf.getString("generator.world")),
                conf.getInt("generator.x"),
                conf.getInt("generator.y"),
                conf.getInt("generator.z"));
        if(this.getGeneratorByLocation(location) != null) {
            return null;
        }
        int level = conf.getInt("generator.start_level");
        String basePath = conf.getString("generator.base_file");
        File baseFile = new File(EggWars.get().getDataFolder().getAbsolutePath() + "/base/" + (basePath.endsWith(".yml") ? basePath : basePath + ".yml"));

        GeneratorSettings settings = new GeneratorSettings(baseFile);
        Generator gen =  new Generator(location, settings, settings.getUpgradeMap().get(level));
        generators.add(gen);
        return gen;
    }

    public Generator createFromString(String string) {
        if(string.contains(";")) {
            String[] mainSplit = string.split(";");
            String coords = mainSplit[0];
            String genSettings = mainSplit[1];
            String[] genInfo = genSettings.split(":");
            return createFromPath(StringUtils.getLocationFromString(coords), genInfo[0], Integer.valueOf(genInfo[1]));
        }
        return null;
    }

    public Generator createFromStringWithWorld(World world, String string) {
        if(string.contains(";")) {
            String[] mainSplit = string.split(";");
            String coords = mainSplit[0];
            String genSettings = mainSplit[1];
            String[] genInfo = genSettings.split(":");
            return createFromPath(StringUtils.getLocationWithWorld(world, coords), genInfo[0], Integer.valueOf(genInfo[1]));
        }
        return null;
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
