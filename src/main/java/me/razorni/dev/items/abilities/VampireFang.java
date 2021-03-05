package me.razorni.dev.items.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.Map;

public class VampireFang implements Listener {

    private Map<Player, Long> cooldown = new HashMap<>();
    private Map<Player, Player> playerMap = new HashMap<>();

    @EventHandler
    public void onHitVampire(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                Player damaged = (Player) event.getEntity();

                if (damager.getItemInHand().getType() == Material.SPIDER_EYE) {
                    playerMap.put(damaged, damager);
                    damager.sendMessage("test2");
                    cooldown.put(damaged, (long) (30 * 1000));
                }

            }
        }

    }

    @EventHandler
    public void onDamagedwhileVampire(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getCause() == EntityDamageEvent.DamageCause.CUSTOM) {
                Player player = (Player) event.getEntity();
                Player damager = Bukkit.getPlayer(playerMap.get(player).getName());

                if (cooldown.containsKey(player)) {
                    damager.setHealth(damager.getHealth() + event.getFinalDamage());
                    damager.sendMessage("test");
                }

            }
        }
    }

}
