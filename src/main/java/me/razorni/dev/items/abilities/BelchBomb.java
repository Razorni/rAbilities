package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.items.events.BelchBombEvent;
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
import me.razorni.dev.cooldown.Cooldowns;

import java.util.List;

public class BelchBomb implements Listener {

    private AbilityEvents item = AbilityEvents.BELCHBOMB;
    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getBelchbomb().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("BelchBomb.Name")).replace("%time%", Cooldowns.getBelchbomb().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
    @EventHandler
    public void onWither(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item.isSimilar(p.getItemInHand()))
            return;

        if (Cooldowns.getAbilitycd().onCooldown(p)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("BelchBomb.Name")).replace("%time%", Cooldowns.getAbilitycd().getRemaining(p))));
                event.setCancelled(true);
                return;
            }
        }
        if (Cooldowns.getBelchbomb().onCooldown(p)) {
            for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                p.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("BelchBomb.Name")).replace("%time%", Cooldowns.getBelchbomb().getRemaining(p))));
                event.setCancelled(true);
                return;
            }
        }

        if (BelchBombEvent.getNearbyPlayers(p).isEmpty()) {
            for (String list : rAbilities.config.getConfig().getStringList("BelchBomb.Message.None-Near")) {
                p.sendMessage(chatUtil.chat(list));
            }
            event.setCancelled(true);
            p.updateInventory();
        }

        Cooldowns.getBelchbomb().applyCooldown(p, rAbilities.config.getConfig().getInt("BelchBomb.Cooldown") * 1000);
        if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
            Cooldowns.getAbilitycd().applyCooldown(p, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
        }

        for (String list : rAbilities.config.getConfig().getStringList("BelchBomb.Message.Used")) {
            p.sendMessage(chatUtil.chat(list));
        }
        AbilityEvents.takeItem(p, p.getItemInHand());
        p.updateInventory();

        List<Entity> nearby = p.getNearbyEntities(BelchBombEvent.BELCH_RANGE, BelchBombEvent.BELCH_RANGE, BelchBombEvent.BELCH_RANGE);
        for (Entity entity : nearby) {
            if (entity instanceof Player) {
                Player players = ((Player) entity).getPlayer();

                for (String section : rAbilities.config.getConfig().getConfigurationSection("BelchBomb.Effects").getKeys(false)) {

                    int power = rAbilities.config.getConfig().getInt("BelchBomb.Effects." + section + ".Power");
                    int time = rAbilities.config.getConfig().getInt("BelchBomb.Effects." + section + ".Time");
                    String type = rAbilities.config.getConfig().getString("BelchBomb.Effects." + section + ".Type");

                    players.addPotionEffect(new PotionEffect(PotionEffectType.getByName(type), time * 20, power - 1));

                }

                for (String list : rAbilities.config.getConfig().getStringList("BelchBomb.Message.Been-Blinded")) {
                    players.sendMessage(chatUtil.chat(list).replaceAll("%player%", p.getName()));
                }
            }
        }
    }

}
