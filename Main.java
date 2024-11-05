package com.yourusername.gamemodesplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private CaptureTheFlag ctfGame;

    @Override
    public void onEnable() {
        ctfGame = new CaptureTheFlag(this);
        this.getCommand("ctf").setExecutor(ctfGame);
        getServer().getPluginManager().registerEvents(ctfGame, this);
        getLogger().info("Game Modes Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Game Modes Plugin Disabled.");
    }
}
