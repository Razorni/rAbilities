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
import me.razorni.dev.cooldown.Cooldowns;

import java.util.ArrayList;
import java.util.Collection;

public class PotionInheritor implements Listener {

    private ArrayList<Player> hit1 = new ArrayList<>();
    private ArrayList<Player> hit2 = new ArrayList<>();
    private ArrayList<Player> hit3 = new ArrayList<>();
    private AbilityEvents item = AbilityEvents.POTIONINHERITOR;

    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getPotioninheritor().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("PotionInheritor.Name")).replace("%time%", Cooldowns.getPotioninheritor().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {

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
                if (Cooldowns.getPotioninheritor().onCooldown(damager)) {
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                        damager.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PotionInheritor.Name")).replace("%time%", Cooldowns.getPotioninheritor().getRemaining(damager))));
                        event.setCancelled(true);
                        return;
                    }
                }
                if (hit1.contains(damaged)) {
                    hit1.remove(damaged);
                    hit2.add(damaged);
                    return;
                }
                if (hit2.contains(damaged)) {
                    hit2.remove(damaged);
                    hit3.add(damaged);
                    return;
                }
                if (hit3.contains(damaged)) {
                    Collection<PotionEffect> damagedActivePotionEffects = (Collection<PotionEffect>) damaged.getActivePotionEffects();

                    for (String list : rAbilities.config.getConfig().getStringList("PotionInheritor.Message.Hit-Someone")) {
                        damager.sendMessage(chatUtil.chat(list.replaceAll("%player%", damaged.getName())).replaceAll("%ability%", rAbilities.config.getConfig().getString("PotionInheritor.Name")));
                    }
                    Cooldowns.getPotioninheritor().applyCooldown(damager, rAbilities.config.getConfig().getInt("PotionInheritor.Cooldown") * 1000);
                    if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                        Cooldowns.getAbilitycd().applyCooldown(damager, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
                    }

                    damager.getActivePotionEffects().clear();
                    for (PotionEffect potionEffectType : damagedActivePotionEffects) {
                        damager.addPotionEffect(new PotionEffect(potionEffectType.getType(), rAbilities.config.getConfig().getInt("PotionInheritor.Effect.Time") * 20, potionEffectType.getAmplifier()));
                    }
                    AbilityEvents.takeItem(damager, damager.getItemInHand());
                    hit3.remove(damaged);
                    damager.updateInventory();
                }
                if (damaged.getActivePotionEffects() == null) {
                    for (String list : rAbilities.config.getConfig().getStringList("PotionInheritor.Message.No-Effects")) {
                        damager.sendMessage(chatUtil.chat(list.replaceAll("%player%", damaged.getName())).replaceAll("%ability%", rAbilities.config.getConfig().getString("PotionInheritor.Name")));
                    }
                    return;
                }
                hit1.add(damaged);
                return;
            }
        }
    }
}
