package me.razorni.dev.items.abilities;

import me.razorni.dev.cooldown.Cooldowns;
import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class GrapplingHook implements Listener {

    private AbilityEvents item = AbilityEvents.GRAPPLER;
    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {

        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getGrappler().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("Grappler.Name")).replace("%time%", Cooldowns.getGrappler().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
    @EventHandler
    public void onFish(PlayerFishEvent event) {



        if (!item.isSimilar(event.getPlayer().getItemInHand()))
            return;

        if (item.isSimilar(event.getPlayer().getItemInHand())) {
            if (AbilityEvents.checkLocation(event.getPlayer().getLocation(), rAbilities.config.getConfig().getInt("SPAWN.Radius"), 0, 0)) {
                for (String list : rAbilities.config.getConfig().getStringList("SPAWN.CantUse")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list));
                }
                event.setCancelled(true);
                return;
            }
        }

        if (Cooldowns.getAbilitycd().onCooldown(event.getPlayer())) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(event.getPlayer()))));
                event.setCancelled(true);
                return;
            }
        }
        if (Cooldowns.getGrappler().onCooldown(event.getPlayer())) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%",
                        rAbilities.config.getConfig().getString("Grappler.Name")).replace("%time%",
                        Cooldowns.getGrappler().getRemaining(event.getPlayer()))));
                event.setCancelled(true);
                return;
            }
        }
        if (event.getState() == PlayerFishEvent.State.IN_GROUND) {
            Cooldowns.getGrappler().applyCooldown(event.getPlayer(), rAbilities.config.getConfig().getInt("Grappler.Cooldown") * 1000);
            if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                Cooldowns.getAbilitycd().applyCooldown(event.getPlayer(), rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            }
            Location loc = event.getHook().getLocation();
            Location playerloc = event.getPlayer().getLocation();
            Location hookloc = loc.subtract(playerloc);
            Vector vector = new Vector(hookloc.toVector().normalize().multiply(rAbilities.config.getConfig().getInt("Grappler.Boost")).getX(),
                    0.5,
                    hookloc.toVector().normalize().multiply(rAbilities.config.getConfig().getInt("Grappler.Boost")).getZ());
            event.getPlayer().setVelocity(vector);
            for (String list : rAbilities.config.getConfig().getStringList("Grappler.Message.Used")) {
                event.getPlayer().sendMessage(chatUtil.chat(list));
                return;
            }
        }

    }

}
