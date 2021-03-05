package me.razorni.dev.items.abilities;

import me.razorni.dev.gui.pocketbard.PocketBardGUI;
import me.razorni.dev.items.AbilityEvents;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PocketBard implements Listener {

    private AbilityEvents item = AbilityEvents.POCKETBARD;

    @EventHandler

    public void onClick(PlayerInteractEvent event) {

        Player p = event.getPlayer();

        if (!event.getAction().name().contains("RIGHT"))
            return;

        if (!item.isSimilar(p.getItemInHand()))
            return;

        PocketBardGUI.openAbilityGUI(p);
    }
}
