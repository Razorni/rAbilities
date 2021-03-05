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
import me.razorni.dev.cooldown.Cooldowns;

public class PortableBackstab implements Listener {

    private AbilityEvents item = AbilityEvents.BACKSTAB;


    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getBackstab().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("BackStab.Name")).replace("%time%", Cooldowns.getBackstab().getRemaining(event.getPlayer()))));
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
                if (Cooldowns.getBackstab().onCooldown(damager)) {
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                        damager.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("BackStab.Name")).replace("%time%", Cooldowns.getBackstab().getRemaining(damager))));
                        event.setCancelled(true);
                        return;
                    }
                }

                if (getBehind(damaged, damager)) {
                    for (String list : rAbilities.config.getConfig().getStringList("BackStab.Message.Been-Hit")) {
                        damaged.sendMessage(chatUtil.chat(list).replace("%player%", damager.getName()));
                    }
                    for (String list : rAbilities.config.getConfig().getStringList("BackStab.Message.Hit-Someone")) {
                        damager.sendMessage(chatUtil.chat(list).replace("%player%", damaged.getName()));
                    }
                    Cooldowns.getBackstab().applyCooldown(damager, rAbilities.config.getConfig().getInt("BackStab.Cooldown") * 1000);
                    if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                        Cooldowns.getAbilitycd().applyCooldown(damager, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
                    }
                    AbilityEvents.takeItem(damager, damager.getItemInHand());
                    damaged.getWorld().playEffect(damaged.getEyeLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
                    if (damaged.getHealth() > rAbilities.config.getConfig().getDouble("BackStab.Health-When-Hit")) {
                        damaged.setHealth(damaged.getHealth() - rAbilities.config.getConfig().getDouble("BackStab.Health-When-Hit"));
                    } else {
                        return;
                    }
                    damaged.damage(0);
                } else {
                    for (String list : rAbilities.config.getConfig().getStringList("BackStab.Message.Not-Behind")) {
                        damager.sendMessage(chatUtil.chat(list));
                    }
                }
            }
        }
    }

    public String getDirection(Player player) {
        String dir = "";
        float y = player.getLocation().getYaw();
        if (y < 0.0F) {
            y += 360.0F;
        }
        y %= 360.0F;
        int i = (int) ((y + 8.0F) / 22.5D);
        if ((i == 0) || (i == 1) || (i == 15)) {
            dir = "west";
        } else if ((i == 4) || (i == 5) || (i == 6) || (i == 2) || (i == 3)) {
            dir = "north";
        } else if ((i == 8) || (i == 7) || (i == 9)) {
            dir = "east";
        } else if ((i == 11) || (i == 10) || (i == 12) || (i == 13) || (i == 14)) {
            dir = "south";
        } else {
            dir = "west";
        }
        return dir;
    }

    private boolean getBehind(Player player, Player attacker) {
        if (getDirection(attacker).equals(getDirection(player))) {
            return true;
        }
        return false;
    }
}
