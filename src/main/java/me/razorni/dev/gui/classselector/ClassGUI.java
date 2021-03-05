package me.razorni.dev.gui.classselector;

import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import me.razorni.dev.util.build.ItemBuilder;
import me.razorni.dev.util.chatUtil;

import java.util.List;

public class ClassGUI {

    public static void openClassGUI(Player player) {

        Inventory i;

        int size = rAbilities.getConfiguration().getConfig().getInt("Class-GUI.Size");

        i = Bukkit.createInventory(null, size, chatUtil.chat("&e&lClass Selector"));

        if (rAbilities.getConfiguration().getConfig().getBoolean("Class-GUI.Items.DIAMOND.Enabled")) {

            int slot = rAbilities.getConfiguration().getConfig().getInt("Class-GUI.Items.DIAMOND.Slot");
            String material = rAbilities.getConfiguration().getConfig().getString("Class-GUI.Items.DIAMOND.Item");
            String name = rAbilities.getConfiguration().getConfig().getString("Class-GUI.Items.DIAMOND.Name");
            List<String> lore = rAbilities.getConfiguration().getConfig().getStringList("Class-GUI.Items.DIAMOND.Lore");

            ItemStack stack = new ItemBuilder(Material.valueOf(material))
                    .displayName(chatUtil.chat(name))
                    .setLore(chatUtil.list(lore))
                    .build();

            i.setItem(slot, stack);
        }

        player.openInventory(i);

    }

}
