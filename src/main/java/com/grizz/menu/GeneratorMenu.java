package com.grizz.menu;

import com.grizz.generators.Generator;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gbtank.
 */
public class GeneratorMenu extends Menu {

    @Getter private Generator generator;
    @Getter private Map<Integer, MenuItem> slotMap = new HashMap<>();

    public GeneratorMenu(String title, int slots, Map<Integer, MenuItem> slotMap) {
        this.type = InventoryType.CHEST;
        this.title = title;
        this.slots = slots;
        this.slotMap = slotMap;
    }

    public void update(Generator generator) {
        this.generator = generator;
        this.inventory = Bukkit.createInventory(null, slots);
    }

}
