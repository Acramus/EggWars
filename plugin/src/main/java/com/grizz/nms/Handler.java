package com.grizz.nms;

import com.grizz.merchant.MerchantWrapper;
import org.bukkit.entity.Player;

/**
 * Created by Gbtank.
 */
public interface Handler {

    void displayMerchantGUI(Player player, MerchantWrapper merchant, String name);

    void displayActionBar(Player player, String text);

    void displayTitle(Player player, String title, double fadeIn, double stay, double fadeOut);
    void displaySubTitle(Player player, String subtitle, double fadeIn, double stay, double fadeOut);

}
