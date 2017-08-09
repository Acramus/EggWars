package com.grizz.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gbtank.
 */
public class ItemBuilder {

    // TODO: No colour displaynames or lore. Fix Builder, use no Lombok.

    private String displayName;
    private Material material;
    private int amount;
    private int durability;
    private List<String> loreList = new ArrayList<>();

    public ItemBuilder displayName(String displayName) {
        this.displayName = ChatColor.translateAlternateColorCodes('&', displayName);
        return this;
    }

    public ItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder material(String material) {
        this.material = Material.matchMaterial(material);
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder addLore(String lore) {
        this.loreList.add(ChatColor.translateAlternateColorCodes('&', lore));
        return this;
    }

    public ItemBuilder addMultipleLore(String... lore) {
        for(String l : lore) {
            this.loreList.add(ChatColor.translateAlternateColorCodes('&', l));
        }
        return this;
    }

    public ItemBuilder addMultipleLore(List<String> lore) {
        for(String l : lore) {
            this.loreList.add(ChatColor.translateAlternateColorCodes('&', l));
        }
        return this;
    }

    public ItemBuilder durability(int durability) {
        this.durability = durability;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(loreList);
        item.setItemMeta(meta);
        item.setDurability((short) durability);
        return item;
    }

}
