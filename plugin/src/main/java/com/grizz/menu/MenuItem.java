package com.grizz.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Gbtank.
 */
@AllArgsConstructor
public class MenuItem {

    @Getter private ItemStack item;
    @Getter private MenuAction action;
    @Getter private String actionData;

    public boolean hasAction() {
        return (action != null);
    }

}
