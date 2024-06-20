package me.bn_2k9.darkskies;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class DarkSkies extends JavaPlugin {

    Plugin p = this;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Stared!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        Bukkit.getLogger().info("Shutdown!");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return true;
    }

}
