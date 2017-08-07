package com.grizz.menu;

import com.grizz.generators.GeneratorMenu;
import com.grizz.utils.ItemBuilder;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gbtank.
 */
public class MenuManager {

    @Getter private Set<Menu> menus = new HashSet<>();

    // Singleton Structure

    private static MenuManager mm = new MenuManager();

    protected MenuManager() {  }

    public static MenuManager get() {
        return mm;
    }

    public GeneratorMenu createFromFile(File menuFile) {
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(menuFile);
        Map<Integer, MenuItem> slotMap = new HashMap<>();
        for(String key : conf.getConfigurationSection("gui.items").getKeys(false)) {
            ItemBuilder builder = new ItemBuilder();
            ItemStack item = builder.material(conf.getString("gui.items." + key + ".material"))
                    .amount(conf.getInt("gui.items." + key + ".amount"))
                    .displayName(conf.getString("gui.items." + key + ".display_name"))
                    .addMultipleLore(conf.getStringList("gui.items." + key + ".lore"))
                    .build();
            MenuItem menuItem;
            if(conf.getString("gui.items." + key + ".action") != null) {
                String[] actionData = conf.getString("gui.items." + key + ".action").split(" ");
                menuItem = new MenuItem(item, MenuAction.getActionByName(actionData[0]), actionData[1]);
            } else {
                menuItem = new MenuItem(item, null, "");
            }
            slotMap.put(Integer.valueOf(key), menuItem);
        }

        GeneratorMenu menu = new GeneratorMenu(ChatColor.translateAlternateColorCodes('&', conf.getString("gui.title")), conf.getInt("gui.slots"), slotMap);
        menus.add(menu);
        return menu;
    }

}
