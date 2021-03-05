package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.ArrayList;
import java.util.List;

public class Harpoon implements Listener {

    private AbilityEvents item = AbilityEvents.HARPOON;

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().name().contains("LEFT")) {

            if (!item.isSimilar(player.getItemInHand()))
                return;
            if (item.isSimilar(player.getItemInHand())) {
                if (AbilityEvents.checkLocation(player.getLocation(), rAbilities.config.getConfig().getInt("SPAWN.Radius"), 0, 0)) {
                    for (String list : rAbilities.config.getConfig().getStringList("SPAWN.CantUse")) {
                        player.sendMessage(chatUtil.chat(list));
                    }
                    event.setCancelled(true);
                    return;
                }
            }
            if (Cooldowns.getAbilitycd().onCooldown(player)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(player))));
                    return;
                }
            }
            if (Cooldowns.getHarpoon().onCooldown(player)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("Harpoon.Name")).replace("%time%", Cooldowns.getHarpoon().getRemaining(player))));
                    return;
                }
            }

            Player lookingAt = this.getNearestEntityInSight(player, 7);
            if (lookingAt == null) {
                player.sendMessage(chatUtil.chat("&cYou are not looking at a player."));
                return;
            }
            for (String list : rAbilities.config.getConfig().getStringList("Harpoon.Message.Pushed")) {
                player.sendMessage(chatUtil.chat(list).replaceAll("%player%", lookingAt.getName()));
            }
            for (String list : rAbilities.config.getConfig().getStringList("Harpoon.Message.Been-Pushed")) {
                lookingAt.sendMessage(chatUtil.chat(list).replaceAll("%player%", player.getName()));
            }
            Cooldowns.getHarpoon().applyCooldown(player, rAbilities.config.getConfig().getInt("Harpoon.Cooldown") * 1000);
            Cooldowns.getAbilitycd().applyCooldown(player, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            Vector dir = player.getLocation().getDirection();
            dir.setY(0.75);
            player.getWorld().playSound(player.getLocation(), Sound.BLAZE_HIT, 3.0f, 1.0f);
            player.setVelocity(dir.multiply(this.getDistance(player, lookingAt) / 7));

        } else if (event.getAction().name().contains("RIGHT")) {
            if (!item.isSimilar(player.getItemInHand()))
                return;

            if (item.isSimilar(player.getItemInHand())) {
                if (AbilityEvents.checkLocation(player.getLocation(), rAbilities.config.getConfig().getInt("SPAWN.Radius"), 0, 0)) {
                    for (String list : rAbilities.config.getConfig().getStringList("SPAWN.CantUse")) {
                        player.sendMessage(chatUtil.chat(list));
                    }
                    event.setCancelled(true);
                    return;
                }
            }
            if (Cooldowns.getAbilitycd().onCooldown(player)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(player))));
                    return;
                }
            }
            if (Cooldowns.getHarpoon().onCooldown(player)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("Harpoon.Name")).replace("%time%", Cooldowns.getHarpoon().getRemaining(player))));
                    return;
                }
            }
            Player lookingAt = this.getNearestEntityInSight(player, 7);
            if (lookingAt == null) {
                player.sendMessage(chatUtil.chat("&cYou are not looking at a player."));
                return;
            }
            for (String list : rAbilities.config.getConfig().getStringList("Harpoon.Message.Pulled")) {
                player.sendMessage(chatUtil.chat(list).replaceAll("%player%", lookingAt.getName()));
            }
            for (String list : rAbilities.config.getConfig().getStringList("Harpoon.Message.Been-Pulled")) {
                lookingAt.sendMessage(chatUtil.chat(list).replaceAll("%player%", player.getName()));
            }
            Cooldowns.getHarpoon().applyCooldown(player, rAbilities.config.getConfig().getInt("Harpoon.Cooldown") * 1000);
            Cooldowns.getAbilitycd().applyCooldown(player, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            Vector dir = player.getLocation().getDirection();
            dir.multiply(this.getDistance(player, lookingAt) / 6.1 * -0.5);
            dir.setY(0.462);
            lookingAt.getWorld().playSound(lookingAt.getLocation(), Sound.BLAZE_HIT, 3.0f, 1.0f);
            lookingAt.setVelocity(dir);
        }
    }
    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getHarpoon().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("Harpoon.Name")).replace("%time%", Cooldowns.getHarpoon().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
    private Player getNearestEntityInSight(Player player, int range) {
        List<Player> inRange = new ArrayList<Player>();
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (this.getDistance(player, online) < 5.1) {
                inRange.add(online);
            }
        }
        for (Player in : inRange) {
            if (this.getLookingAt(player, in)) {
                return in;
            }
        }
        return null;
    }

    private boolean getLookingAt(Player player, Player player1) {
        Location eye = player.getEyeLocation();
        Vector toEntity = player1.getEyeLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());
        Vector toEntity2 = player1.getLocation().toVector().subtract(eye.toVector());
        double dot2 = toEntity2.normalize().dot(eye.getDirection());
        return dot > 0.99 || dot2 > 0.99;
    }

    private double getDistance(Player player, Player player1) {
        return player.getLocation().distance(player1.getLocation());
    }

}
