package me.razorni.dev.items.pocketbards;

import me.razorni.dev.cooldown.Cooldowns;
import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PocketBards implements Listener {
    private AbilityEvents item = AbilityEvents.INVIS;
    private AbilityEvents item2 = AbilityEvents.STRENGTH;
    private AbilityEvents item3 = AbilityEvents.SPEED;
    private AbilityEvents item4 = AbilityEvents.RES;
    private AbilityEvents item5 = AbilityEvents.REGEN;
    private AbilityEvents item6 = AbilityEvents.WITHER;
    private AbilityEvents item7 = AbilityEvents.JUMP;

    @EventHandler
    public void onInvis(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item.isSimilar(p.getItemInHand()))
            return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Cooldowns.getAbilitycd().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            if (Cooldowns.getPocketbard().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name")).replace("%time%", Cooldowns.getPocketbard().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            Cooldowns.getPocketbard().applyCooldown(p, rAbilities.config.getConfig().getInt("PocketBard.PocketBard.Cooldown") * 1000);
            if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            }
            for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Invis.Message")) {
                p.sendMessage(chatUtil.chat(list));
            }
            AbilityEvents.takeItem(p, p.getItemInHand());
            p.updateInventory();
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, rAbilities.config.getConfig().getInt("PocketBard.Invis.PotionEffect.Time") * 23, rAbilities.config.getConfig().getInt("PocketBard.Invis.PotionEffect.Power") - 1));
        }
    }

    @EventHandler
    public void onSpeed(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item3.isSimilar(p.getItemInHand()))
            return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Cooldowns.getAbilitycd().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            if (Cooldowns.getPocketbard().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name")).replace("%time%", Cooldowns.getPocketbard().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            Cooldowns.getPocketbard().applyCooldown(p, rAbilities.config.getConfig().getInt("PocketBard.PocketBard.Cooldown") * 1000);
            if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            }
            for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Speed.Message")) {
                p.sendMessage(chatUtil.chat(list));
            }
            AbilityEvents.takeItem(p, p.getItemInHand());
            p.updateInventory();
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, rAbilities.config.getConfig().getInt("PocketBard.Speed.PotionEffect.Time") * 23, rAbilities.config.getConfig().getInt("PocketBard.Speed.PotionEffect.Power") - 1));
        }
    }

    @EventHandler
    public void onStrength(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item2.isSimilar(p.getItemInHand()))
            return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Cooldowns.getAbilitycd().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            if (Cooldowns.getPocketbard().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name")).replace("%time%", Cooldowns.getPocketbard().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            Cooldowns.getPocketbard().applyCooldown(p, rAbilities.config.getConfig().getInt("PocketBard.PocketBard.Cooldown") * 1000);
            if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            }
            for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Strength.Message")) {
                p.sendMessage(chatUtil.chat(list));
            }
            AbilityEvents.takeItem(p, p.getItemInHand());
            p.updateInventory();
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, rAbilities.config.getConfig().getInt("PocketBard.Strength.PotionEffect.Time") * 23, rAbilities.config.getConfig().getInt("PocketBard.Strength.PotionEffect.Power") - 1));
        }
    }

    @EventHandler
    public void onRegen(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item5.isSimilar(p.getItemInHand()))
            return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Cooldowns.getAbilitycd().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            if (Cooldowns.getPocketbard().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name")).replace("%time%", Cooldowns.getPocketbard().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            Cooldowns.getPocketbard().applyCooldown(p, rAbilities.config.getConfig().getInt("PocketBard.PocketBard.Cooldown") * 1000);
            if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            }
            for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Regen.Message")) {
                p.sendMessage(chatUtil.chat(list));
            }
            AbilityEvents.takeItem(p, p.getItemInHand());
            p.updateInventory();
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, rAbilities.config.getConfig().getInt("PocketBard.Regen.PotionEffect.Time") * 23, rAbilities.config.getConfig().getInt("PocketBard.Regen.PotionEffect.Power") - 1));
        }
    }

    @EventHandler
    public void onWither(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item6.isSimilar(p.getItemInHand()))
            return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Cooldowns.getAbilitycd().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            if (Cooldowns.getPocketbard().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name")).replace("%time%", Cooldowns.getPocketbard().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            Cooldowns.getPocketbard().applyCooldown(p, rAbilities.config.getConfig().getInt("PocketBard.PocketBard.Cooldown") * 1000);
            if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            }
            for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Wither.Message")) {
                p.sendMessage(chatUtil.chat(list));
            }
            AbilityEvents.takeItem(p, p.getItemInHand());
            p.updateInventory();
            for (Entity player2 : p.getNearbyEntities(rAbilities.config.getConfig().getInt("PocketBard.Wither.Distance"), rAbilities.config.getConfig().getInt("PocketBard.Wither.Distance"), rAbilities.config.getConfig().getInt("PocketBard.Wither.Distance"))) {
                ((Player) player2).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, rAbilities.config.getConfig().getInt("PocketBard.Wither.PotionEffect.Time") * 23, rAbilities.config.getConfig().getInt("PocketBard.Wither.PotionEffect.Power") - 1));
            }
        }
    }

    @EventHandler
    public void onRes(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item4.isSimilar(p.getItemInHand()))
            return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Cooldowns.getAbilitycd().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            if (Cooldowns.getPocketbard().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name")).replace("%time%", Cooldowns.getPocketbard().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            Cooldowns.getPocketbard().applyCooldown(p, rAbilities.config.getConfig().getInt("PocketBard.PocketBard.Cooldown") * 1000);
            if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            }
            for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Resistance.Message")) {
                p.sendMessage(chatUtil.chat(list));
            }
            AbilityEvents.takeItem(p, p.getItemInHand());
            p.updateInventory();
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, rAbilities.config.getConfig().getInt("PocketBard.Resistance.PotionEffect.Time") * 23, rAbilities.config.getConfig().getInt("PocketBard.Resistance.PotionEffect.Power") - 1));
        }
    }

    @EventHandler
    public void onJump(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item7.isSimilar(p.getItemInHand()))
            return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Cooldowns.getAbilitycd().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("ExoticBone.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            if (Cooldowns.getPocketbard().onCooldown(p)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name")).replace("%time%", Cooldowns.getPocketbard().getRemaining(p))));
                    event.setCancelled(true);
                    return;
                }
            }
            Cooldowns.getPocketbard().applyCooldown(p, rAbilities.config.getConfig().getInt("PocketBard.PocketBard.Cooldown") * 1000);
            if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            }
            for (String list : rAbilities.config.getConfig().getStringList("PocketBard.JumpBoost.Message")) {
                p.sendMessage(chatUtil.chat(list));
            }
            AbilityEvents.takeItem(p, p.getItemInHand());
            p.updateInventory();
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, rAbilities.config.getConfig().getInt("PocketBard.JumpBoost.PotionEffect.Time") * 23, rAbilities.config.getConfig().getInt("PocketBard.JumpBoost.PotionEffect.Power") - 1));
        }
    }
}
