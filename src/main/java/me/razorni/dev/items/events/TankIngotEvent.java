package me.razorni.dev.items.events;

import me.razorni.dev.items.abilities.TankIngot;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TankIngotEvent {

    public static List<Player> getNearbyPlayers(Player player) {
        List<Player> valid = new ArrayList<>();
        for (Entity entity : player.getNearbyEntities(TankIngot.INGOT_RANGE, TankIngot.INGOT_RANGE / 2, TankIngot.INGOT_RANGE)) {
            if (entity instanceof Player) {
                Player nearbyPlayer = (Player) entity;
                valid.add(nearbyPlayer);
            }
        }
        return (valid);
    }
}
