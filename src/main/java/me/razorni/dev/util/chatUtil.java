package me.razorni.dev.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class chatUtil {

    public static String chat(String message) {

        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static List<String> list(List<String> s){
        List<String> strings = new ArrayList<>();
        s.forEach(str -> strings.add(ChatColor.translateAlternateColorCodes('&', str)));
        return strings;
    }

    public static List<String> colorLines( List<String> lore) {
        List<String> color = new ArrayList<String>();
        for ( String s : lore) {
            color.add(chat(s));
        }
        return color;
    }
}