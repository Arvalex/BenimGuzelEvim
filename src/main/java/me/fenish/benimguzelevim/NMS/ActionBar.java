package me.fenish.benimguzelevim.NMS;

import me.fenish.benimguzelevim.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ActionBar {
    Main pl;
    public ActionBar(){
        pl = Main.getInstance();
    }

    public void sendActionBar(Player p, String s){
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(s));
    }
}
