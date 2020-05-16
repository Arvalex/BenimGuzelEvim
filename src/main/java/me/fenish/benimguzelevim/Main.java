package me.fenish.benimguzelevim;

import me.fenish.benimguzelevim.Commands.MainCommand;
import me.fenish.benimguzelevim.Commands.SethomeCommand;
import me.fenish.benimguzelevim.Events.DeleteGuiClick;
import me.fenish.benimguzelevim.Events.HomeGuiClick;
import me.fenish.benimguzelevim.NMS.ActionBar;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    public static Main getInstance(){
        return instance;
    }
    public YamlCreator yml;
    public HomeGuiClick homecevent;
    public ActionBar acbar;
    public DeleteGuiClick delcevent;
    @Override
    public void onEnable() {
        instance = this;
        homecevent = new HomeGuiClick();
        acbar = new ActionBar();
        delcevent = new DeleteGuiClick();
        this.getCommand("home").setExecutor(new MainCommand());
        this.getCommand("sethome").setExecutor(new SethomeCommand());
        this.getServer().getPluginManager().registerEvents(homecevent, this);
        this.getServer().getPluginManager().registerEvents(delcevent, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
