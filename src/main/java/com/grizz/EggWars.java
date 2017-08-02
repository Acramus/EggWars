package com.grizz;

import com.grizz.generators.Generator;
import com.grizz.generators.GeneratorManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Gbtank.
 */
public class EggWars extends JavaPlugin implements PluginMessageListener {

    // Singleton Structure

    private static EggWars ew = new EggWars();

    protected EggWars() {}

    public static EggWars get() {
        return ew;
    }


    // TODO: Remove test code when I get back to work

    // TODO: Move commands into neater packages
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("test") && player.hasPermission("eggwars.test")) {
            System.out.println(getDataFolder().getAbsolutePath());
            Generator gen = GeneratorManager.get().createFromFile("example_gen.yml");
            gen.getLocation().clone().add(0, -1, 0).getBlock().setType(Material.SANDSTONE);
            gen.tryStart();
        }
        return false;
    }

    // TODO: Generate arena example!
    public void onEnable() {
        saveDefaultConfig();

        File arenas = new File(getDataFolder().getAbsolutePath() + "/arenas/");
        File base = new File(getDataFolder().getAbsolutePath() + "/base/");
        File gens = new File(getDataFolder().getAbsolutePath() + "/generators/");

        if(!arenas.exists() && !base.exists() && !gens.exists()) {
            try {
                generateExamples();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!arenas.exists()) arenas.mkdir();
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


    public void generateExamples() throws IOException {
        File base = new File(getDataFolder() + "/base/" + "example_base.yml");
        if(!base.exists()) {
            InputStream baseIn = null;
            FileOutputStream baseOut = null;

            try {
                base.createNewFile();
                baseIn = getResource("example_base.yml");
                baseOut = new FileOutputStream(base);

                int c;
                while ((c = baseIn.read()) != -1) baseOut.write(c);

            } finally {
                if (baseIn != null) baseIn.close();
                if(baseOut != null) baseOut.close();
            }
        }

        File gen = new File(getDataFolder() + "/generators/" + "example_gen.yml");
        if(!gen.exists()) {
            InputStream genIn = null;
            FileOutputStream genOut = null;

            try {
                gen.createNewFile();
                genIn = getResource("example_gen.yml");
                genOut = new FileOutputStream(gen);

                int c;
                while ((c = genIn.read()) != -1) genOut.write(c);

            } finally {
                if (genIn != null) genIn.close();
                if(genOut != null) genOut.close();
            }
        }
    }

}
