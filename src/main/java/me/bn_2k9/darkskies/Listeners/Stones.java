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

        Integer potdur = 3*20;

        PotionEffect Nightvis = new PotionEffect(PotionEffectType.NIGHT_VISION, 13, 225, true, true, false);
        PotionEffect healthpot = new PotionEffect(PotionEffectType.HEALTH_BOOST, potdur, 0, true, true, false);
        PotionEffect Haste = new PotionEffect(PotionEffectType.FAST_DIGGING, potdur, 0, true, true, false);
        PotionEffect Speed = new PotionEffect(PotionEffectType.SPEED, potdur, 0, true, true, false);

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

                    if (play.getInventory().getItem(e).hasItemMeta()) {
                        if (play.getInventory().getItem(e).getItemMeta().getDisplayName().equals(DarkSkies.Colorcode(p.getConfig().getString("Stones.Speedstone")))) {
                            if (!play.getActivePotionEffects().contains(Speed)) {
                                if (Checktier(play, Checkifhasstone(play, p.getConfig().getString("Stones.Speedstone"))) == 1) {
                                    play.addPotionEffect(Speed);
                                }
                                if (Checktier(play, Checkifhasstone(play, p.getConfig().getString("Stones.Speedstone"))) == 2) {
                                    Speed = new PotionEffect(PotionEffectType.SPEED, potdur, 1, true, true, false);
                                    play.addPotionEffect(Speed);
                                }
                            }
                        }
                    }

                    if (play.getInventory().getItem(e).hasItemMeta()) {
                        if (play.getInventory().getItem(e).getItemMeta().getDisplayName().equals(DarkSkies.Colorcode(p.getConfig().getString("Stones.HasteStone")))) {
                            if (!play.getActivePotionEffects().contains(Haste)) {
                                if (Checktier(play, Checkifhasstone(play, p.getConfig().getString("Stones.HasteStone"))) == 1) {
                                    play.addPotionEffect(Haste);
                                }
                                if (Checktier(play, Checkifhasstone(play, p.getConfig().getString("Stones.HasteStone"))) == 2) {
                                    Haste = new PotionEffect(PotionEffectType.FAST_DIGGING, potdur, 1, true, true, false);
                                    play.addPotionEffect(Haste);
                                }
                                if (Checktier(play, Checkifhasstone(play, p.getConfig().getString("Stones.HasteStone"))) == 3) {
                                    Haste = new PotionEffect(PotionEffectType.FAST_DIGGING, potdur, 2, true, true, false);
                                    play.addPotionEffect(Haste);
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
