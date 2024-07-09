package me.bn_2k9.darkskies.Listeners;

import me.bn_2k9.darkskies.DarkSkies;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Stones {

    static Plugin p = DarkSkies.getPlugin(DarkSkies.class);

    public static void CheckStones(Player play) {

        PotionEffect Nightvis = new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 225, true, true, false);
        PotionEffect healthpot = new PotionEffect(PotionEffectType.HEALTH_BOOST, -1, 0, true, true, false);

        play.removePotionEffect(healthpot.getType());
        play.removePotionEffect(Nightvis.getType());

        for (Integer e : p.getConfig().getIntegerList("Features.Stones.StoneSlots")) {

            Boolean proc = true;

            if (play.getInventory().getItem(e) != null) {
                if (play.getInventory().getItem(e).hasItemMeta()) {
                    if (play.getInventory().getItem(e).getItemMeta().hasLore()) {
                        if (!play.getInventory().getItem(e).getItemMeta().getLore().get(p.getConfig().getInt("Features.Stones.WaterMarkLine")).equals(DarkSkies.Colorcode(p.getConfig().getString("Features.Stones.WaterMarkLore")))) {
                            proc = false;
                        }
                    } else {
                        proc = false;
                    }
                } else {
                    proc = false;
                }

                if (proc) {


                    if (play.getInventory().getItem(e).hasItemMeta()) {
                        if (play.getInventory().getItem(e).getItemMeta().getDisplayName().equals(DarkSkies.Colorcode(p.getConfig().getString("Features.Stones.Nightvisstone")))) {
                            if (!play.getActivePotionEffects().contains(Nightvis)) {
                                play.addPotionEffect(Nightvis);
                            }
                        }
                    }

                    if (play.getInventory().getItem(e).hasItemMeta()) {
                        if (play.getInventory().getItem(e).getItemMeta().getDisplayName().equals(DarkSkies.Colorcode(p.getConfig().getString("Features.Stones.Healthstone")))) {
                            if (!play.getActivePotionEffects().contains(healthpot)) {
                                if (Checktier(play, Checkifhasstone(play, p.getConfig().getString("Features.Stones.Healthstone"))) == 1) {
                                    play.addPotionEffect(healthpot);
                                }
                                if (Checktier(play, Checkifhasstone(play, p.getConfig().getString("Features.Stones.Healthstone"))) == 2) {
                                    healthpot = new PotionEffect(PotionEffectType.HEALTH_BOOST, -1, 1, true, true, false);
                                    play.addPotionEffect(healthpot);
                                }
                                if (Checktier(play, Checkifhasstone(play, p.getConfig().getString("Features.Stones.Healthstone"))) == 3) {
                                    healthpot = new PotionEffect(PotionEffectType.HEALTH_BOOST, -1, 2, true, true, false);
                                    play.addPotionEffect(healthpot);
                                }
                            }
                        }
                    }



                }

            }
        }

    }

    public static Integer Checkifhasstone(Player p, String Itemname) {
        Plugin pl = DarkSkies.getPlugin(DarkSkies.class);


        Integer slot = -1;
        for (Integer e : pl.getConfig().getIntegerList("Features.Stones.StoneSlots")) {

            if (p.getInventory().getItem(e) != null) {
                if (p.getInventory().getItem(e).hasItemMeta()) {
                    if (p.getInventory().getItem(e).getItemMeta().hasLore()) {
                        if (!p.getInventory().getItem(e).getItemMeta().getLore().get(pl.getConfig().getInt("Features.Stones.WaterMarkLine")).equals(DarkSkies.Colorcode(pl.getConfig().getString("Features.Stones.WaterMarkLore")))) {
                            return -1;
                        }
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }

            if (p.getInventory().getItem(e).hasItemMeta()) {
                if (p.getInventory().getItem(e).getItemMeta().getDisplayName().equals(DarkSkies.Colorcode(Itemname))) {
                    slot = e;
                    return slot;
                }
                }
            }
        }
        return slot;
    }

    public static int Checktier(Player p, Integer slot) {
        Plugin pl = DarkSkies.getPlugin(DarkSkies.class);

        Integer tier = 0;

        tier = Integer.parseInt(p.getInventory().getItem(slot).getItemMeta().getLore().get(pl.getConfig().getInt("Features.Stones.TierLine")));
        Bukkit.getLogger().info(tier.toString());
        return tier;
    }





}
