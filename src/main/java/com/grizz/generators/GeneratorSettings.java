package com.grizz.generators;

import com.grizz.utils.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
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

        ItemBuilder dropBuilder = new ItemBuilder();
        ItemStack i = dropBuilder.material(conf.getString("item.material"))
                .amount(conf.getInt("item.amount"))
                .displayName(conf.getString("item.display_name"))
                .addMultipleLore(conf.getStringList("item.lore"))
                .build();
        this.item = i;

        for(String level : conf.getConfigurationSection("upgrades").getKeys(false)) {
            if(conf.get("upgrades." + level + ".upgrade_item") == null || conf.get("upgrades." + (level + 1)) == null) {
                upgradeMap.put(Integer.valueOf(level),
                        new GeneratorLevel(null,
                                Integer.valueOf(level),
                                conf.getInt("upgrades." + level + ".max_drops"),
                                conf.getLong("upgrades." + level + ".ticks")));
                continue;
            }
            ItemBuilder upgradeBuilder = new ItemBuilder();
            upgradeMap.put(Integer.valueOf(level),
                    new GeneratorLevel(upgradeBuilder.material(conf.getString("upgrades" + level + ".upgrade_item.material"))
                            .amount(conf.getInt("upgrades" + level + ".upgrade_item.amount"))
                            .displayName(conf.getString("upgrades" + level + ".upgrade_item.display_name"))
                            .addMultipleLore(conf.getStringList("upgrades" + level + ".upgrade_item.lore"))
                            .build(),
                            Integer.valueOf(level),
                            conf.getInt("upgrades." + level + ".max_drops"),
                            conf.getLong("upgrades." + level + ".ticks")));
        }
    }

}
