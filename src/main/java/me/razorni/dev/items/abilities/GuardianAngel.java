package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.HashMap;
import java.util.UUID;

public class GuardianAngel implements Listener {

    private AbilityEvents item = AbilityEvents.GUARDIAN;
    public HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onGuardian(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (!event.getAction().name().contains("RIGHT"))
            return;
        if (!item.isSimilar(player.getItemInHand()))
            return;

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
                player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(player))));
                event.setCancelled(true);
                player.updateInventory();
                return;
            }
        }
        if (Cooldowns.getGuardianangel().onCooldown(player)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("GuardianAngel.Name")).replace("%time%", Cooldowns.getGuardianangel().getRemaining(player))));
                event.setCancelled(true);
                player.updateInventory();
                return;
            }
        }
        AbilityEvents.takeItem(player, player.getItemInHand());
        Cooldowns.getGuardianangel().applyCooldown(player, rAbilities.config.getConfig().getInt("GuardianAngel.Cooldown") * 1000);
        Cooldowns.getAbilitycd().applyCooldown(player, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
        cooldown.put(player.getUniqueId(), System.currentTimeMillis() + rAbilities.config.getConfig().getInt("GuardianAngel.Initiated-Time") * 1000);
        for (String list : rAbilities.config.getConfig().getStringList("GuardianAngel.Message.Used")) {
            player.sendMessage(chatUtil.chat(list));
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (cooldown.containsKey(player.getUniqueId())) {
                    for (String list : rAbilities.config.getConfig().getStringList("GuardianAngel.Message.Expired")) {
                        player.sendMessage(chatUtil.chat(list));
                    }
                    cooldown.remove(player.getUniqueId());
                }
            }
        }.runTaskLater(rAbilities.getInstance(), 20L * rAbilities.config.getConfig().getInt("GuardianAngel.Initiated-Time"));
    }
    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getGuardianangel().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("GuardianAngel.Name")).replace("%time%", Cooldowns.getGuardianangel().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
    @EventHandler
    public void onHitGuardian(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player damaged = (Player)event.getEntity();
        double health = damaged.getHealth() - event.getDamage();
        if (cooldown.containsKey(damaged.getUniqueId()) && health <= rAbilities.config.getConfig().getDouble("GuardianAngel.Hearts-Before-Heal")) {
            damaged.setHealth(20);
            for (String list : rAbilities.config.getConfig().getStringList("GuardianAngel.Message.Initiated")) {
                damaged.sendMessage(chatUtil.chat(list));
            }
            cooldown.remove(damaged.getUniqueId());
            event.setCancelled(true);
        }
    }

}
