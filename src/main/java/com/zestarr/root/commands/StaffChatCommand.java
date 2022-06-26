package com.zestarr.root.commands;

import com.zestarr.root.commands.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static com.zestarr.root.utils.ChatUtils.format;
import static com.zestarr.root.utils.ChatUtils.sendFromServer;

public class StaffChatCommand extends CommandManager {

    public StaffChatCommand() {
        super(
                "staffchat",
                new String[]{"sc"},
                "Sends a message to the staff chat",
                "roots.staffchat"
                );
    }


    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof Player) {

            if (args.length > 0) {
                StringBuilder message = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    String arg = args[i] + " ";
                    message.append(arg);

                }

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("roots.staffchat")) {
                        player.sendMessage(format("&7[&cSC&7] " + ((Player) sender).getDisplayName() + " " + message));

                    }

                }


            } else {
                sendFromServer("&cPlease follow the format: /staffchat (message) or /sc (message)", (Player) sender);
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
