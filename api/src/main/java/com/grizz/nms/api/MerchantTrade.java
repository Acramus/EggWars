package com.grizz.nms.api;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Gbtank.
 */
public class MerchantTrade {

    @Getter private ItemStack first;
    @Getter private ItemStack second;
    @Getter private ItemStack result;

}
