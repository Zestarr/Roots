package com.zestarr.root;

import com.zestarr.root.commands.*;
import com.zestarr.root.commands.gamemodes.AdventureCommand;
import com.zestarr.root.commands.gamemodes.CreativeCommand;
import com.zestarr.root.commands.gamemodes.SpectatorCommand;
import com.zestarr.root.commands.gamemodes.SurvivalCommand;
import com.zestarr.root.utils.PlayerDataAPI;
import org.bukkit.plugin.java.JavaPlugin;

import static com.zestarr.root.utils.ChatUtils.log;

public final class Roots extends JavaPlugin {

    private static Roots mainInstance;
    public static PlayerDataAPI api;

    @Override
    public void onEnable() {
        mainInstance = this;
        api = new PlayerDataAPI(this);

        log("Root Commands is now enabled");
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        if (getConfig().getBoolean("CustomCommands")) {
            if (getConfigBoolean("Commands.Feed")) { new FeedCommand(); }
            if (getConfigBoolean("Commands.Kill")) { new KillCommand(); }
            if (getConfigBoolean("Commands.Fly")) { new FlyCommand(); }
            if (getConfigBoolean("Commands.Heal")) { new HealCommand(); }
            if (getConfigBoolean("Commands.Sudo")) { new SudoCommand(); }
            if (getConfigBoolean("Commands.God")) { new GodCommand(); }
            if (getConfigBoolean("Commands.Gamemode.Survival")) { new SurvivalCommand(); }
            if (getConfigBoolean("Commands.Gamemode.Creative")) { new CreativeCommand(); }
            if (getConfigBoolean("Commands.Gamemode.Adventure")) { new AdventureCommand(); }
            if (getConfigBoolean("Commands.Gamemode.Spectator")) { new SpectatorCommand(); }
        }


    }

    @Override
    public void onDisable() {
        log("Root Commands is now disabled");
    }

    public static Roots getMainInstance() { return mainInstance; }

    private boolean getConfigBoolean(String path) {
        return getConfig().getBoolean(path);
    }
}
