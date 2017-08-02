package com.grizz;

import com.google.common.base.Charsets;
import com.grizz.generators.Generator;
import com.grizz.generators.GeneratorManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Gbtank.
 */
public class EggWars extends JavaPlugin implements PluginMessageListener {

    // TODO: Remove test code when I get back to work

    // TODO: Move commands into neater packages
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("test") && player.hasPermission("eggwars.test")) {
            System.out.println(getDataFolder().getAbsolutePath());
            Generator gen = GeneratorManager.get().createFromFile(new File(getDataFolder().getAbsolutePath() + "/example_gen.yml"));
            gen.getLocation().clone().add(0, -1, 0).getBlock().setType(Material.SANDSTONE);
            gen.tryStart();
        }
        return false;
    }

    public void onEnable() {
        saveDefaultConfig();

        // Activate managers
        GeneratorManager gm = new GeneratorManager(this);

        File base = new File(getDataFolder().getAbsolutePath() + "/base/");
        File gens = new File(getDataFolder().getAbsolutePath() + "/generators/");

        if(!base.exists() && !gens.exists()) {
            generateExamples();
        }

        if(!base.exists()) base.mkdir();
        if(!gens.exists()) gens.mkdir();

    }

    public void onDisable() {
        for(Generator gen : GeneratorManager.get().getGenerators()) {
            Bukkit.getScheduler().cancelTask(gen.getRunId());
        }
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        // TODO: Configure BungeeCord
    }


    // TODO: Read file from OutputStream
    public void generateExamples() {
        File base = new File(getDataFolder() + "/" + "example_base.yml");
        if(!base.exists()) {
            InputStream exampleBaseStream = getResource("example_base.yml");
            YamlConfiguration exampleBase = YamlConfiguration.loadConfiguration(new InputStreamReader(exampleBaseStream, Charsets.UTF_8));
            try {
                exampleBase.save(base);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File gen = new File(getDataFolder() + "/" + "example_gen.yml");
        if(!gen.exists()) {
            InputStream exampleGenStream = getResource("example_gen.yml");
            YamlConfiguration exampleGen = YamlConfiguration.loadConfiguration(new InputStreamReader(exampleGenStream, Charsets.UTF_8));
            try {
                exampleGen.save(getDataFolder() + "/" + exampleGen.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
