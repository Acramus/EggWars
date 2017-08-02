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
                copyResource("example_arena.yml", this.getDataFolder().getAbsolutePath() + "/arenas/example_arena.yml");
                copyResource("example_base.yml", this.getDataFolder().getAbsolutePath() + "/base/example_base.yml");
                copyResource("example_gen.yml", this.getDataFolder().getAbsolutePath() + "/generators/example_gen.yml");
                copyResource("sign.yml", this.getDataFolder().getAbsolutePath() + "/example_sign.yml");
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

    public void copyResource(String originPath, String finalPath) throws IOException {
        File finalFile = new File(getDataFolder() + finalPath);
        if(!finalFile.exists()) {
            InputStream finalIn = null;
            FileOutputStream finalOut = null;

            try {
                finalFile.createNewFile();
                finalIn = getResource(originPath);
                finalOut = new FileOutputStream(finalFile);

                int c;
                while ((c = finalIn.read()) != -1) finalOut.write(c);

            } finally {
                if (finalIn != null) finalIn.close();
                if(finalOut != null) finalOut.close();
            }
        }
    }

}
