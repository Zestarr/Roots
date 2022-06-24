// Copied over from https://github.com/SimplyMerlin/Fiber/

package com.zestarr.root.utils;

import com.zestarr.root.Roots;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static void send(CommandSender receiver, String message) { receiver.sendMessage(format(message)); }

    public static String format(String s) { return ChatColor.translateAlternateColorCodes('&', s); }

    public static void log(String message) { send(Bukkit.getConsoleSender(), message); }

    public static void sendFromServer(String s, Player player) { player.sendMessage(format( Roots.getMainInstance().getConfig().getString("ServerString") + " &8» " + s)); }


}