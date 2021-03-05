package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.HashMap;
import java.util.Map;

public class ExoticBone implements Listener {

    private Map<Player, Integer> hits = new HashMap<>();
    private AbilityEvents item = AbilityEvents.EXOTICBONE;
    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getBone().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("ExoticBone.Name")).replace("%time%", Cooldowns.getBone().getRemaining(event.getPlayer()))));
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
                if (Cooldowns.getBone().onCooldown(damager)) {
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                        damager.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getBone().getRemaining(damager))));
                        event.setCancelled(true);
                        return;
                    }
                }

                hits.putIfAbsent(damager, 0);
                hits.put(damager, hits.get(damager) + 1);

                if (hits.get(damager) == rAbilities.config.getConfig().getInt("ExoticBone.Hits")) {
                    for (String list : rAbilities.config.getConfig().getStringList("ExoticBone.Message.Been-Hit")) {
                        damaged.sendMessage(chatUtil.chat(list.replaceAll("%player%", damager.getName()).replaceAll("%time%", String.valueOf(rAbilities.config.getConfig().getInt("ExoticBone.Message.Been-Hit")))));
                    }
                    for (String list : rAbilities.config.getConfig().getStringList("ExoticBone.Message.Hit-Someone")) {
                        damager.sendMessage(chatUtil.chat(list.replaceAll("%player%", damaged.getName())).replaceAll("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")));
                    }
                    Cooldowns.getIsboned().applyCooldown(damaged, rAbilities.config.getConfig().getInt("ExoticBone.Cooldown.Damaged") * 1000);
                    Cooldowns.getBone().applyCooldown(damager, rAbilities.config.getConfig().getInt("ExoticBone.Cooldown.Damager") * 1000);
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

    @EventHandler(priority = EventPriority.HIGH)
    public void onBreak(BlockPlaceEvent event) {
        if (Cooldowns.getIsboned().onCooldown(event.getPlayer())) {
            for (String list : rAbilities.config.getConfig().getStringList("ExoticBone.Message.Cant-Build")) {
                event.getPlayer().sendMessage(chatUtil.chat(list).replace("%time%", Cooldowns.getIsboned().getRemaining(event.getPlayer())));
            }
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBreak(BlockBreakEvent event) {
        if (Cooldowns.getIsboned().onCooldown(event.getPlayer())) {
            for (String list : rAbilities.config.getConfig().getStringList("ExoticBone.Message.Cant-Build")) {
                event.getPlayer().sendMessage(chatUtil.chat(list).replace("%time%", Cooldowns.getIsboned().getRemaining(event.getPlayer())));
            }
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (Cooldowns.getIsboned().onCooldown(player)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getClickedBlock().getType() == Material.CHEST
                        || event.getClickedBlock().getType() == Material.TRAP_DOOR
                        || event.getClickedBlock().getType() == Material.WOOD_DOOR
                        || event.getClickedBlock().getType() == Material.WOODEN_DOOR
                        || event.getClickedBlock().getType() == Material.TRAPPED_CHEST
                        || event.getClickedBlock().getType() == Material.FENCE_GATE) {
                    if (event.getClickedBlock() == null)
                        return;

                    for (String list : rAbilities.config.getConfig().getStringList("ExoticBone.Message.Cant-Build")) {
                        event.getPlayer().sendMessage(chatUtil.chat(list).replace("%time%", Cooldowns.getIsboned().getRemaining(event.getPlayer())));
                    }
                    event.setCancelled(true);
                }
            }
        }
    }
}
