package me.razorni.dev.gui.pocketbard;

import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import me.razorni.dev.util.build.ItemBuilder;
import me.razorni.dev.util.chatUtil;

import java.util.ArrayList;
import java.util.List;

public class PocketBardGUI {

    public static void openAbilityGUI(Player player) {
        Inventory drawer = Bukkit.createInventory(null, rAbilities.getConfiguration().getConfig().getInt("PocketBard.Size"), chatUtil.chat("&b&lPocketBard"));

        for (String section : rAbilities.getConfiguration().getConfig().getConfigurationSection("PocketBard.Items").getKeys(false)) {
            for (String list : rAbilities.getConfiguration().getConfig().getStringList("PocketBard.Items." + section + ".Lore")) {
                List<String> lore = new ArrayList<>();
                lore.add(chatUtil.chat(list));
                drawer.setItem(rAbilities.getConfiguration().getConfig().getInt("PocketBard.Items." + section + ".Slot"),
                        new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("PocketBard.Items." + section + ".Item"))).displayName(
                                chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("PocketBard.Items."  + section + ".Name")))
                                .setLore(lore).build());
            }
        }
        player.openInventory(drawer);
    }
    
}
