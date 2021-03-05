package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
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

public class PoisonDart implements Listener {

    AbilityEvents item = AbilityEvents.POISONDART;

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
            Arrow snowball = (Arrow) entity;
            snowball.setMetadata("poisondart", new FixedMetadataValue(rAbilities.getInstance(), player.getUniqueId()));
        }
    }
    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getPoisondart().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("PoisonDart.Name")).replace("%time%", Cooldowns.getPoisondart().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
    @EventHandler
    public void onClick(PlayerInteractEvent event) {



        if (!event.getAction().name().contains("RIGHT")) {
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

        if (Cooldowns.getPoisondart().onCooldown(player)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PoisonDart.Name")).replace("%time%", Cooldowns.getPoisondart().getRemaining(player))));
                event.setCancelled(true);
                player.updateInventory();
                return;
            }
        }
        Cooldowns.getPoisondart().applyCooldown(player, rAbilities.config.getConfig().getInt("PoisonDart.Cooldown") * 1000);
        if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
            Cooldowns.getAbilitycd().applyCooldown(player, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
        }
        player.launchProjectile(Arrow.class);
        player.updateInventory();

    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getDamager() instanceof Arrow) {
                Player damaged = (Player) e.getEntity();
                Arrow arrow = (Arrow) e.getDamager();
                if (!arrow.hasMetadata("poisondart"))
                    return;

                if (arrow.getShooter() instanceof Player) {
                    for (String list : rAbilities.config.getConfig().getStringList("PoisonDart.Message.Been-Hit")) {
                        damaged.sendMessage(chatUtil.chat(list).replaceAll("%player%", ((Player) arrow.getShooter()).getName()));
                    }
                    for (String list : rAbilities.config.getConfig().getStringList("PoisonDart.Message.Hit-Someone")) {
                        ((Player) arrow.getShooter()).getPlayer().sendMessage(chatUtil.chat(list).replaceAll("%player%", damaged.getName()));
                    }
                    for (String section : rAbilities.config.getConfig().getConfigurationSection("PoisonDart.Effects").getKeys(false)) {

                        int power = rAbilities.config.getConfig().getInt("PoisonDart.Effects." + section + ".Power");
                        int time = rAbilities.config.getConfig().getInt("PoisonDart.Effects." + section + ".Time");
                        String type = rAbilities.config.getConfig().getString("PoisonDart.Effects." + section + ".Type");

                        damaged.addPotionEffect(new PotionEffect(PotionEffectType.getByName(type), time * 20, power - 1));
                    }
                }
            }
        }
    }

}
