package com.xdefcon.spigotping.tablist;

import com.xdefcon.spigotping.SpigotPing;
import com.xdefcon.spigotping.utils.PingUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PingTabList extends BukkitRunnable {
    private JavaPlugin plugin;

    public PingTabList(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void run() {
        for (Player player : this.plugin.getServer().getOnlinePlayers()) {
            String currentName;
            if (plugin.getConfig().getBoolean("tablist.show-real-name")) {
                currentName = player.getName();
            } else {
                currentName = player.getDisplayName();
            }
            String prefix = plugin.getConfig().getString("tablist.prefix");
            if (!prefix.equals("")) {
                player.setPlayerListName(ChatColor.translateAlternateColorCodes('&',
                        prefix.replace("%ping%", "" + PingUtil.getPing(player))) + " " + currentName);
            }
            String suffix = plugin.getConfig().getString("tablist.suffix");
            if (!suffix.equals("")) {
                player.setPlayerListName(currentName + " " + ChatColor.translateAlternateColorCodes('&',
                        suffix.replace("%ping%", "" + PingUtil.getPing(player))));
            }
        }
    }
}

