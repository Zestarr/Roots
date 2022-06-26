package com.zestarr.root.commands;

import com.zestarr.root.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ClearChatCommand extends CommandManager{


    public ClearChatCommand() {
        super(
                "clearchat",
                new String[]{"cc"},
                "Clears the chat",
                "roots.clearchat"
        );

    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        for (int i = 0; i < 300; i++) {
            Bukkit.broadcastMessage("    ");
        }

        Bukkit.broadcastMessage("\n ");
        ChatUtils.serverBroadcast("&cChat has been cleared");
        Bukkit.broadcastMessage("\n ");

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
