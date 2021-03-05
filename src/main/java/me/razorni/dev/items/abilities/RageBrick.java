package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.items.events.RageBrickEvent;
import me.razorni.dev.items.events.TankIngotEvent;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.razorni.dev.cooldown.Cooldowns;

public class RageBrick implements Listener {


    public static int BRICK_RANGE = rAbilities.config.getConfig().getInt("RageBrick.Distance");
    private AbilityEvents item = AbilityEvents.RAGEBRICK;

    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getRagebrick().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("RageBrick.Name")).replace("%time%", Cooldowns.getRagebrick().getRemaining(event.getPlayer()))));
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
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Cooldowns.getAbilitycd().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                    event.setCancelled(true);
                    p.updateInventory();
                    return;
                }
            }
            if (Cooldowns.getRagebrick().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("RageBrick.Name")).replace("%time%", Cooldowns.getRagebrick().getRemaining(p))));
                    event.setCancelled(true);
                    p.updateInventory();
                    return;
                }
            }
            if (TankIngotEvent.getNearbyPlayers(p).isEmpty()) {
                for (String list : rAbilities.config.getConfig().getStringList("RageBrick.Message.Denied")) {
                    p.sendMessage(chatUtil.chat(list));
                }
                event.setCancelled(true);
                p.updateInventory();
            }
            if (!TankIngotEvent.getNearbyPlayers(p).isEmpty()) {
                Cooldowns.getRagebrick().applyCooldown(p, rAbilities.config.getConfig().getInt("RageBrick.Cooldown") * 1000);
                if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                    Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
                }
                for (String section : rAbilities.config.getConfig().getConfigurationSection("RageBrick.Effects").getKeys(false)) {

                    String type = rAbilities.config.getConfig().getString("RageBrick.Effects." + section + ".Type");
                    int power = rAbilities.config.getConfig().getInt("RageBrick.Effects." + section + ".Power");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.getByName(type), TankIngotEvent.getNearbyPlayers(p).size() * 20 * rAbilities.config.getConfig().getInt("RageBrick.Seconds-Per-Player"), power - 1));
                }
                AbilityEvents.takeItem(p, p.getItemInHand());
                p.updateInventory();
                for (String list : rAbilities.config.getConfig().getStringList("RageBrick.Message.Success")) {
                    int timefinal = rAbilities.config.getConfig().getInt("RageBrick.Seconds-Per-Player") * RageBrickEvent.getNearbyPlayers(p).size();
                    p.sendMessage(chatUtil.chat(list).replace("%time%", String.valueOf(timefinal)));
                    return;
                }
            }
        }
    }

}
