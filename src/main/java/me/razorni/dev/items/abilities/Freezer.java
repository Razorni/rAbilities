package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.razorni.dev.cooldown.Cooldowns;

public class Freezer implements Listener {

    AbilityEvents item = AbilityEvents.FREEZER;

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

        if (entity instanceof Snowball) {
            Snowball snowball = (Snowball) entity;
            snowball.setMetadata("freezer", new FixedMetadataValue(rAbilities.getInstance(), player.getUniqueId()));
            WitherGun.refundcheck.add(snowball);
        }
    }
    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getFreezer().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("Freezer.Name")).replace("%time%", Cooldowns.getFreezer().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
    @EventHandler
    public void onClick(PlayerInteractEvent event) {



        if (!event.getAction().name().startsWith("RIGHT")) {
            return;
        }

        Player player = event.getPlayer();

        if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
            return;
        }

        if (!item.isSimilar(player.getItemInHand())) {
            return;
        }

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
                player.sendMessage(chatUtil.chat(list.replace("%time%", Cooldowns.getAbilitycd().getRemaining(player))));
                event.setCancelled(true);
                player.updateInventory();
                return;
            }
        }

        if (Cooldowns.getFreezer().onCooldown(player)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("Freezer.Name")).replace("%time%", Cooldowns.getFreezer().getRemaining(player))));
                event.setCancelled(true);
                player.updateInventory();
                return;
            }
        }
        Cooldowns.getFreezer().applyCooldown(player, rAbilities.config.getConfig().getInt("Freezer.Cooldown") * 1000);
        if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
            Cooldowns.getAbilitycd().applyCooldown(player, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
        }
        player.launchProjectile(Snowball.class);
        player.updateInventory();

    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getDamager() instanceof Snowball) {

                Player damaged = (Player) e.getEntity();
                Snowball snowball = (Snowball) e.getDamager();
                if (!snowball.hasMetadata("freezer"))
                    return;

                if (snowball.getShooter() instanceof Player) {
                    damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, rAbilities.config.getConfig().getInt("Freezer.Effect.Time") * 23,
                            rAbilities.config.getConfig().getInt("Freezer.Effect.Power")));
                    for (String list : rAbilities.config.getConfig().getStringList("Freezer.Message.Been-Hit")) {
                        damaged.sendMessage(chatUtil.chat(list).replaceAll("%player%", ((Player) snowball.getShooter()).getName()));
                    }
                    for (String list : rAbilities.config.getConfig().getStringList("Freezer.Message.Hit-Someone")) {
                        ((Player) snowball.getShooter()).getPlayer().sendMessage(chatUtil.chat(list).replaceAll("%player%", damaged.getName()));
                    }
                    WitherGun.refundcheck.remove(snowball);
                }
            }
        }
    }

}
