package me.bn_2k9.darkskies.Methods;

import me.bn_2k9.darkskies.DarkSkies;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Backpack {
    static Plugin pl = DarkSkies.getPlugin(DarkSkies.class);
    static String prefix = DarkSkies.Colorcode(pl.getConfig().getString("Plugin.Prefix"));
    public static void openBackpack(Player p) {
        if (p.getInventory().getItemInOffHand().getType() != Material.AIR) {
            if (p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                if (p.getInventory().getItemInOffHand().getType() == Material.PAPER &&  pl.getConfig().getIntegerList("Features.Backpack.CustomModelData").contains(p.getInventory().getItemInOffHand().getItemMeta().getCustomModelData())){
                    p.openInventory(p.getEnderChest());
                    return;
                }
            }
        }
        p.sendMessage(prefix + DarkSkies.Colorcode(pl.getConfig().getString("Messages.No-BackPack")));
    }

}
