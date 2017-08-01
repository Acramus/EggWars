package com.grizz.utils;

import lombok.Builder;
import lombok.Singular;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by Gbtank.
 */
@Builder
public class ItemBuilder {

    private String displayName;
    private Material material;
    private int amount;
    @Singular private List<String> lores;

    public ItemStack toItem() {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

}
