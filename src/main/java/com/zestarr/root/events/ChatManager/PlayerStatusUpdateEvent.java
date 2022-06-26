package com.zestarr.root.events.ChatManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.zestarr.root.utils.ChatUtils.format;

public class PlayerStatusUpdateEvent implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {

        Bukkit.broadcastMessage(format("&7[&a+&7] &7" + e.getPlayer().getDisplayName()));

    }

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent e) {

        Bukkit.broadcastMessage(format("&7[&c-&7] &7" + e.getPlayer().getDisplayName()));

    }


}
