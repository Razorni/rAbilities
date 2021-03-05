package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.*;

public class AntiPot implements Listener {

    private Map<Player, Integer> hits = new HashMap<>();
    private AbilityEvents item = AbilityEvents.ANTIPOT;

    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getAntipotion().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("AntiPotion.Name")).replace("%time%", Cooldowns.getAntipotion().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {



        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                Player damaged = (Player) event.getEntity();

                if (!item.isSimilar(damager.getItemInHand()))
                    return;
                if (item.isSimilar(damager.getItemInHand())) {
                    if (AbilityEvents.checkLocation(damager.getLocation(), rAbilities.config.getConfig().getInt("SPAWN.Radius"), 0, 0)) {
                        for (String list : rAbilities.config.getConfig().getStringList("SPAWN.CantUse")) {
                            damager.sendMessage(chatUtil.chat(list));
                        }
                        event.setCancelled(true);
                        return;
                    }
                }
                if (Cooldowns.getAbilitycd().onCooldown(damager)) {
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                        damager.sendMessage(chatUtil.chat(list).replace("%time%", Cooldowns.getAbilitycd().getRemaining(damager)));
                        event.setCancelled(true);
                        return;
                    }
                }
                if (Cooldowns.getAntipotion().onCooldown(damager)) {
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                        damager.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("AntiPotion.Name")).replace("%time%", Cooldowns.getAntipotion().getRemaining(damager))));
                        event.setCancelled(true);
                        return;
                    }
                }
                hits.putIfAbsent(damager, 0);
                hits.put(damager, hits.get(damager) + 1);

                if (hits.get(damager) == rAbilities.config.getConfig().getInt("AntiPotion.Hits")) {
                    for (String list : rAbilities.config.getConfig().getStringList("AntiPotion.Message.Been-Hit")) {
                        damaged.sendMessage(chatUtil.chat(list.replaceAll("%player%", damager.getName()).replaceAll("%time%", String.valueOf(rAbilities.config.getConfig().getInt("AntiPotion.Cooldown.Hit-Player")))));
                    }
                    for (String list : rAbilities.config.getConfig().getStringList("AntiPotion.Message.Hit-Someone")) {
                        damager.sendMessage(chatUtil.chat(list.replaceAll("%player%", damaged.getName())).replaceAll("%ability%", rAbilities.config.getConfig().getString("AntiPotion.Name")));
                    }
                    Cooldowns.getIsantipotioned().applyCooldown(damaged, rAbilities.config.getConfig().getInt("AntiPotion.Cooldown.Hit-Player") * 1000);
                    Cooldowns.getAntipotion().applyCooldown(damager, rAbilities.config.getConfig().getInt("AntiPotion.Cooldown.Damager") * 1000);
                    if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                        Cooldowns.getAbilitycd().applyCooldown(damager, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
                    }
                    AbilityEvents.takeItem(damager, damager.getItemInHand());
                    hits.remove(damager);
                    damager.updateInventory();
                }
                return;
            }
        }
    }
    @EventHandler
    public void onAntiPot(PlayerInteractEvent event) {
        if (!(event.getAction().name().contains("RIGHT")))
            return;

        if (event.getPlayer().getItemInHand().getType() == Material.POTION) {
            if (Cooldowns.getIsantipotioned().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("AntiPotion.Message.Cant-Pot")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("AntiPotion.Name")).replace("%time%", Cooldowns.getIsantipotioned().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlaceAntiPot(BlockPlaceEvent event) {
        if (item.isSimilar(event.getPlayer().getItemInHand())) {
            event.setCancelled(true);
            return;
        }
    }

}
