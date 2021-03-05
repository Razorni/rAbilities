package me.razorni.dev.items.abilities;

import me.razorni.dev.items.AbilityEvents;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import me.razorni.dev.cooldown.Cooldowns;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeLogger implements Listener {

    private Map<Villager, UUID> villagerMap = new HashMap<>();
    private Map<UUID, Long> cooldown = new HashMap<>();
    private AbilityEvents item = AbilityEvents.FAKELOGGER;

    @EventHandler
    public void clickCooldowns(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!item.isSimilar(event.getPlayer().getItemInHand()))
                return;
            if (Cooldowns.getFakelogger().onCooldown(event.getPlayer())) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    event.getPlayer().sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString
                            ("FakeLogger.Name")).replace("%time%", Cooldowns.getFakelogger().getRemaining(event.getPlayer()))));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onClickVillager(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().hasMetadata("logger")) {
            event.setCancelled(true);
            return;
        }
        if (event.getRightClicked() instanceof Villager) {
            if (event.getPlayer().getItemInHand().getType() == Material.MONSTER_EGG) {
                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        if (!event.getAction().name().contains("RIGHT"))
            return;
        if (item.isSimilar(event.getPlayer().getItemInHand())) {


            Player player = event.getPlayer();

            if (AbilityEvents.checkLocation(player.getLocation(), rAbilities.config.getConfig().getInt("SPAWN.Radius"), 0, 0)) {
                for (String list : rAbilities.config.getConfig().getStringList("SPAWN.CantUse")) {
                    player.sendMessage(chatUtil.chat(list));
                }
                event.setCancelled(true);
                return;
            }

            if (Cooldowns.getAbilitycd().onCooldown(player)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Ability.OnCooldown")) {
                    player.sendMessage(chatUtil.chat(list.replace("%time%", Cooldowns.getAbilitycd().getRemaining(player))));
                    event.setCancelled(true);
                    player.updateInventory();
                    return;
                }
            }

            if (Cooldowns.getFakelogger().onCooldown(player)) {
                for (String list : rAbilities.config.getConfig().getStringList("Message.Item.OnCooldown")) {
                    player.sendMessage(chatUtil.chat(list.replace("%ability%", rAbilities.config.getConfig().getString("FakeLogger.Name")).replace("%time%", Cooldowns.getFakelogger().getRemaining(player))));
                    event.setCancelled(true);
                    player.updateInventory();
                    return;
                }
            }

            double health = rAbilities.config.getConfig().getDouble("FakeLogger.Villager.Health");
            String name = rAbilities.config.getConfig().getString("FakeLogger.Villager.Name");
            int time = rAbilities.config.getConfig().getInt("FakeLogger.Player.Invis-Time");

            Villager villager = (Villager) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.VILLAGER);

            villager.setMetadata("logger", new FixedMetadataValue(rAbilities.getInstance(), player.getName()));
            villager.setVelocity(event.getPlayer().getVelocity());
            villager.setHealth(health);
            villager.setCustomName(chatUtil.chat(name.replaceAll("%player%", player.getName())));
            villager.setCustomNameVisible(true);
            villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 100));

            AbilityEvents.takeItem(player, player.getItemInHand());
            if (rAbilities.config.getConfig().getBoolean("Ability-Cooldown-Enabled")) {
                Cooldowns.getAbilitycd().applyCooldown(player, rAbilities.config.getConfig().getInt("Ability-Cooldown") * 1000);
            }
            Cooldowns.getFakelogger().applyCooldown(player, rAbilities.config.getConfig().getInt("FakeLogger.Cooldown") * 1000);
            cooldown.put(player.getUniqueId(), (long) (time * 1000));
            villagerMap.put(villager, player.getUniqueId());
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.hidePlayer(player);
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (cooldown.containsKey(player.getUniqueId())) {
                        player.sendMessage(chatUtil.chat("&cYour invis wore off."));
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.showPlayer(player);
                        }
                    }
                }
            }.runTaskLater(rAbilities.getInstance(), 20 * time);

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!villager.isDead()) {
                        if (cooldown.containsKey(player.getUniqueId())) {
                            player.sendMessage(chatUtil.chat("&cYou villager has despawned."));
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.showPlayer(player);
                            }
                            cooldown.remove(player.getUniqueId());
                            villagerMap.remove(villager);
                            villager.remove();
                            cooldown.remove(player.getUniqueId());
                        }
                    }
                }
            }.runTaskLater(rAbilities.getInstance(), 20 * 30);
            event.setCancelled(true);
        }

    }


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        if (!(event.getEntity() instanceof Villager))
            return;

        Villager villager = (Villager) event.getEntity();

        if (villager.hasMetadata("logger")) {
            if (villager.isDead()) {
                villager.remove();

                Player damaged = Bukkit.getPlayer(villagerMap.get(villager));
                if (cooldown.containsKey(damaged.getUniqueId())) {
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.showPlayer(damaged);
                    }
                    damaged.sendMessage(chatUtil.chat("&cYour decoy villager has died."));
                }
            }
        }
    }

}
