package com.grizz.nms.api;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Gbtank.
 */
public interface Handler {

    void displayMerchantGUI(Player player, List<MerchantTrade> trades, String name);

    void displayActionBar(Player player, String text);

    void displayTitle(Player player, String title, double fadeIn, double stay, double fadeOut);
    void displaySubTitle(Player player, String subtitle, double fadeIn, double stay, double fadeOut);

}
