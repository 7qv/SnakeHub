package dev.aapy;

import dev.aapy.customtimer.CustomTimerManager;
import dev.aapy.file.*;
import dev.aapy.hooker.Hooker;
import dev.aapy.hotbar.PvPListener;
import dev.aapy.manager.Manager;
import dev.aapy.manager.managers.PermissionManager;
import dev.aapy.queue.Queue;
import dev.aapy.tablist.TabAnimationTask;
import dev.aapy.tablist.TablistProvider;
import dev.aapy.tablist.shared.TabHandler;
import dev.aapy.tablist.v1_7_R4.v1_7_R4TabAdapter;
import dev.aapy.tablist.v1_8_R3.v1_8_R3TabAdapter;
import dev.aapy.util.CC;
import dev.aapy.scoreboard.AnimationTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Hub extends JavaPlugin {

    private static Hub plugin;
    private PermissionManager permission;
    private Manager manager;
    private CustomTimerManager customTimerManager;
    private PvPListener pvpListener;
    public static Queue queue;
    private Hooker hooker;

    @Override
    public void onEnable() {
        plugin = this;

        this.manager = new Manager(this);
        this.manager.enable();
        CC.log("&cSnakeHub &f" + this.manager.getManagers().size() + " &amanagers have been registered");

        CC.log("&cSnakeHub &f" + "&aPermissions have been registered");
        this.permission = new PermissionManager(this);
        this.permission.loadHook();

        this.customTimerManager = new CustomTimerManager();
        this.queue = new Queue();

        this.hooker = new Hooker();

        AnimationTask.init();
        TabAnimationTask.init();

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        CC.log("&cSnakeHub &f" + "&aBungeeCoord Connecting!");

        Message.getConfig().load();
        Scoreboard.getConfig().load();
        Nametag.getConfig().load();
        Tablist.getConfig().load();
        Config.getConfig().load();
        HookerFile.getConfig().load();

        CC.log("&7&m=========================");
        CC.log("");
        CC.log("&cPlugin Name: &fSnakeHub");
        CC.log("&cVersion: &f1.3.0");
        CC.log("&cAuthor: &fAapy#0001");
        CC.log("");
        CC.log("&7&m=========================");

        for (World world : Bukkit.getWorlds()) {
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setGameRuleValue("doMobSpawning", "false");
            world.setTime(6000L);
        }

        if (Config.getConfig().getBoolean("BOOLEANS.TABLIST")) {
            if (Bukkit.getVersion().contains("1.7"))  {
                new TabHandler(new v1_7_R4TabAdapter(), new TablistProvider(), this, 20L);
            }
            if (Bukkit.getVersion().contains("1.8")) {
                new TabHandler(new v1_8_R3TabAdapter(), new TablistProvider(), this, 20L);
            }
        }

        Message.getConfig().save();
        Scoreboard.getConfig().save();
        Nametag.getConfig().save();
        Tablist.getConfig().save();
        Config.getConfig().save();
        HookerFile.getConfig().save();
    }

    @Override
    public void onDisable() {
        this.manager.disable();
        Message.getConfig().save();
        Scoreboard.getConfig().save();
        Nametag.getConfig().save();
        Tablist.getConfig().save();
        Config.getConfig().save();
        HookerFile.getConfig().save();
    }

    public static Hub getInst() {
        return plugin;
    }
}