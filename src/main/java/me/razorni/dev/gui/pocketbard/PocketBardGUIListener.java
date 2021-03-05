package me.razorni.dev.gui.pocketbard;

import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.build.ItemBuilder;
import me.razorni.dev.util.chatUtil;

import java.util.List;

public class PocketBardGUIListener implements Listener {
    @EventHandler
    public void onInventoryInteractPage1(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(ChatColor.stripColor("PocketBard"))) {
            return;
        }
        event.setCancelled(true);
        if (event.getSlot() == -999) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(ChatColor.stripColor("PocketBard"))) {
            for (String section : rAbilities.getConfiguration().getConfig().getConfigurationSection("PocketBard.Items").getKeys(false)) {
                    List<String> lore = rAbilities.getConfiguration().getConfig().getStringList("PocketBard.Items." + section + ".Lore");
                    if (event.getCurrentItem().isSimilar(new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("PocketBard.Items." + section + ".Item"))).displayName(
                            chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("PocketBard.Items." + section + ".Name")))
                            .setLore(chatUtil.list(lore))
                            .build())) {

                        //Cooldowns.getPocketbard().applyCooldown(player, rAbilities.config.getConfig().getInt("PocketBard.PocketBard.Cooldown") * 1000);
                        AbilityEvents.takeItem(player, player.getItemInHand());
                        String command = rAbilities.getConfiguration().getConfig().getString("PocketBard.Items." + section + ".Command");
                        player.closeInventory();
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("%player%", player.getName()));
                }
            }
        }
    }
}
