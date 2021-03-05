package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import me.razorni.dev.cooldown.Cooldowns;

public class Cleave implements Listener {

    private AbilityEvents item = AbilityEvents.CLEAVE;


    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getCleave().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("Cleave.Name")).replace("%time%", Cooldowns.getCleave().getRemaining(event.getPlayer()))));
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
                        damager.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("AntiRedstone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(damager))));
                        event.setCancelled(true);
                        return;
                    }
                }
                if (Cooldowns.getCleave().onCooldown(damager)) {
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                        damager.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("Cleave.Name")).replace("%time%", Cooldowns.getCleave().getRemaining(damager))));
                        event.setCancelled(true);
                        return;
                    }
                }

                for (String list : rAbilities.config.getConfig().getStringList("Cleave.Message.Been-Hit")) {
                    damaged.sendMessage(chatUtil.chat(list).replace("%player%", damager.getName()));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Cleave.Message.Hit-Someone")) {
                    damager.sendMessage(chatUtil.chat(list).replace("%player%", damaged.getName()));
                }
                Cooldowns.getCleave().applyCooldown(damager, rAbilities.config.getConfig().getInt("Cleave.Cooldown") * 1000);
                if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                    Cooldowns.getAbilitycd().applyCooldown(damager, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
                }
                AbilityEvents.takeItem(damager, damager.getItemInHand());
                Cooldowns.getIscleaved().applyCooldown(damaged, rAbilities.config.getConfig().getInt("Cleave.Bleed-Duration") * 1000);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (Cooldowns.getIscleaved().onCooldown(damaged)) {
                            if (damaged.getHealth() < 1.0)
                                return;

                            for (String list : rAbilities.config.getConfig().getStringList("Cleave.Message.Bleeding")) {
                                damaged.sendMessage(chatUtil.chat(list));
                            }
                            if (damaged.getHealth() > rAbilities.config.getConfig().getDouble("Cleave.Hearts-Per-Second")) {
                                damaged.setHealth(damaged.getHealth() - rAbilities.config.getConfig().getDouble("Cleave.Hearts-Per-Second"));
                            } else {
                                damaged.setHealth(0);
                            }
                            damaged.damage(0);
                            damaged.getWorld().playEffect(damaged.getEyeLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
                        } else {
                            return;
                        }
                    }

                }.runTaskTimerAsynchronously(rAbilities.getInstance(), 20L, 20L);
            }
        }
    }
}
