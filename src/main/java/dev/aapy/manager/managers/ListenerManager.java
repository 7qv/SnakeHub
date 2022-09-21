package dev.aapy.manager.managers;

import com.google.common.collect.Lists;
import dev.aapy.Hub;
import dev.aapy.file.Config;
import dev.aapy.file.Nametag;
import dev.aapy.listeners.*;
import dev.aapy.hotbar.*;
import dev.aapy.util.particles.Particles;
import dev.aapy.manager.handler.Handler;
import dev.aapy.nametags.NametagProvider;
import dev.aapy.selector.event.SelectorEvent;
import dev.aapy.selector.menu.SelectorMenu;
import dev.aapy.util.CC;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;

import java.util.List;

/**
 * @author 7qv_ and Alexito2060 on 19/2/2022.
 * @project SnakeHub
 */

@Getter
public final class ListenerManager extends Handler {

    private final List<Handler> listener = Lists.newArrayList();

    private LunarListener lunarListener;


    public ListenerManager(Hub plugin) { super(plugin); }

    @Override
    public void enable() {
        PluginManager manager = getPlugin().getServer().getPluginManager();
        manager.registerEvents(new PlayerListener(), getPlugin());
        if (Config.getConfig().getBoolean("BOOLEANS.LUNAR-CLIENT.NAMETAG")) {
            this.lunarListener = new LunarListener();
            this.lunarListener.runTaskTimerAsynchronously(Hub.getInst(), 5L, 10L);
        }
        if (Nametag.getConfig().getBoolean("NAMETAGS.ENABLED")) {
            new dev.aapy.nametags.manager.Nametag(Hub.getInst(), new NametagProvider());
        }

        manager.registerEvents(new DeveloperListener(), getPlugin());
        manager.registerEvents(new EnderButtListener(), getPlugin());
        manager.registerEvents(new ChatFormatListener(), getPlugin());
        manager.registerEvents(new JumpListener(), getPlugin());
        manager.registerEvents(new LaunchPadListener(), getPlugin());
        manager.registerEvents(new Particles(), getPlugin());
        manager.registerEvents(new PlayerInvisibilityListener(), getPlugin());
        manager.registerEvents(new PvPListener(), getPlugin());
        manager.registerEvents(new OutfitsListener(), getPlugin());
        manager.registerEvents(new CosmeticListener(), getPlugin());
        manager.registerEvents(new SelectorEvent(), getPlugin());
        manager.registerEvents(new SelectorMenu(), getPlugin());
        //manager.registerEvents(new SelectorListener(), getPlugin());
        manager.registerEvents(new CosmeticListener(), getPlugin());
        manager.registerEvents(new ChatFormatListener(), getPlugin());
        manager.registerEvents(new WorldListener(), getPlugin());
        CC.log("&cSnakeHub &f" + this.listener.size() + " &aListeners have been registered");
    }
}
