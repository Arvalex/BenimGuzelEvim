package me.fenish.benimguzelevim.Events;

import me.fenish.benimguzelevim.YamlCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DeleteGuiClick implements Listener {
    @EventHandler
    public void invClick(InventoryClickEvent e){
        if(e.getView().getTitle() == "§cLokasyon Sil?"){
            e.setCancelled(true);
            if(e.getCurrentItem() != null){
                if(e.getCurrentItem().getType() == Material.LIME_STAINED_GLASS_PANE){
                    e.getWhoClicked().closeInventory();
                    String n = e.getView().getItem(13).getItemMeta().getDisplayName().replace("§a", "").toLowerCase();
                    YamlCreator creator = new YamlCreator((Player) e.getWhoClicked());
                    FileConfiguration config = creator.getConfig();
                    creator.set(n,null);
                    creator.save();
                    Bukkit.getServer().dispatchCommand(e.getWhoClicked(), "home");
                }
                else if(e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE){
                    e.getWhoClicked().closeInventory();
                    Bukkit.getServer().dispatchCommand(e.getWhoClicked(), "home");
                }
            }
        }
    }
}
