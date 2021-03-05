package me.razorni.dev.gui.give.pages;

import me.razorni.dev.gui.give.convo.Coversation;
import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import me.razorni.dev.util.build.ItemBuilder;
import me.razorni.dev.util.chatUtil;

import java.util.ArrayList;
import java.util.List;

public class GiveGUIListener implements Listener {
    @EventHandler
    public void onInventoryInteractPage1(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(ChatColor.stripColor("Give GUI (Page 1 of 2)"))) {
            return;
        }
        event.setCancelled(true);
        if (event.getSlot() == -999) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        Player p = rAbilities.getInstance().getPlayerMap().get(player);
        if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(ChatColor.stripColor("Give GUI (Page 1 of 2)"))) {
            for (String section : rAbilities.getConfiguration().getConfig().getConfigurationSection("Give-GUI.Items.Page-1").getKeys(false)) {
                for (String list : rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-1." + section + ".Lore")) {
                    List<String> lore = new ArrayList<>();
                    lore.add(chatUtil.chat(list));
                    for (String list2 : rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-Items.Page-1.Next-Page.Lore")) {
                        List<String> lore2 = new ArrayList<>();
                        lore2.add(chatUtil.chat(list2));
                        for (String list3 : rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-Items.Page-1.Previous-Page.Lore")) {
                            List<String> lore3 = new ArrayList<>();
                            lore3.add(chatUtil.chat(list3));
                            if (event.getCurrentItem().isSimilar(new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-1.Next-Page.Item"))).displayName(
                                    chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-1.Next-Page.Name")))
                                    .setLore(lore2).build())) {
                                player.closeInventory();
                                rAbilities.getInstance().getPlayerMap().remove(p);
                                rAbilities.getInstance().getPlayerMap().remove(player);
                                GiveGUI.openGiveGUIPage2(player, p);
                                return;
                            }
                            if (event.getCurrentItem().isSimilar(new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-1.Previous-Page.Item"))).displayName(
                                    chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-1.Previous-Page.Name")))
                                    .setLore(lore3).build())) {
                                player.closeInventory();
                                rAbilities.getInstance().getPlayerMap().remove(p);
                                rAbilities.getInstance().getPlayerMap().remove(player);
                                player.sendMessage(chatUtil.chat("&cYou are already on Page 1."));
                                return;
                            }
                            if (event.getCurrentItem().isSimilar(new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-1." + section + ".Item"))).displayName(
                                    chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-1." + section + ".Name")))
                                    .data((short) rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-1." + section + ".Data"))

                                    .setLore(lore)
                                    .build())) {
                                String command = rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-1." + section + ".Command");
                                player.closeInventory();
                                Coversation.conversationDouble(player, chatUtil.chat("&eEnter an amount to give " + p.getName() + "."), (d) -> {
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("%player%", p.getName()).replaceAll("%amount%", String.valueOf(d.intValue())));
                                    player.sendMessage(chatUtil.chat("&bYou have given &3" + p.getName() + " &bx" + d.intValue() + " of " + rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-1." + section + ".Name")));
                                    rAbilities.getInstance().getPlayerMap().remove(player);
                                    rAbilities.getInstance().getPlayerMap().remove(p);
                                });
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryInteractPage2(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(ChatColor.stripColor("Give GUI (Page 2 of 2)"))) {
            return;
        }
        event.setCancelled(true);
        if (event.getSlot() == -999) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        Player p = rAbilities.getInstance().getPlayerMap().get(player);
        if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(ChatColor.stripColor("Give GUI (Page 2 of 2)"))) {
            for (String section : rAbilities.getConfiguration().getConfig().getConfigurationSection("Give-GUI.Items.Page-2").getKeys(false)) {
                for (String list : rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-2." + section + ".Lore")) {
                    List<String> lore = new ArrayList<>();
                    lore.add(chatUtil.chat(list));
                    for (String list2 : rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-Items.Page-2.Next-Page.Lore")) {
                        List<String> lore2 = new ArrayList<>();
                        lore2.add(chatUtil.chat(list2));
                        for (String list3 : rAbilities.getConfiguration().getConfig().getStringList("Give-GUI.Items.Page-Items.Page-2.Previous-Page.Lore")) {
                            List<String> lore3 = new ArrayList<>();
                            lore3.add(chatUtil.chat(list3));
                            if (event.getCurrentItem().isSimilar(new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-2.Next-Page.Item"))).displayName(
                                    chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-2.Next-Page.Name")))
                                    .setLore(lore2).build())) {
                                player.closeInventory();
                                rAbilities.getInstance().getPlayerMap().remove(p);
                                rAbilities.getInstance().getPlayerMap().remove(player);
                                player.sendMessage(chatUtil.chat("&cYou are already on Page 2."));
                                return;
                            }
                            if (event.getCurrentItem().isSimilar(new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-2.Previous-Page.Item"))).displayName(
                                    chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-Items.Page-2.Previous-Page.Name")))
                                    .setLore(lore3).build())) {
                                player.closeInventory();
                                rAbilities.getInstance().getPlayerMap().remove(p);
                                rAbilities.getInstance().getPlayerMap().remove(player);
                                GiveGUI.openGiveGUI(player, p);
                                return;
                            }
                            if (event.getCurrentItem().isSimilar(new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-2." + section + ".Item"))).displayName(
                                    chatUtil.chat(rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-2." + section + ".Name")))
                                    .data((short) rAbilities.getConfiguration().getConfig().getInt("Give-GUI.Items.Page-2." + section + ".Data"))
                                    .setLore(lore)
                                    .build())) {
                                String command = rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-2." + section + ".Command");
                                player.closeInventory();
                                Coversation.conversationDouble(player, chatUtil.chat("&eEnter an amount to give " + p.getName() + "."), (d) -> {
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("%player%", p.getName()).replaceAll("%amount%", String.valueOf(d.intValue())));
                                    player.sendMessage(chatUtil.chat("&bYou have given &3" + p.getName() + " &bx" + d.intValue() + " of " + rAbilities.getConfiguration().getConfig().getString("Give-GUI.Items.Page-2." + section + ".Name")));
                                    rAbilities.getInstance().getPlayerMap().remove(player);
                                    rAbilities.getInstance().getPlayerMap().remove(p);
                                });
                            }
                        }
                    }
                }
            }
        }
    }
}

