package com.grizz.generators;

import com.grizz.menu.Menu;
import com.grizz.menu.MenuItem;
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
