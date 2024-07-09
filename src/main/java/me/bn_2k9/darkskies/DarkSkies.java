package me.bn_2k9.darkskies;

import me.bn_2k9.darkskies.Listeners.Listeners;
import me.bn_2k9.darkskies.Listeners.Stones;
import me.bn_2k9.darkskies.Methods.Backpack;
import me.bn_2k9.darkskies.Methods.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public final class DarkSkies extends JavaPlugin {

    Plugin p = this;
    @Override
    public void onEnable() {
        // Plugin startup logic
        p.getServer().getPluginManager().registerEvents(new Listeners(), this);

        p.saveDefaultConfig();

        Bukkit.getLogger().info(Colorcode(p.getConfig().getString("Plugin.Prefix")) + " Started");

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Stones.CheckStones(player);
                }
            }
        }.runTaskTimer(this, 20L * 10L /*<-- the initial delay */, 20L * 2L /*<-- the interval */);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        Bukkit.getLogger().info(Colorcode(p.getConfig().getString("Plugin.Prefix")) + " Shutdown");

    }
    // Listens to all the commands
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            // The command listener for the backpack.
            if (command.getName().equals("Backpack")) {

                Backpack.openBackpack(sender.getServer().getPlayer(sender.getName()));

            }

            if (command.getName().equals("dhelp")) {
                if (sender.hasPermission(p.getConfig().getString("Features.HelpOverride.StaffPermission"))) {
                    Help("Features.HelpOverride.StaffHelp", sender);
                } else {
                    Help("Features.HelpOverride.PlayerHelp", sender);
                }
            }

            if (command.getName().equals("setcustommodeldata")) {
                if (args.length != 0) {
                    ItemMeta i = ((Player) sender).getPlayer().getInventory().getItemInMainHand().getItemMeta();
                    i.setCustomModelData(Integer.parseInt(args[0]));
                    ((Player) sender).getPlayer().getInventory().getItemInMainHand().setItemMeta(i);
                    sender.sendMessage("Added To The Item");
                } else {
                    sender.sendMessage("Please Provide an number");
                }
            }
        }

        return true;
    }

    public void Help(String l, CommandSender send) {
        for (String s : p.getConfig().getStringList(l)) {
            send.sendMessage(Colorcode(s));
        }

    }

    public static String Colorcode (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
