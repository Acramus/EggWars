package com.grizz.generators;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gbtank.
 */
public class GeneratorSettings {

    @Getter @Setter private String name;
    @Getter @Setter private ItemStack item;
    @Getter private Map<Integer, GeneratorLevel>  upgradeMap = new HashMap<>();

}
