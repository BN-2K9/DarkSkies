package me.bn_2k9.darkskies;

import me.bn_2k9.darkskies.Methods.Backpack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class DarkSkies extends JavaPlugin {

    Plugin p = this;
    @Override
    public void onEnable() {
        // Plugin startup logic

        p.saveDefaultConfig();

        Bukkit.getLogger().info("Stared!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        Bukkit.getLogger().info("Shutdown!");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equals("Backpack")) {

                Backpack.openBackpack(sender.getServer().getPlayer(sender.getName()));

            }
        }

        return true;
    }

    public static String Colorcode (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
