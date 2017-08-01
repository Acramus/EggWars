package com.grizz.generators;

import com.grizz.utils.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gbtank.
 */
public class GeneratorSettings {

    @Getter @Setter private String name;
    @Getter @Setter private ItemStack item;
    @Getter private Map<Integer, GeneratorLevel>  upgradeMap = new HashMap<>();

    public GeneratorSettings(File file) {
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);

        this.name = conf.getString("name");
        System.out.println(conf.getString("item.material"));

        ItemStack i = ItemBuilder.builder()
                .material(Material.matchMaterial(conf.getString("item.material")))
                .amount(conf.getInt("item.amount"))
                .displayName(conf.getString("item.display_name"))
                .lores(conf.getStringList("item.lores"))
                .build()
                .toItem();
        this.item = i;

        for(String level : conf.getConfigurationSection("upgrades").getKeys(false)) {
            upgradeMap.put(Integer.valueOf(level),
                    new GeneratorLevel(Integer.valueOf(level),
                            conf.getInt("upgrades." + level + ".max_drops"),
                            conf.getLong("upgrades." + level + ".ticks")));
        }
    }

}
