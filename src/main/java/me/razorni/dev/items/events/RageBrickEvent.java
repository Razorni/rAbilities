package me.razorni.dev.items.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import me.razorni.dev.items.abilities.RageBrick;

import java.util.ArrayList;
import java.util.List;

public class RageBrickEvent {
    public static List<Player> getNearbyPlayers(Player player) {
        List<Player> valid = new ArrayList<>();
        for (Entity entity : player.getNearbyEntities(RageBrick.BRICK_RANGE, RageBrick.BRICK_RANGE / 2, RageBrick.BRICK_RANGE)) {
            if (entity instanceof Player) {
                Player nearbyPlayer = (Player) entity;
                valid.add(nearbyPlayer);
            }
        }
        return (valid);
    }
}
