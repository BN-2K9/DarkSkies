package me.bn_2k9.darkskies.Listeners;

import me.bn_2k9.darkskies.DarkSkies;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

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
