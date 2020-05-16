package me.fenish.benimguzelevim.Commands;

import me.fenish.benimguzelevim.YamlCreator;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

public class MainCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("home")) {
                Player p = (Player) sender;
                YamlCreator creator = new YamlCreator(p);
                FileConfiguration config = creator.getConfig();
                Inventory inv = Bukkit.createInventory(null, 9, "§9Ev Menüsü");
                if (config != null) {
                    if (config.getKeys(false).size() != 0) {
                        for (Object key : config.getKeys(false)) {
                            if(config.getLocation(String.valueOf(key)).getWorld() == p.getWorld()) {
                                ItemStack item = new ItemStack(Material.LIME_BED);
                                ItemMeta meta = item.getItemMeta();
                                List<String> lore = new ArrayList<>();
                                DecimalFormat format = new DecimalFormat("0.#");
                                String coord = format.format(floor(p.getLocation().distance((Location) config.get(String.valueOf(key)))));

                                lore.add(" ");
                                lore.add("§e[SOL TIK] §7Konuma ışınlanır.");
                                lore.add("§c[SAĞ TIK] §7Konumu siler.");
                                lore.add(" ");
                                lore.add("§7Bu konumdan §a" + coord + "§7 Blok uzaktasın.");
                                meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(String.valueOf(key)));
                                meta.setLore(lore);
                                item.setItemMeta(meta);
                                inv.addItem(item);
                            }

                        }
                    }
                    else {
                        ItemStack empty = new ItemStack(Material.PAPER);
                        ItemMeta meta = empty.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore.add("§8=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                        lore.add("§7Şuanda hiç eviniz bulunmuyor.");
                        lore.add("§7Ev eklemek için §a/sethome <isim>");
                        lore.add("§7Daha sonra eviniz bu menüde gözükecektir.");
                        lore.add("§8=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                        meta.setLore(lore);
                        meta.setDisplayName("§b? §7Bilgi §b?");
                        empty.setItemMeta(meta);
                        inv.setItem(8, empty);
                    }

                    p.openInventory(inv);
                }
                else{
                    p.sendMessage("§8[§aBGE§8] §cBir hata oluştu yetkililer ile iletişime geçiniz.");
                }
            }
        }else{
            System.out.println("§8[§bBGE§8] §cBu komutu oyuncular kullanır.");
        }
        return true;
    }
}

