package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import me.razorni.dev.cooldown.Cooldowns;

public class TeleportBow implements Listener {

    AbilityEvents item = AbilityEvents.TELEBOW;

    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getTelebow().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("TeleBow.Name")).replace("%time%", Cooldowns.getTelebow().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void SwitcherThrown(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item.isSimilar(player.getItemInHand()))
            return;

        if (Cooldowns.getAbilitycd().onCooldown(player)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                player.sendMessage(chatUtil.chat(list.replace("%time%", Cooldowns.getAbilitycd().getRemaining(player))));
                event.setUseItemInHand(Event.Result.DENY);
                event.setCancelled(true);
                player.updateInventory();
                return;
            }
        }

        if (Cooldowns.getTelebow().onCooldown(player)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("TeleBow.Name")).replace("%time%", Cooldowns.getTelebow().getRemaining(player))));
                event.setCancelled(true);
                player.updateInventory();
                return;
            }
        }
        Cooldowns.getRefundcool().removeCooldown(player);
        Cooldowns.getAbilitycd().applyCooldown(player, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
        Cooldowns.getTelebow().applyCooldown(player, rAbilities.config.getConfig().getInt("TeleBow.Cooldown") * 1000);
        event.getPlayer().launchProjectile(Arrow.class);
        player.updateInventory();
        player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 1));
        event.setCancelled(true);
    }

    @EventHandler
    public void onLaunch(ProjectileLaunchEvent e) {
        if (!(e.getEntity().getShooter() instanceof Player)) {
            return;
        }

        Projectile entity = e.getEntity();
        Player player = (Player) entity.getShooter();

        if (!item.isSimilar(player.getItemInHand())) {
            return;
        }

        if (entity instanceof Arrow) {
            Arrow arrow = (Arrow) entity;
            arrow.setMetadata("telebow", new FixedMetadataValue(rAbilities.getInstance(), player.getUniqueId()));

        }
    }

    @EventHandler
    public void onTelehit(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Player) {
            if (e.getDamager() instanceof Arrow) {
                Player damaged = (Player) e.getEntity();
                Arrow arrow = (Arrow) e.getDamager();
                if (!arrow.hasMetadata("telebow"))
                    return;
                if (arrow.getShooter() instanceof Player) {

                    Player damager = (Player) arrow.getShooter();

                    damager.teleport(damaged);
                    for (String list : rAbilities.config.getConfig().getStringList("TeleBow.Message.Hit-Someone")) {
                        damager.sendMessage(chatUtil.chat(list).replaceAll("%player%", damaged.getName()));
                    }
                }
            }

        }
    }
}
