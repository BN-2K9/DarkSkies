package me.bn_2k9.darkskies.Listeners;

import me.bn_2k9.darkskies.DarkSkies;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Random;

public class Listeners implements Listener {

    static Plugin pl = DarkSkies.getPlugin(DarkSkies.class);


    public void OnPlayerCommandpreprocces(PlayerCommandPreprocessEvent e) {
        if (pl.getConfig().getBoolean("Features.HelpOverride.Enabled")) {
            if (e.getMessage().equals("/help") || e.getMessage().equals("/?")) {
                e.setMessage("/dhelp");
            }
        }
    }



    @EventHandler
    public void OnPlayerFish(PlayerFishEvent e) {

        if (pl.getConfig().getBoolean("Features.Fishing.Enabled")) {

        }
        String nameone = e.getPlayer().getInventory().getItemInMainHand().getType().name().toLowerCase().replace("_", " ");
        String Finished = WordUtils.capitalizeFully(nameone);
        Finished = ChatColor.stripColor(Finished);

        if (e.getCaught() instanceof Item) {

            if (pl.getConfig().contains("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) ) {

                if (pl.getConfig().contains("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) ) {
                    Finished = pl.getConfig().getString("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                    Finished = ChatColor.stripColor(Finished);
                }

                if (pl.getConfig().getInt("Features.Fishing.Tools." + Finished + ".CustomModelData")  != -1) {

                    if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == pl.getConfig().getInt("Features.Fishing.Tools." + Finished + ".CustomModelData")) {
                            if (pl.getConfig().contains("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + Finished + ".LootTable"))) {
                                GiveLoot(e, Finished);
                                e.setCancelled(true);
                                e.getHook().remove();
                            }

                        }

                    }
                }
            } else {
                GiveLoot(e, Finished);
                e.setCancelled(true);
                e.getHook().remove();
            }

        }

    }

    public static void GiveLoot(PlayerFishEvent e, String s) {

        Bukkit.getLogger().info(pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable"));
        List Loot = pl.getConfig().getList("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable"));
        if (Loot != null) {

            Random ran = new Random();
            int value = ran.nextInt(Loot.size());

            String Num = Loot.get(value).toString().replace("{", "").replace("=", " ").split(" ")[0];
            String mat = pl.getConfig().getString("Features.Fishing.LootTables.Common." + Num + ".Material");

            Bukkit.getLogger().info("Features.Fishing.LootTables.Common." + Num + ".Material");
            Bukkit.getLogger().info(Num);
            Bukkit.getLogger().info(mat);

            ItemStack Item = new ItemStack(Material.valueOf(mat));

            if (pl.getConfig().getString("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".ItemName") != null) {
                Bukkit.getLogger().info("Added Name");
                ItemMeta Im = Item.getItemMeta();

                Im.setDisplayName(DarkSkies.Colorcode(pl.getConfig().getString("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".ItemName")));

                Item.setItemMeta(Im);
            }
            if (pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".CustomModelData") != -1) {
                Bukkit.getLogger().info("Added data");
                ItemMeta Im = Item.getItemMeta();
                Im.setCustomModelData(pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "." + Num + ".ItemName"));

                Item.setItemMeta(Im);
            }
            if (pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".Durability") != -1) {

            }
            e.getPlayer().getInventory().addItem(Item);

        }
    }

}
