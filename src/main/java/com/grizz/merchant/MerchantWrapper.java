package com.grizz.merchant;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gbtank.
 */
public class MerchantWrapper {

    @Getter private Location location;
    @Getter private Villager villager;

    @Getter private ShopMenu shop;
    @Getter private Map<String, MerchantMenu> menuMap = new HashMap<>();

    public MerchantWrapper(Location location, ShopMenu shop) {
        this.location = location;
        this.shop = shop;
    }

    // TODO: Run on game start.
    public void spawnVillager() {
        if(villager == null) villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
    }

}
