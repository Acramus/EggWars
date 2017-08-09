package com.grizz.menu;

import com.grizz.generators.GeneratorMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Gbtank.
 */
@AllArgsConstructor
public class MenuItem {

    @Getter private Menu menu;

    @Getter private ItemStack item;
    @Getter private MenuAction action;
    @Getter private String actionData;

    public boolean hasAction() {
        return (action != null);
    }

    public void runAction(Player player) {
        switch(action) {
            case CLOSE:
                player.closeInventory();
            case OPEN:
                player.openInventory(MenuManager.get()
                        .getMenuByNameAndSection(actionData.substring(0, actionData.lastIndexOf("/")),
                                actionData.substring(actionData.lastIndexOf("/") + 1)).getInventory());
            case UPGRADE:
                if(menu instanceof GeneratorMenu) ((GeneratorMenu) menu).getGenerator().upgrade(player);
        }
    }

}
