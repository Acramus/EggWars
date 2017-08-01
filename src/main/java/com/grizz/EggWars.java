package com.grizz;

import com.grizz.generators.Generator;
import com.grizz.generators.GeneratorManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.File;

/**
 * Created by Gbtank.
 */
public class EggWars extends JavaPlugin implements PluginMessageListener {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("test") && player.hasPermission("eggwars.test")) {
            Generator gen = GeneratorManager.get().createFromFile(new File(getDataFolder().getAbsolutePath() + "example_gen.yml"));
            gen.getLocation().clone().add(0, -1, 0).getBlock().setType(Material.SANDSTONE);
            gen.tryStart();
        }
        return false;
    }

    public void onEnable() {
        saveDefaultConfig();

        // Activate managers
        GeneratorManager gm = new GeneratorManager(this);

        File base = new File(getDataFolder().getAbsolutePath() + "base");
        if(!base.exists()) base.mkdir();
        File gens = new File(getDataFolder().getAbsolutePath() + "generators");
        if(!gens.exists()) gens.mkdir();
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        // TODO: Configure BungeeCord
    }

}
