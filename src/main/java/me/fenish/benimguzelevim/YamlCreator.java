package me.fenish.benimguzelevim;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class YamlCreator extends YamlConfiguration {
    File yml;
    FileConfiguration config;

    public YamlCreator(Player p) {
        yml = new File(Main.getInstance().getDataFolder(), p.getName() + ".yml");
        if (!yml.exists()) {
            try {
                yml.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        load();
    }
    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(yml);
    }
    public void load() {
        try {
            super.load(yml);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            System.out.println("§8[§bBGE§8] §cDosya Yüklenemedi.");
        }
    }
    public void save(){
        try {
            super.save(yml);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("§8[§bBGE§8] §cDosya Kaydedilemedi.");
        }
    }
}
