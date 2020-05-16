package me.fenish.benimguzelevim.Events;

import me.fenish.benimguzelevim.Main;
import me.fenish.benimguzelevim.YamlCreator;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.inventory.ItemStack;

public class HomeGuiClick implements Listener {

    @EventHandler
    public void invClick(InventoryClickEvent e){
        if(e.getView().getTitle() == "§9Ev Menüsü"){
            e.setCancelled(true);
            if(e.getCurrentItem() != null){
                if(e.getCurrentItem().getType() == Material.LIME_BED) {
                    e.getWhoClicked().closeInventory();
                    String n = e.getCurrentItem().getItemMeta().getDisplayName().replace("§a", "").toLowerCase();
                    YamlCreator creator = new YamlCreator((Player) e.getWhoClicked());
                    FileConfiguration config = creator.getConfig();
                    Location loc = (Location) config.get(n);
                    Location pLoc1 = e.getWhoClicked().getLocation();
                    if (e.getClick().isLeftClick()) {
                        int coold = 3;
                        if (e.getWhoClicked().hasPermission("benimguzelevim.tier1")) {
                            coold = 2;
                        }
                        if (e.getWhoClicked().hasPermission("benimguzelevim.tier2")) {
                            coold = 1;
                        }

                        int[] timer = {0};
                        int finalCoold = coold;

                        e.getWhoClicked().sendMessage("§8[§aBGE§8] §e" + coold + " §7Saniye boyunca haraket etmeyiniz.");

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Location pLoc2 = e.getWhoClicked().getLocation();
                                if (pLoc1.getBlockX() == pLoc2.getBlockX() && pLoc1.getBlockY() == pLoc2.getBlockY() && pLoc1.getBlockZ() == pLoc2.getBlockZ()) {
                                    if (finalCoold - timer[0] != 0) {
                                        Main.getInstance().acbar.sendActionBar((Player) e.getWhoClicked(), "§7==§8[ §a" + (finalCoold - timer[0]) + " §8]§7==");
                                    }
                                    timer[0]++;
                                } else {
                                    cancel();
                                    Main.getInstance().acbar.sendActionBar((Player) e.getWhoClicked(), "§cHaraket ettiğiniz için ışınlanma iptal edildi.");
                                }
                                if (timer[0] == finalCoold + 1) {
                                    cancel();
                                    e.getWhoClicked().teleport(loc);
                                    ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 3, 1);
                                }

                            }
                        }.runTaskTimer(Main.getInstance(), 0, 20);
                    } else if (e.getClick().isRightClick()) {
                        Inventory inv = Bukkit.createInventory(null, 27, "§cLokasyon Sil?");
                        ItemStack red = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                        ItemStack onay = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
                        ItemStack konum = new ItemStack(Material.LIME_BED);

                        ItemMeta konummeta = konum.getItemMeta();
                        ItemMeta redmeta = red.getItemMeta();
                        ItemMeta onaymeta = onay.getItemMeta();

                        konummeta.setDisplayName(ChatColor.GREEN + n);
                        redmeta.setDisplayName("§cVazgeç");
                        onaymeta.setDisplayName("§aOnayla");

                        konum.setItemMeta(konummeta);
                        red.setItemMeta(redmeta);
                        onay.setItemMeta(onaymeta);

                        inv.setItem(11, onay);
                        inv.setItem(13, konum);
                        inv.setItem(15, red);

                        e.getWhoClicked().openInventory(inv);
                    }
                }
            }
        }
    }
}
