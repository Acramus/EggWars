package com.grizz;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * Created by Gbtank.
 */
public class Eggwars extends JavaPlugin implements PluginMessageListener {

    public void onEnable() {
        saveDefaultConfig();
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

    }
}
