package me.bn_2k9.darkskies.Listeners;

import me.bn_2k9.darkskies.DarkSkies;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Listeners implements Listener {

    static Plugin pl = DarkSkies.getPlugin(DarkSkies.class);

    @EventHandler
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
            String nameone = e.getPlayer().getInventory().getItemInMainHand().getType().name().toLowerCase().replace("_", " ");
            String Finished = WordUtils.capitalizeFully(nameone);
            Finished = ChatColor.stripColor(Finished);

            if (e.getCaught() instanceof Item) {
                Bukkit.getLogger().info("p");
                if (pl.getConfig().contains("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) ) {
                    Bukkit.getLogger().info(Finished);
                    if (pl.getConfig().contains("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) ) {
                        Finished = pl.getConfig().getString("Features.Fishing.Tools." + e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                        Finished = ChatColor.stripColor(Finished);
                        Bukkit.getLogger().info(Finished);
                    }


                    if (pl.getConfig().getInt("Features.Fishing.Tools." + Finished + ".CustomModelData")  != -1) {

                        if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == pl.getConfig().getInt("Features.Fishing.Tools." + Finished + ".CustomModelData")) {
                                if (pl.getConfig().contains("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + Finished + ".LootTable"))) {
                                    if (pl.getConfig().contains("Features.Fishing.Tools." + Finished)) {
                                        Bukkit.getLogger().info(Finished);
                                        GiveLoot(e, Finished);
                                        e.setCancelled(pl.getConfig().getBoolean("Features.Fishing.RemoveVanillaLoot"));
                                        e.getHook().remove();
                                    }
                                }

                            }

                        }
                    }
                } else {
                    if (pl.getConfig().contains("Features.Fishing.Tools." + Finished)) {
                        Bukkit.getLogger().info(Finished);
                        GiveLoot(e, Finished);
                        e.setCancelled(pl.getConfig().getBoolean("Features.Fishing.RemoveVanillaLoot"));
                        e.getHook().remove();
                    }
                }
            }
        }

    }

    @EventHandler
    public void falldamage(EntityDamageEvent e) {

        Plugin pl = DarkSkies.getPlugin(DarkSkies.class);

        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player p = ((Player) e.getEntity()).getPlayer();
            if (Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.FallprotStone")) != -1) {
                if (Stones.Checktier(p, Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.FallprotStone"))) == 1) {
                    e.setDamage(e.getDamage() / 100 * 75);
                }
                if (Stones.Checktier(p, Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.FallprotStone"))) == 2) {
                    e.setDamage(e.getDamage() / 100 * 50);
                }
                if (Stones.Checktier(p, Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.FallprotStone"))) == 3) {
                    e.setDamage(e.getDamage() / 100 * 25);
                }
                if (Stones.Checktier(p, Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.FallprotStone"))) == 4) {
                    e.setCancelled(true);
                }
            }
        }
        if (e.getEntity() instanceof Player) {
            if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                Player p = ((Player) e.getEntity()).getPlayer();
                if (Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.FireProtStone")) != -1) {
                    if (Stones.Checktier(p, Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.FireProtStone"))) == 1) {
                        e.setDamage(e.getDamage() / 100 * 50);
                    }
                    if (Stones.Checktier(p, Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.FireProtStone"))) == 2) {
                        e.setDamage(e.getDamage() / 100 * 25);
                    }
                }
            }
        }

        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
            Player p = ((Player) e.getEntity()).getPlayer();
            if (Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.FireProtStone")) != -1) {
                if (Stones.Checktier(p, Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.FireProtStone"))) == 3) {
                    e.setCancelled(true);
                }
            }
        }


    }

    @EventHandler
    public void hunger(FoodLevelChangeEvent e){
        Plugin pl = DarkSkies.getPlugin(DarkSkies.class);
        Player p = ((Player) e.getEntity()).getPlayer();
        if (Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.Foodstone")) != -1) {
            if (Stones.Checktier(p, Stones.Checkifhasstone(p, pl.getConfig().getString("Stones.Foodstone"))) == 1) {
                if (e.getFoodLevel() <= 14) {
                    Bukkit.getLogger().info(p.getName());
                    ItemStack i = new ItemStack(Material.BREAD, 1);
                    if (p.getInventory().contains(Material.BREAD)) {
                        consumeItem(p, 1, Material.BREAD);
                        e.setFoodLevel(e.getFoodLevel() + 6);
                    }
                }
            }
        }
    }

    public boolean consumeItem(Player player, int count, Material mat) {
        Map<Integer, ? extends ItemStack> ammo = player.getInventory().all(mat);

        int found = 0;
        for (ItemStack stack : ammo.values())
            found += stack.getAmount();
        if (count > found)
            return false;

        for (Integer index : ammo.keySet()) {
            ItemStack stack = ammo.get(index);

            int removed = Math.min(count, stack.getAmount());
            count -= removed;

            if (stack.getAmount() == removed)
                player.getInventory().setItem(index, null);
            else
                stack.setAmount(stack.getAmount() - removed);

            if (count <= 0)
                break;
        }

        player.updateInventory();
        return true;
    }

    public static void GiveLoot(PlayerFishEvent e, String s) {

        Bukkit.getLogger().info(pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable"));
        List Loot = pl.getConfig().getConfigurationSection("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable")).getKeys(false).stream().toList();
        if (Loot != null) {
            Bukkit.getLogger().info("Not null");
            Random ran = new Random();
            int value = ran.nextInt(Loot.size());

            String Num = Loot.get(value).toString().replace("{", "").replace("=", " ").split(" ")[0];
            String mat = pl.getConfig().getString("Features.Fishing.LootTables.Common." + Num + ".Material");

            Bukkit.getLogger().info(mat);

            ItemStack Item = new ItemStack(Material.valueOf(mat));

            if (pl.getConfig().contains("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".ItemName")) {
                if (pl.getConfig().getString("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".ItemName") != null) {
                    ItemMeta Im = Item.getItemMeta();

                    Im.setDisplayName(DarkSkies.Colorcode(pl.getConfig().getString("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".ItemName")));

                    Item.setItemMeta(Im);
                }
            }
            // adds CustomModelData
            if (pl.getConfig().contains("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".CustomModelData")) {
                if (pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".CustomModelData") != -1) {
                    ItemMeta Im = Item.getItemMeta();
                    Im.setCustomModelData(pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "." + Num + ".ItemName"));

                    Item.setItemMeta(Im);
                }
            }
            // applies damage
            if (pl.getConfig().contains("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".Durability")) {
                if (pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".Durability") != -1) {
                    ItemMeta Im = Item.getItemMeta();
                    ((Damageable) Im).setDamage(Material.getMaterial(mat).getMaxDurability() - pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".Durability"));

                    Item.setItemMeta(Im);
                }
            }
            // sets the amount of items a index has
            if (pl.getConfig().contains("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".Amount")) {
                if (pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".Amount") != 1) {
                    Item.setAmount((pl.getConfig().getInt("Features.Fishing.LootTables." + pl.getConfig().getString("Features.Fishing.Tools." + s + ".LootTable") + "."+ Num + ".Amount")));
                }
            }

            if (pl.getConfig().getInt("Features.Fishing.ChanceOfGettingAnyLoot") != -1) {
                Random ran2 = new Random();
                int value2 = ran2.nextInt(Loot.size());
                if (value2 == 0) {
                    e.getPlayer().getInventory().addItem(Item);
                    e.getPlayer().sendMessage(DarkSkies.Colorcode(pl.getConfig().getString("Messages.GetItem").replace("%ITEM%", Item.getItemMeta().getDisplayName())) );
                }
            } else {
                e.getPlayer().getInventory().addItem(Item);
            }

        }
    }

}
