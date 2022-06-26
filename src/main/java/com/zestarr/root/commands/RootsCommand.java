package com.zestarr.root.commands;

import com.zestarr.root.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RootsCommand extends CommandManager {

    public RootsCommand() {
        super(
                "Roots",
                new String[]{},
                "Explains Roots",
                "");



    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof Player) {
            ChatUtils.sendFromServer("&7Commands that are heavily demanded, without all the side effects.", (Player) sender);
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
