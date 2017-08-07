package com.grizz.utils;

import com.grizz.generators.Generator;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;

import java.io.File;

/**
 * Created by Gbtank.
 */
public class GuiUtils {

    public static Inventory createGeneratorGuiFromFile(Generator generator, File file, String path) {
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
        Inventory inv = Bukkit.createInventory(null, conf.getInt(path + ".slots"));
        for(String string : conf.getConfigurationSection(path + ".items").getKeys(false)) {
            int slot = Integer.valueOf(string);

        }
        return null;
    }

}
