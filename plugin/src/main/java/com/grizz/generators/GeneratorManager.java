package com.grizz.generators;

import com.grizz.EggWars;
import com.grizz.menu.MenuManager;
import com.grizz.utils.StringUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Gbtank.
 */
public class GeneratorManager {

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
        File baseFile = new File(EggWars.get().getDataFolder().getAbsolutePath() + EggWars.get().getBaseDir() + (basePath.endsWith(".yml") ? basePath : basePath + ".yml"));

        String menuPath = YamlConfiguration.loadConfiguration(baseFile).getString("menu");
        File menuFile = new File(EggWars.get().getDataFolder().getAbsolutePath() + (menuPath.endsWith(".yml") ? menuPath : menuPath + ".yml"));

        GeneratorSettings settings = new GeneratorSettings(baseFile);
        Generator gen =  new Generator(null, location, settings, settings.getUpgradeMap().get(level));
        gen.setMenu(MenuManager.get().createGeneratorMenuFromFile(menuFile, gen));
        generators.add(gen);
        return gen;
    }

    public Generator createFromFile(String genPath) {
        File genFile = new File(EggWars.get().getDataFolder().getAbsolutePath() + EggWars.get().getGenDir() + (genPath.endsWith(".yml") ? genPath : genPath + ".yml"));

        YamlConfiguration conf = YamlConfiguration.loadConfiguration(genFile);

        Location location = StringUtils.getLocationWithWorld(Bukkit.getWorld(conf.getString("generator.world")), conf.getString("generator.coords"));
        if(this.getGeneratorByLocation(location) != null) {
            return null;
        }
        int level = conf.getInt("generator.start_level");
        String basePath = conf.getString("generator.base_file");
        File baseFile = new File(EggWars.get().getDataFolder().getAbsolutePath() + EggWars.get().getBaseDir() + (basePath.endsWith(".yml") ? basePath : basePath + ".yml"));

        String menuPath = YamlConfiguration.loadConfiguration(baseFile).getString("menu");
        File menuFile = new File(EggWars.get().getDataFolder().getAbsolutePath() + (menuPath.endsWith(".yml") ? menuPath : menuPath + ".yml"));

        GeneratorSettings settings = new GeneratorSettings(baseFile);
        Generator gen =  new Generator(null, location, settings, settings.getUpgradeMap().get(level));
        gen.setMenu(MenuManager.get().createGeneratorMenuFromFile(menuFile, gen));
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

    public Optional<Generator> getGeneratorByLocation(final Location location) {
        return generators.stream().filter(generator -> generator.getLocation().equals(location)).findFirst();
    }

}
