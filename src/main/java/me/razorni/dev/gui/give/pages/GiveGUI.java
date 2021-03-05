package me.razorni.dev.gui.give.pages;

import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import me.razorni.dev.util.build.ItemBuilder;
import me.razorni.dev.util.chatUtil;

import java.util.ArrayList;
import java.util.List;

public class GiveGUI {

    public static void openGiveGUI(Player player, Player target) {

        Inventory drawer = Bukkit.createInventory(null, rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-1-Size"), chatUtil.chat("&6Give GUI &7(Page 1 of 2)"));

        if (rAbilities.getConfiguration().getConfig().getBoolean("Give-GUI.Items.Page-Items.Page-1.Next-Page.Enabled")) {
            for (String list3 : rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-Items.Page-1.Previous-Page.Lore")) {
                List<String> lore3 = new ArrayList<>();
                lore3.add(chatUtil.chat(list3));
                drawer.setItem(rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-Items.Page-1.Next-Page.Slot") - 1, new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-1.Next-Page.Item"))).displayName(
                        chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-1.Next-Page.Name")))
                        .setLore(lore3).build());
            }
        }
        if (rAbilities.getConfiguration().getConfig().getBoolean("Give-GUI.Items.Page-Items.Page-1.Previous-Page.Enabled")) {
            for (String list2 : rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-Items.Page-1.Previous-Page.Lore")) {
                List<String> lore2 = new ArrayList<>();
                lore2.add(chatUtil.chat(list2));
                drawer.setItem(rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-Items.Page-1.Previous-Page.Slot") - 1, new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-1.Previous-Page.Item"))).displayName(
                        chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-1.Previous-Page.Name")))
                        .setLore(lore2).build());
            }
        }

        for (String section : rAbilities.getConfiguration().getConfig().getConfigurationSection("Give-GUI.Items.Page-1").getKeys(false)) {
            List<String> lore = rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-1." + section + ".Lore");

            int data = rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-1." + section + ".Data");

            drawer.setItem(rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-1." + section + ".Slot") - 1,
                    new ItemBuilder(
                    Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-1." + section + ".Item")))
                    .displayName(chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-1." + section + ".Name")))
                    .data((short) data)
                    .setLore(chatUtil.list(lore)).build());
            rAbilities.getInstance().getPlayerMap().put(player, target);
        }
        player.openInventory(drawer);

    }

    public static void openGiveGUIPage2(Player player, Player target) {

        Inventory drawer = Bukkit.createInventory(null, rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-2-Size"), chatUtil.chat("&6Give GUI &7(Page 2 of 2)"));

        if (rAbilities.getConfiguration().getConfig().getBoolean("Give-GUI.Items.Page-Items.Page-2.Next-Page.Enabled")) {
            for (String list3 : rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-Items.Page-2.Previous-Page.Lore")) {
                List<String> lore3 = new ArrayList<>();
                lore3.add(chatUtil.chat(list3));
                drawer.setItem(rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-Items.Page-2.Next-Page.Slot") - 1, new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-1.Next-Page.Item"))).displayName(
                        chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-2.Next-Page.Name")))
                        .setLore(lore3).build());
            }
        }
        if (rAbilities.getConfiguration().getConfig().getBoolean("Give-GUI.Items.Page-Items.Page-2.Previous-Page.Enabled")) {
            for (String list2 : rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-Items.Page-2.Previous-Page.Lore")) {
                List<String> lore2 = new ArrayList<>();
                lore2.add(chatUtil.chat(list2));
                drawer.setItem(rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-Items.Page-2.Previous-Page.Slot") - 1, new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-1.Previous-Page.Item"))).displayName(
                        chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-2.Previous-Page.Name")))
                        .setLore(lore2).build());
            }
        }

        for (String section : rAbilities.getConfiguration().getConfig().getConfigurationSection("Give-GUI.Items.Page-2").getKeys(false)) {

            List<String> lore = rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-2." + section + ".Lore");

            int data = rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-2." + section + ".Data");


            drawer.setItem(rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-2." + section + ".Slot") - 1,
                    new ItemBuilder(
                            Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-2." + section + ".Item")))
                            .displayName(chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-2." + section + ".Name")))
                            .data((short) data)
                            .setLore(chatUtil.list(lore)).build());
            rAbilities.getInstance().getPlayerMap().put(player, target);
        }
        player.openInventory(drawer);

    }
}
