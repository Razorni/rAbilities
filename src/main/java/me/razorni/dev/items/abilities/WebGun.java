package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebGun implements Listener {
    AbilityEvents item = AbilityEvents.WEBGUN;

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
            snowball.setMetadata("webgun", new FixedMetadataValue(rAbilities.getInstance(), player.getUniqueId()));
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
        if (Cooldowns.getWebgun().onCooldown(player)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("WebGun.Name")).replace("%time%", Cooldowns.getWebgun().getRemaining(player))));
                event.setCancelled(true);
                player.updateInventory();
                return;
            }
        }
        if (Cooldowns.getRefundcool().onCooldown(player)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("Anti-Glitch.Cooldown-Display")).replace("%time%", Cooldowns.getRefundcool().getRemaining(player))));
                event.setCancelled(true);
                player.updateInventory();
                return;
            }
        }
        for (String list : rAbilities.config.getConfig().getStringList("WebGun.Message.Used")) {
            player.sendMessage(chatUtil.chat(list));
        }
        Cooldowns.getWebgun().applyCooldown(player, rAbilities.config.getConfig().getInt("WebGun.Cooldown") * 1000);
        if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
            Cooldowns.getAbilitycd().applyCooldown(player, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
        }
        player.launchProjectile(Snowball.class);
        player.updateInventory();

    }

    Map<Block, Material> blockhits = new HashMap<>();

    @EventHandler
    public void onThrow(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (event.getEntity() instanceof Player)
            return;
        if (!(event.getEntity() instanceof Snowball))
            return;
        if (!(projectile.hasMetadata("webgun")))
            return;
        Player player = (Player) projectile.getShooter();
        if (rAbilities.config.getConfig().getBoolean("WebGun.3x3-Spawn-Above-Hit-Block")) {
            Player shooter = (Player) projectile.getShooter();
            Location loc4 = projectile.getLocation();
            Location loc1 = new Location(projectile.getLocation().getWorld(),
                    projectile.getLocation().getX(), projectile.getLocation().getY(), projectile.getLocation().getZ() - 0.5);
            Location loc2 = new Location(projectile.getLocation().getWorld(),
                    projectile.getLocation().getX() + 0.5, projectile.getLocation().getY(), projectile.getLocation().getZ() - 0.5);
            Location loc3 = new Location(projectile.getLocation().getWorld(),
                    projectile.getLocation().getX() + 0.5, projectile.getLocation().getY(), projectile.getLocation().getZ());
            Location loc5 = new Location(projectile.getLocation().getWorld(),
                    projectile.getLocation().getX() + 1, projectile.getLocation().getY(), projectile.getLocation().getZ() + 1);
            Location loc6 = new Location(projectile.getLocation().getWorld(),
                    projectile.getLocation().getX() - 1, projectile.getLocation().getY(), projectile.getLocation().getZ() + 1);
            Location loc7 = new Location(projectile.getLocation().getWorld(),
                    projectile.getLocation().getX(), projectile.getLocation().getY(), projectile.getLocation().getZ() + 1);
            Location loc8 = new Location(projectile.getLocation().getWorld(),
                    projectile.getLocation().getX() - 1, projectile.getLocation().getY(), projectile.getLocation().getZ());
            Location loc9 = new Location(projectile.getLocation().getWorld(),
                    projectile.getLocation().getX() - 1, projectile.getLocation().getY(), projectile.getLocation().getZ() - 1);
            ArrayList<FallingBlock> droppedWebs = new ArrayList<FallingBlock>();
            droppedWebs.add(projectile.getLocation().getWorld().spawnFallingBlock(loc1, Material.WEB, (byte) 0));
            droppedWebs.add(projectile.getLocation().getWorld().spawnFallingBlock(loc2, Material.WEB, (byte) 0));
            droppedWebs.add(projectile.getLocation().getWorld().spawnFallingBlock(loc3, Material.WEB, (byte) 0));
            droppedWebs.add(projectile.getLocation().getWorld().spawnFallingBlock(loc4, Material.WEB, (byte) 0));
            droppedWebs.add(projectile.getLocation().getWorld().spawnFallingBlock(loc5, Material.WEB, (byte) 0));
            droppedWebs.add(projectile.getLocation().getWorld().spawnFallingBlock(loc6, Material.WEB, (byte) 0));
            droppedWebs.add(projectile.getLocation().getWorld().spawnFallingBlock(loc7, Material.WEB, (byte) 0));
            droppedWebs.add(projectile.getLocation().getWorld().spawnFallingBlock(loc8, Material.WEB, (byte) 0));
            droppedWebs.add(projectile.getLocation().getWorld().spawnFallingBlock(loc9, Material.WEB, (byte) 0));
        }
    }

    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getWebgun().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("WebGun.Name")).replace("%time%", Cooldowns.getWebgun().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onBlockChange(EntityChangeBlockEvent e) {

        if (e.getEntity().getType() == EntityType.FALLING_BLOCK && e.getTo() == Material.WEB) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(rAbilities.getInstance(),
                    new BukkitRunnable() {
                public void run() {
                    e.getBlock().setType(Material.AIR);
                }
            }, 20 * rAbilities.config.getConfig().getInt("WebGun.Web-Delay"));
        }
    }

}
