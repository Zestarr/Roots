package com.zestarr.root;

import com.zestarr.root.commands.*;
import com.zestarr.root.commands.Discord.BotControl;
import com.zestarr.root.commands.Discord.DiscordListener;
import com.zestarr.root.commands.gamemodes.AdventureCommand;
import com.zestarr.root.commands.gamemodes.CreativeCommand;
import com.zestarr.root.commands.gamemodes.SpectatorCommand;
import com.zestarr.root.commands.gamemodes.SurvivalCommand;
import com.zestarr.root.events.ChatManager.ChatEvent;
import com.zestarr.root.events.ChatManager.PlayerStatusUpdateEvent;
import com.zestarr.root.utils.PlayerDataAPI;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

import static com.zestarr.root.utils.ChatUtils.log;

public class Roots extends JavaPlugin {

    private static Roots mainInstance;
    public static PlayerDataAPI api;
    private static JDA jda;

    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    @Override
    public void onEnable() {

        log("Roots (Commands) is now enabled");
        mainInstance = this;
        api = new PlayerDataAPI(this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        new RootsCommand();

        JDABuilder builder = JDABuilder.createDefault(getConfig().getString("Discord.DiscordBridge.Token"));

        builder.setActivity(Activity.playing("Soul Network S3"));
        builder.addEventListeners(new DiscordListener());

        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        try {
            jda.awaitReady();
            log("JDA Bot is ready!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




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
            if (getConfigBoolean("Commands.ClearChat")) { new ClearChatCommand(); }
            if (getConfigBoolean("Commands.StaffChat")) { new StaffChatCommand(); }


        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerStatusUpdateEvent(), this);

            RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();

        }

        if (!setupEconomy() ) {
            log(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            //getServer().getPluginManager().disablePlugin(this);
            return;
        }

        setupPermissions();
        setupChat();








        }


    }

    @Override
    public void onDisable() {
        log("Roots (Commands) is now disabled");

        getJda().shutdownNow();
        while (getJda().getStatus() != JDA.Status.SHUTDOWN) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static Roots getMainInstance() { return mainInstance; }

    public boolean getConfigBoolean(String path) {
        return getConfig().getBoolean(path);
    }

    public static JDA getJda() {
        return jda;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

}
