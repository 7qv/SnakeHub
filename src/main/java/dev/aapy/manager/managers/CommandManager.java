package dev.aapy.manager.managers;

import com.google.common.collect.Lists;
import dev.aapy.Hub;
import dev.aapy.commands.admin.CustomTimerCMD;
import dev.aapy.commands.admin.SetSpawn;
import dev.aapy.commands.admin.SendTitle;
import dev.aapy.util.CC;
import dev.alex.net.utilities.command.Command;
import dev.aapy.commands.plugin.HubCore;
import dev.aapy.commands.social.*;
import dev.aapy.manager.handler.Handler;

import java.util.List;

/**
 * @author 7qv_ and Alexito2060 on 19/2/2022.
 * @project SnakeHub
 *
**/

public final class CommandManager extends Handler {
    private final List<Handler> commands = Lists.newArrayList();
    private Command command;

    public CommandManager(Hub plugin) { super(plugin); }

    @Override
    public void enable() {
        this.command = new Command("", false);
        command.register(new HubCore());
        command.register(new WebSite());
        command.register(new SendTitle());
        command.register(new CustomTimerCMD());
        command.register(new SetSpawn());
        command.register(new Twitter());
        command.register(new TeamSpeak());
        command.register(new Store());
        command.register(new Discord());
        CC.log("&cSnakeHub &f" + commands.size() +  " &aCommands have been registered");
        this.command.registerAll();
    }

    @Override
    public void disable() {
        this.command.unregisterAll();
    }
}