package com.grizz.menu;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * Created by Gbtank.
 */
public abstract class Menu {

    @Getter @Setter public String filePath;

    @Getter @Setter public String title;
    @Getter @Setter public int slots;

    @Getter @Setter public Inventory inventory;
    @Getter @Setter public InventoryType type;

}
