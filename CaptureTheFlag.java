package com.yourusername.gamemodesplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class CaptureTheFlag implements CommandExecutor, Listener {
    private final Main plugin;
    private boolean gameActive = false;
    private List<Player> teamRed = new ArrayList<>();
    private List<Player> teamBlue = new ArrayList<>();
    private final FlagManager flagManager;

    public CaptureTheFlag(Main plugin) {
        this.plugin = plugin;
        this.flagManager = new FlagManager();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("start")) {
            startGame();
            return true;
        } else if (args[0].equalsIgnoreCase("stop")) {
            stopGame();
            return true;
        }
        return false;
    }

    private void startGame() {
        if (gameActive) {
            Bukkit.broadcastMessage(ChatColor.RED + "Game already in progress.");
            return;
        }
        gameActive = true;
        Bukkit.broadcastMessage(ChatColor.GREEN + "Capture the Flag game started!");
    }

    private void stopGame() {
        if (!gameActive) {
            Bukkit.broadcastMessage(ChatColor.RED + "No game in progress.");
            return;
        }
        gameActive = false;
        Bukkit.broadcastMessage(ChatColor.GREEN + "Capture the Flag game ended!");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!gameActive) return;
        Player player = event.getPlayer();

        if (flagManager.isFlagCaptured(player)) {
            Bukkit.broadcastMessage(player.getName() + " captured the flag!");
            if (teamRed.contains(player)) {
                Bukkit.broadcastMessage(ChatColor.RED + "Red team scores!");
            } else if (teamBlue.contains(player)) {
                Bukkit.broadcastMessage(ChatColor.BLUE + "Blue team scores!");
            }
        }
    }
}
