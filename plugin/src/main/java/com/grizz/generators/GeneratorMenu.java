package com.grizz.generators;

import com.grizz.menu.Menu;
import com.grizz.menu.MenuItem;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gbtank.
 */
public class GeneratorMenu extends Menu {

    @Getter @Setter private Generator generator;
    @Getter @Setter private Map<Integer, MenuItem> slotMap = new HashMap<>();

    public GeneratorMenu(String fileName, String title, int slots) {
        this.fileName = fileName;
        this.type = InventoryType.CHEST;
        this.section = "gui";

        this.title = title;
        this.slots = slots;
    }

    public GeneratorMenu(String fileName, String title, int slots, Map<Integer, MenuItem> slotMap) {
        this.fileName = fileName;
        this.type = InventoryType.CHEST;
        this.section = "gui";

        this.title = title;
        this.slots = slots;
        this.slotMap = slotMap;
    }

    public void update(Generator generator) {
        this.generator = generator;
        this.inventory = Bukkit.createInventory(null, slots);
    }

}
