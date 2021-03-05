package me.razorni.dev.gui.classselector;

public class ClassGUIListener {

    /*@EventHandler
    public void onInventoryInteractPage1(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(ChatColor.stripColor("Class Selector"))) {
            return;
        }
        event.setCancelled(true);
        if (event.getSlot() == -999) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(ChatColor.stripColor("Class Selector"))) {

            String material = rAbilities.getConfiguration().getConfig().getString("Class-GUI.Items.DIAMOND.Item");
            String name = rAbilities.getConfiguration().getConfig().getString("Class-GUI.Items.DIAMOND.Name");
            List<String> lore = rAbilities.getConfiguration().getConfig().getStringList("Class-GUI.Items.DIAMOND.Lore");

            ItemStack stack = new ItemBuilder(Material.valueOf(material))
                    .displayName(chatUtil.chat(name))
                    .setLore(chatUtil.list(lore))
                    .build();

            if (event.getCurrentItem().isSimilar(stack)) {


                String enchant = rAbilities.getConfiguration().getConfig().getString("Class-GUI.Items.DIAMOND.Armor.Boots.Enchants." + section + ".Enchant");
                int level = rAbilities.getConfiguration().getConfig().getInt("Class-GUI.Items.DIAMOND.Armor.Boots.Enchants." + section + ".Level");


                ItemStack boots = new ItemBuilder(Material.valueOf(rAbilities.getConfiguration().getConfig().getString("Class-GUI.Items.DIAMOND.Armor.Boots.Item")))
                        .enchant(Enchantment.getByName(enchant), level, true)
                        .build();
                player.getInventory().setBoots(boots);

                player.closeInventory();

            }

        }
    }*/

}
