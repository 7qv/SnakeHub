package dev.aapy.commands.social;

import dev.alex.net.utilities.command.command.CommandExecutor;
import dev.aapy.file.Config;
import dev.aapy.file.Message;
import dev.aapy.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author 7qv_ and Alexito2060 on 19/2/2022.
 * @project SnakeHub
 */
public class Twitter extends CommandExecutor {

    public Twitter() {
        super("twitter", "Twitter Command", "hub.twitter.command");
    }

   @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Message.getConfig().getStringList("SOCIAL.TWITTER").forEach((s) -> {
            sender.sendMessage(CC.translate(s.replace("<twitter>", Config.getConfig().getString("SOCIAL.TWITTER"))));
        });
        return true;
    }
}
