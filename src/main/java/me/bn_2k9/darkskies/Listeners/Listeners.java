package me.bn_2k9.darkskies.Listeners;

import me.bn_2k9.darkskies.DarkSkies;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Random;

public class Listeners implements Listener {

    static Plugin pl = DarkSkies.getPlugin(DarkSkies.class);
    @EventHandler
    public void OnPlayerFish(PlayerFishEvent e) {

        if (e.getCaught() instanceof Item) {

            if (pl.getConfig().contains("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()) ) {

                if (pl.getConfig().getInt("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() + ".CustomModelData")  != -1) {

                    if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == pl.getConfig().getInt("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() + ".CustomModelData")) {
                           if (pl.getConfig().contains("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() + ".LootTable"))) {
                                List Loot = pl.getConfig().getList("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() + ".LootTable"));
                                if (Loot.isEmpty()) {

                                    Random ran = new Random();
                                    int value = ran.nextInt(Loot.size());

                                    String mat = Loot.get(value).toString().toUpperCase();
                                    ItemStack Item = new ItemStack(Material.valueOf(mat));

                                    if (pl.getConfig().getString("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() + ".LootTable") + "."+ mat + ".ItemName") != null) {
                                        Item.getItemMeta().setDisplayName(pl.getConfig().getString("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() + ".LootTable") + "."+ mat + ".ItemName"));
                                    }
                                    if (pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() + ".LootTable") + "."+ mat + ".CustomModelData") != 0) {
                                        Item.getItemMeta().setCustomModelData(pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() + ".LootTable") + "."+ mat + ".ItemName"));
                                    }
                                }
                           }

                        }

                    }
                } else {

                }


            }

        }

    }

    public static void GiveLoot() {

    }

}
