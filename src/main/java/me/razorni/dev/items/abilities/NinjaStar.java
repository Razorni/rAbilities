package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.*;

public class NinjaStar implements Listener {

    private AbilityEvents item = AbilityEvents.NINJASTAR;
    private Map<UUID, UUID> ninjastarmap = new HashMap<>();
    private List<Player> ninja = new ArrayList<>();

    @EventHandler
    public void onNinjaStar(PlayerInteractEvent event) {

        Player p = event.getPlayer();

        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item.isSimilar(p.getItemInHand()))
            return;

        if (item.isSimilar(p.getItemInHand())) {
            if (AbilityEvents.checkLocation(p.getLocation(), rAbilities.config.getConfig().getInt("SPAWN.Radius"), 0, 0)) {
                for (String list : rAbilities.config.getConfig().getStringList("SPAWN.CantUse")) {
                    p.sendMessage(chatUtil.chat(list));
                }
                event.setCancelled(true);
                return;
            }
        }
        if (Cooldowns.getAbilitycd().onCooldown(p)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.AbilityEvents.OnCooldown")) {
                p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                event.setCancelled(true);
                p.updateInventory();
                return;
            }
        }
        if (Cooldowns.getNinjastar().onCooldown(p)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("NinjaStar.Name")).replace("%time%", Cooldowns.getNinjastar().getRemaining(p))));
                event.setCancelled(true);
                p.updateInventory();
                return;
            }
        }
        Player damaged = Bukkit.getPlayer(ninjastarmap.get(p.getUniqueId()));
        if (!ninjastarmap.containsKey(p.getUniqueId())) {
            p.sendMessage(chatUtil.chat("&cNo player has hit you in the last 15 seconds."));
            return;
        }
        if (!ninja.contains(p)) {
            p.sendMessage(chatUtil.chat("&cNo player has hit you in the last 15 seconds."));
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                p.teleport(damaged.getLocation());
                for (String list : rAbilities.config.getConfig().getStringList("NinjaStar.Message.Teleported")) {
                    p.sendMessage(chatUtil.chat(list).replaceAll("%player%", damaged.getName()));
                }
                if (rAbilities.config.getConfig().getBoolean("NinjaStar.Been-Teleported.Message")) {
                    for (String list : rAbilities.config.getConfig().getStringList("NinjaStar.Message.Been-Teleported")) {
                        damaged.sendMessage(chatUtil.chat(list).replaceAll("%player%", p.getName()));
                    }
                }
            }
        }.runTaskLater(rAbilities.getInstance(), rAbilities.config.getConfig().getInt("NinjaStar.WarmUp") * 20);

        for (String list : rAbilities.config.getConfig().getStringList("NinjaStar.Message.Teleporting")) {
            p.sendMessage(chatUtil.chat(list).replaceAll("%player%", damaged.getName()));
        }
        ninja.remove(damaged);
        Cooldowns.getNinjastar().applyCooldown(p, rAbilities.config.getConfig().getInt("NinjaStar.Cooldown") * 1000);
        AbilityEvents.takeItem(p, p.getItemInHand());
    }

    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) {
            return;
        }
        if (event.getDamager() instanceof Projectile)
            return;
        
        Player damaged = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if (damaged == damager) {
            return;
        }
        if (ninjastarmap.containsKey(damaged.getUniqueId())) {
            ninjastarmap.remove(damaged.getUniqueId());
            ninja.remove(damaged);
        } else {
            ninjastarmap.put(damaged.getUniqueId(), damager.getUniqueId());
            ninja.add(damaged);
            new BukkitRunnable() {
                public void run() {
                    ninjastarmap.remove(damaged.getUniqueId(), damager.getUniqueId());
                    ninja.remove(damaged);
                }
            }.runTaskLater(rAbilities.getInstance(), 15 * 20);
        }
    }

    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getNinjastar().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("NinjaStar.Name")).replace("%time%", Cooldowns.getNinjastar().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

}
