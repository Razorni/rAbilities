package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.Random;

public class AntiFallBoots implements Listener {

    AbilityEvents item = AbilityEvents.ANTIFALL;

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL && item.isSimilar(((Player) entity).getInventory().getBoots())) {
            Player player = (Player) entity;
            Random ran = new Random();
            int choice = ran.nextInt(100) + 1;
            if (Cooldowns.getAbilitycd().onCooldown(player)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(player))));
                    event.setCancelled(false);
                    player.updateInventory();
                }
                return;
            }
            if (Cooldowns.getAntifall().onCooldown(player)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("AntiFall.Name")).replace("%time%", Cooldowns.getAntifall().getRemaining(player))));
                    event.setCancelled(false);
                    player.updateInventory();
                }
                return;
            }
            if (choice > rAbilities.config.getConfig().getInt("AntiFall.Chance")) {
                if (rAbilities.config.getConfig().getBoolean("AntiFall.Cooldown.Enabled-Success")) {
                    Cooldowns.getAntifall().applyCooldown(player, rAbilities.config.getConfig().getInt("AntiFall.Cooldown.Time") * 1000);
                    if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                        Cooldowns.getAbilitycd().applyCooldown(player, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
                    }
                    for (String list : rAbilities.config.getConfig().getStringList("AntiFall.Message.Success")) {
                        player.sendMessage(chatUtil.chat(list));
                    }
                    event.setCancelled(true);
                }
                return;
            }
            if (choice < rAbilities.config.getConfig().getInt("AntiFall.Chance")) {
                if (rAbilities.config.getConfig().getBoolean("AntiFall.Cooldown.Enabled-Failed")) {
                    Cooldowns.getAntifall().applyCooldown(player, rAbilities.config.getConfig().getInt("AntiFall.Cooldown.Time") * 1000);
                    if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                        Cooldowns.getAbilitycd().applyCooldown(player, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
                    }
                    for (String list : rAbilities.config.getConfig().getStringList("AntiFall.Message.Failed")) {
                        player.sendMessage(chatUtil.chat(list));
                    }
                    event.setCancelled(false);
                }
            }
        }
    }

}

