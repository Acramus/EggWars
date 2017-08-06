package com.grizz.merchant;

import org.bukkit.Location;
import org.bukkit.entity.Villager;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gbtank.
 */
public class MerchantWrapper {

    private Location location;
    private Villager v;

    private ShopMenu shop;
    private Set<MerchantMenu> menuSet = new HashSet<>();

}
