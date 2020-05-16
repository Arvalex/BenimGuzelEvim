package me.fenish.benimguzelevim.Commands;

import me.fenish.benimguzelevim.YamlCreator;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SethomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(command.getName().equalsIgnoreCase("sethome")) {
                Player p = (Player) sender;
                if (args.length == 1) {
                    YamlCreator creator  = new YamlCreator(p);
                    FileConfiguration config = creator.getConfig();
                    int MaxSize = 1;
                    if(p.hasPermission("benimguzelevim.tier1")){ MaxSize = 2;}
                    if(p.hasPermission("benimguzelevim.tier2")){ MaxSize = 3;}

                    if(config.getKeys(false).size() < MaxSize) {
                        if(config.get(args[0].toLowerCase()) == null) {
                            if(args[0].length() < 10) {
                                creator.set(args[0].toLowerCase(), p.getLocation());
                                creator.save();
                                p.sendMessage("§8[§aBGE§8] §a" + StringUtils.capitalize(args[0]) + "§7 Konumu kaydedildi.");
                            }else{
                                p.sendMessage("§8[§aBGE§8] §cBir hata oluştu\n" +
                                        "§8[§aBGE§8] §7Yer ismi maksimum 10 karakter olmalıdır!");
                            }
                       }
                        else{
                            p.sendMessage("§8[§aBGE§8] §cBir hata oluştu\n" +
                                    "§8[§aBGE§8] §7Bu isimde bir konum zaten kayıtlı!");
                        }
                    }else{
                        p.sendMessage("§8[§aBGE§8] §7Maksimum §c" + MaxSize + "§7 Konum belirleyebilirsiniz.\n" +
                                "§8[§aBGE§8] §7Konum hakkı kazanmak için hesabınızı yükseltebilirsiniz.\n" +
                                "§8[§aBGE§8] §7Detayli bilgi için §9Discord Sunucumuza§7 katılın.");
                    }
                } else {
                    p.sendMessage("§8[§aBGE§8] §cHatalı bir kullanım yaptınız\n" +
                            "§8[§aBGE§8] §7/Sethome §e<isim>\n" +
                            "§8[§aBGE§8] §7Örnek: §e/sethome Ev");
                }
            }
        }else{System.out.println("§8[§bBGE§8] §cBu komutu oyuncular kullanır.");}return true;
    }
}
