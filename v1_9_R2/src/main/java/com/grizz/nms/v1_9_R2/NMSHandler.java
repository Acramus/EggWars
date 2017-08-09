package com.grizz.nms.v1_9_R2;

import com.grizz.merchant.MerchantTrade;
import com.grizz.nms.api.Handler;
import net.minecraft.server.v1_9_R2.*;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Gbtank.
 */
public class NMSHandler implements Handler {

    public void displayMerchantGUI(Player player, List<MerchantTrade> trades, String name) {
        EntityVillager villager = new EntityVillager(((CraftWorld) player.getWorld()).getHandle());
        EntityHuman trader = ((CraftPlayer) player).getHandle();

        // Clear Villager Recipe List
        MerchantRecipeList recipeList = villager.getOffers(trader);
        recipeList.clear();

        for(MerchantTrade trade : trades) {
            // Params: CraftItemStack slot1, CraftItemStack slot2, CraftItemStack result, int uses, int maxUses
            MerchantRecipe recipe = new MerchantRecipe(CraftItemStack.asNMSCopy(trade.getFirst()), CraftItemStack.asNMSCopy(trade.getSecond()), CraftItemStack.asNMSCopy(trade.getResult()), 0, 9999);
            recipe.rewardExp = false;
            recipeList.add(recipe);
        }

        // craftVillager.setRecipes(recipeList);
        // craftVillager.setRiches(0);

        // Villager opens trade with player
        villager.setTradingPlayer(trader);
        trader.openTrade(villager);
        // Add player Statistic for talking to villagers
        trader.b(StatisticList.H);
    }

    public void displayActionBar(Player player, String text) {
        String msg = ChatColor.translateAlternateColorCodes('&', text.replace("_", " "));
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ppoc);
    }

    public void displayTitle(Player player, String title, double fadeIn, double stay, double fadeOut) {
        String msg = ChatColor.translateAlternateColorCodes('&', title.replace("_", " "));
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, cbc);
        PacketPlayOutTitle timePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, (int) Math.round(fadeIn * 20), (int) Math.round(stay * 20), (int) Math.round(fadeOut * 20));

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(timePacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlePacket);
    }

    public void displaySubTitle(Player player, String subtitle, double fadeIn, double stay, double fadeOut) {
        String msg = ChatColor.translateAlternateColorCodes('&', subtitle.replace("_", " "));
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutTitle subTitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, cbc);
        PacketPlayOutTitle timePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, (int) Math.round(fadeIn * 20), (int) Math.round(stay * 20), (int) Math.round(fadeOut * 20));

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(timePacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subTitlePacket);
    }

}
