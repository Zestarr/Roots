package com.zestarr.root.commands.Discord;

import com.zestarr.root.Roots;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import static com.zestarr.root.utils.ChatUtils.format;

public class DiscordListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        if (e.getChannel().getId().equals(Roots.getMainInstance().getConfig().getString("Discord.DiscordBridge.DiscordChannelID"))) {
            if (!e.getAuthor().isBot()) {
                Bukkit.broadcastMessage(format("&9[D] &7" + e.getAuthor().getName() + " &8» &f" + e.getMessage().getContentRaw()));
            }

        }

    }

}
