package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrePearl implements Listener {

    private AbilityEvents item = AbilityEvents.PREPEARL;
    private Map<Player, Location> locationsMap = new HashMap<>();
    private Set<Player> countdown = new HashSet<>();

    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getPrepearl().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("PrePearl.Name")).replace("%time%", Cooldowns.getPrepearl().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler

    public void onClick(PlayerInteractEvent event) {

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
            for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                p.sendMessage(chatUtil.chat(list.replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                event.setCancelled(true);
                p.updateInventory();
                return;
            }
        }
        if (Cooldowns.getPrepearl().onCooldown(p)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PrePearl.Name")).replace("%time%", Cooldowns.getPrepearl().getRemaining(p))));
                event.setCancelled(true);
                return;
            }
        }
        event.setCancelled(rAbilities.config.getConfig().getBoolean("PrePearl.Throw-Pearl"));
        countdown.add(p);
        AbilityEvents.takeItem(p, p.getItemInHand());
        Cooldowns.getPrepearl().applyCooldown(p, rAbilities.config.getConfig().getInt("PrePearl.Cooldown") * 1000);
        if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
            Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
        }
        locationsMap.put(p, p.getLocation());
        for (String list : rAbilities.config.getConfig().getStringList("PrePearl.Message.Success")) {
            p.sendMessage(chatUtil.chat(list));
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                p.teleport(locationsMap.get(p));
                for (String list : rAbilities.config.getConfig().getStringList("PrePearl.Message.Teleported")) {
                    p.sendMessage(chatUtil.chat(list));
                }
                locationsMap.remove(p);
            }
        }.runTaskLater(rAbilities.getInstance(), 30 * rAbilities.config.getConfig().getInt("PrePearl.WarmUp"));
    }
}
