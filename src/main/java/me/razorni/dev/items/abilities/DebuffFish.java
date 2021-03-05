package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.HashMap;
import java.util.Map;

public class DebuffFish implements Listener {

    private Map<Player, Integer> hits = new HashMap<>();
    private AbilityEvents item = AbilityEvents.DEBUFFFISH;
    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;

            if (Cooldowns.getDebufffish().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("DebuffFish.Name")).replace("%time%", Cooldowns.getDebufffish().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onExoticbone(EntityDamageByEntityEvent event) {



        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                Player damaged = (Player) event.getEntity();

                if (!item.isSimilar(damager.getItemInHand()))
                    return;

                if (item.isSimilar(damager.getItemInHand())) {
                    if (AbilityEvents.checkLocation(damager.getLocation(), rAbilities.config.getConfig().getInt("SPAWN.Radius"), 0, 0)) {
                        for (String list : rAbilities.config.getConfig().getStringList("SPAWN.CantUse")) {
                            damager.sendMessage(chatUtil.chat(list));
                        }
                        event.setCancelled(true);
                        return;
                    }
                }
                if (Cooldowns.getAbilitycd().onCooldown(damager)) {
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                        damager.sendMessage(chatUtil.chat(list).replace("%time%", Cooldowns.getAbilitycd().getRemaining(damager)));
                        event.setCancelled(true);
                        return;
                    }
                }
                if (Cooldowns.getDebufffish().onCooldown(damager)) {
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                        damager.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("DebuffFish.Name")).replace("%time%", Cooldowns.getDebufffish().getRemaining(damager))));
                        event.setCancelled(true);
                        return;
                    }
                }

                hits.putIfAbsent(damager, 0);
                hits.put(damager, hits.get(damager) + 1);

                if (hits.get(damager) == rAbilities.config.getConfig().getInt("DebuffFish.Hits")) {

                    for (String list : rAbilities.config.getConfig().getStringList("DebuffFish.Message.Been-Hit")) {
                        damaged.sendMessage(chatUtil.chat(list.replaceAll("%player%", damager.getName()).replaceAll("%time%", String.valueOf(rAbilities.config.getConfig().getInt("DebuffFish.Message.Been-Hit")))));
                    }

                    for (String list : rAbilities.config.getConfig().getStringList("DebuffFish.Message.Hit-Someone")) {
                        damager.sendMessage(chatUtil.chat(list.replaceAll("%player%", damaged.getName())).replaceAll("%ability%", rAbilities.config.getConfig().getString("DebuffFish.Name")));
                    }

                    if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                        Cooldowns.getAbilitycd().applyCooldown(damager, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
                    }

                    for (String section : rAbilities.config.getConfig().getConfigurationSection("DebuffFish.Effects").getKeys(false)) {

                        int power = rAbilities.config.getConfig().getInt("DebuffFish.Effects." + section + ".Power");
                        int time = rAbilities.config.getConfig().getInt("DebuffFish.Effects." + section + ".Time");
                        String type = rAbilities.config.getConfig().getString("DebuffFish.Effects." + section + ".Type");

                        damaged.addPotionEffect(new PotionEffect(PotionEffectType.getByName(type), time * 20, power - 1));

                    }

                    Cooldowns.getDebufffish().applyCooldown(damager, rAbilities.config.getConfig().getInt("DebuffFish.Cooldown") * 1000);
                    AbilityEvents.takeItem(damager, damager.getItemInHand());
                    hits.remove(damager);
                    damager.updateInventory();
                }
                return;
            }
        }
    }
}
