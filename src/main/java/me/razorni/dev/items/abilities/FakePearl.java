package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.ArrayList;

public class FakePearl implements Listener {

    private AbilityEvents item = AbilityEvents.FAKEPEARL;
    private ArrayList<Player> fakepearl = new ArrayList<>();

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
                p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                p.updateInventory();
                event.setCancelled(true);
                return;
            }
        }
        if (Cooldowns.getFakepearl().onCooldown(p)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("FakePearl.Name")).replace("%time%", Cooldowns.getFakepearl().getRemaining(p))));
                p.updateInventory();
                event.setCancelled(true);
                return;
            }
        }
        if (p.getLocation().getBlockX() == rAbilities.config.getConfig().getInt("SPAWN.RADIUS") || p.getLocation().getBlockZ() == rAbilities.config.getConfig().getInt("SPAWN.RADIUS")) {
            for (String list : rAbilities.config.getConfig().getStringList("SPAWN.CantUse")) {
                p.sendMessage(chatUtil.chat(list));
                event.setCancelled(true);
            }
        }

        for (String list : rAbilities.config.getConfig().getStringList("FakePearl.Message.Used")) {
            p.sendMessage(chatUtil.chat(list.replaceAll("%player%", p.getName())));
        }

        Cooldowns.getFakepearl().applyCooldown(p, rAbilities.config.getConfig().getInt("FakePearl.Cooldown") * 1000);
        if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
            Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
        }

        fakepearl.add(p);
        p.updateInventory();
    }

    @EventHandler
    public void onFakePearlLand(PlayerTeleportEvent event) {
        if (fakepearl.contains(event.getPlayer())) {
            if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
                fakepearl.remove(event.getPlayer());
                event.setCancelled(true);
            }
        }
    }

}
