package me.razorni.dev.items.refund;

import me.razorni.dev.util.build.ItemBuilder;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.ArrayList;
import java.util.List;

public class Refund implements Listener {

    @EventHandler
    public void onRottenEgg(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        List<String> lore = new ArrayList<>();
        for (String list : rAbilities.config.getConfig().getStringList("RottenEgg.Lore")) {
            lore.add(chatUtil.chat(list));
        }
        ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("RottenEgg.Item")), 1).displayName(
                chatUtil.chat(rAbilities.config.getConfig().getString("RottenEgg.Name")))
                .setLore(lore).build();

        if (projectile.getShooter() instanceof Skeleton)
            return;
        if (projectile.getShooter() instanceof Blaze)
            return;

        Player player = (Player) projectile.getShooter();

        if (!rAbilities.getEggRefunds().containsKey(player))
            return;
        if (event.getEntity() == null)
            return;

        if (!(projectile.hasMetadata("rottenegg")))
            return;
        if (rAbilities.config.getConfig().getBoolean("Refund.Enabled")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    rAbilities.getEggRefunds().remove(player);
                    Cooldowns.getRefundcool().applyCooldown(player, rAbilities.config.getConfig().getInt("Refund.Cooldown") * 1000);
                    Cooldowns.getAbilitycd().removeCooldown(player);
                    Cooldowns.getRottenegg().removeCooldown(player);
                    player.getInventory().addItem(stack);
                    for (String list : rAbilities.config.getConfig().getStringList("Refund.Message.Refunded")) {
                        player.sendMessage(chatUtil.chat(list));
                    }
                }
            }.runTaskLater(rAbilities.getInstance(), 20);
        }
    }

    @EventHandler
    public void onMixerHit(ProjectileHitEvent event) {

        Projectile projectile = event.getEntity();

        List<String> lore = new ArrayList<>();
        for (String list : rAbilities.config.getConfig().getStringList("Mixer.Lore")) {
            lore.add(chatUtil.chat(list));
        }
        ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("Mixer.Item")), 1).displayName(
                chatUtil.chat(rAbilities.config.getConfig().getString("Mixer.Name")))
                .setLore(lore).build();

        if (projectile.getShooter() instanceof Skeleton)
            return;
        if (projectile.getShooter() instanceof Blaze)
            return;

        Player player = (Player) projectile.getShooter();
        if (!rAbilities.getEggRefunds().containsKey(player))
            return;
        if (event.getEntity() == null)
            return;

        if (!(projectile.hasMetadata("mixer")))
            return;
        if (rAbilities.config.getConfig().getBoolean("Refund.Enabled")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    rAbilities.getEggRefunds().remove(player);
                    Cooldowns.getRefundcool().applyCooldown(player, rAbilities.config.getConfig().getInt("Refund.Cooldown") * 1000);
                    Cooldowns.getAbilitycd().removeCooldown(player);
                    Cooldowns.getMixer().removeCooldown(player);
                    player.getInventory().addItem(stack);
                    for (String list : rAbilities.config.getConfig().getStringList("Refund.Message.Refunded")) {
                        player.sendMessage(chatUtil.chat(list));
                    }
                }
            }.runTaskLater(rAbilities.getInstance(), 20);
        }
    }
    @EventHandler
    public void onImpulseHit(ProjectileHitEvent event) {

        Projectile projectile = event.getEntity();

        List<String> lore = new ArrayList<>();
        for (String list : rAbilities.config.getConfig().getStringList("ImpulseBomb.Lore")) {
            lore.add(chatUtil.chat(list));
        }
        ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("ImpulseBomb.Item")),
                1).displayName(
                chatUtil.chat(rAbilities.config.getConfig().getString("ImpulseBomb.Name")))
                .setLore(lore).build();

        if (projectile.getShooter() instanceof Skeleton)
            return;
        if (projectile.getShooter() instanceof Blaze)
            return;

        Player player = (Player) projectile.getShooter();
        if (!rAbilities.getSnowballRefunds().containsKey(player))
            return;
        if (event.getEntity() == null)
            return;

        if (!(projectile.hasMetadata("impulsebomb")))
            return;

        if (rAbilities.config.getConfig().getBoolean("Refund.Enabled")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    rAbilities.getSnowballRefunds().remove(player);
                    Cooldowns.getRefundcool().applyCooldown(player, rAbilities.config.getConfig().getInt("Refund.Cooldown") * 1000);
                    Cooldowns.getAbilitycd().removeCooldown(player);
                    Cooldowns.getImpulsebomb().removeCooldown(player);
                    player.getInventory().addItem(stack);
                    for (String list : rAbilities.config.getConfig().getStringList("Refund.Message.Refunded")) {
                        player.sendMessage(chatUtil.chat(list));
                    }
                }
            }.runTaskLater(rAbilities.getInstance(), 20L);
        }
    }
    @EventHandler
    public void onSwitcherHit(ProjectileHitEvent event) {

        Projectile projectile = event.getEntity();

        List<String> lore = new ArrayList<>();
        for (String list : rAbilities.config.getConfig().getStringList("Switcher.Lore")) {
            lore.add(chatUtil.chat(list));
        }
        ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("Switcher.Item")), 1).displayName(
                chatUtil.chat(rAbilities.config.getConfig().getString("Switcher.Name")))
                .setLore(lore).build();

        if (projectile.getShooter() instanceof Skeleton)
            return;
        if (projectile.getShooter() instanceof Blaze)
            return;

        Player player = (Player) projectile.getShooter();
        if (!rAbilities.getSnowballRefunds().containsKey(player))
            return;
        if (event.getEntity() == null)
            return;

        if (!(projectile.hasMetadata("switcher")))
            return;

        if (rAbilities.config.getConfig().getBoolean("Refund.Enabled")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    rAbilities.getSnowballRefunds().remove(player);
                    Cooldowns.getRefundcool().applyCooldown(player, rAbilities.config.getConfig().getInt("Refund.Cooldown") * 1000);
                    Cooldowns.getAbilitycd().removeCooldown(player);
                    Cooldowns.getSwitcher().removeCooldown(player);
                    player.getInventory().addItem(stack);
                    for (String list : rAbilities.config.getConfig().getStringList("Refund.Message.Refunded")) {
                        player.sendMessage(chatUtil.chat(list));
                    }
                }
            }.runTaskLater(rAbilities.getInstance(), 20L);
        }
    }

    @EventHandler
    public void onChicken(EntitySpawnEvent event) {
        if (event.getEntityType().equals(EntityType.CHICKEN)) {
            event.setCancelled(true);
        } else {
            return;
        }
    }

}
