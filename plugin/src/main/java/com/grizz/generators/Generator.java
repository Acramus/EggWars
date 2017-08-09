package com.grizz.generators;

import com.grizz.EggWars;
import com.grizz.game.ArenaManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Collection;

/**
 * Created by Gbtank.
 */
public class Generator {

    @Getter private Location location;
    @Getter private GeneratorSettings settings;

    @Getter @Setter private GeneratorMenu menu;
    @Getter @Setter private GeneratorData data;

    @Getter @Setter protected int runId;

    public Generator(GeneratorMenu menu, Location location, GeneratorSettings settings, GeneratorData data) {
        this.menu = menu;
        this.location = location;
        this.settings = settings;
        this.data = data;
    }

    public boolean tryStart() {
        if(data.getLevel() > 0) {
            menu.update(this);
            start();
            return true;
        }
        return false;
    }

    /*
     * Start runs whenever a generated is upgraded from broken state to lvl 1 or when a map is loaded.
     * TODO: Run start.
     * TODO: Change height values to make sure the "Generator" is defined under the location where items drop!!
     */
    private void start() {
        this.runId = Bukkit.getScheduler().scheduleSyncRepeatingTask(EggWars.get(), new Runnable() {
            @Override
            public void run() {
                /*
                 * Check if items on generator have exceeded the maximum
                 */
                Collection<Entity> nearby = location.getWorld().getNearbyEntities(location.clone().add(0.5, 0.25, 0.5), 0.25, 0.25, 0.25);
                for(Entity entity : nearby) {
                    if(entity instanceof Item) {
                        Item itemEnt = (Item) entity;
                        if(itemEnt.getItemStack().getType().equals(settings.getItem().getType())) {
                            if(itemEnt.getItemStack().getAmount() >= data.getMaxDrops()) {
                                itemEnt.getItemStack().setAmount(itemEnt.getItemStack().getAmount() - 1);
                            }
                        }
                    }
                }

                Item drop = location.getWorld().dropItemNaturally(location.clone().add(0.5, 0.25, 0.5), settings.getItem());
                // Low velocity stops items from falling off the edge.
                drop.setVelocity(new Vector(0, 0.25, 0));
            }
        }, 0L, data.getInterval());
    }

    public void openMenu(Player player) {
        player.openInventory(menu.getInventory());
    }

    public void upgrade(final Player player) {
        // Check if the current level is the highest level by making sure the next level does not appear
        if(!settings.getUpgradeMap().containsKey(data.getLevel() + 1)) return;

        if(player.getInventory().contains(settings.getUpgradeMap().get(data.getLevel() + 1).getUpgradeItem())) {
            player.getInventory().remove(settings.getUpgradeMap().get(data.getLevel() + 1).getUpgradeItem());
            Bukkit.getScheduler().cancelTask(runId);
            ArenaManager.get().getArenaByPlayer(player).ifPresent(a -> a.getTeamByPlayer(player).ifPresent(t -> t.getPlayers().forEach(p -> p.sendMessage(ChatColor.translateAlternateColorCodes('&', EggWars.get().getPrefix() + EggWars.get().getMessenger().getString("gen_upgrade")
                    .replace("{PLAYER}", player.getName())
                    .replace("{GEN_NAME}", settings.getName())
                    .replace("{GEN_LEVEL}", data.getLevel() + ""))
                    .replace("{NEXT_LEVEL}", data.getLevel() + 1 + "")))));
            this.data = settings.getUpgradeMap().get(data.getLevel() + 1);
            // TODO: Add firework code with firework colour variable.
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', EggWars.get().getPrefix() + EggWars.get().getMessenger().getString("no_enough_items")));
        }
    }

}