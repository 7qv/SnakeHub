package dev.aapy.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import dev.aapy.Hub;
import dev.aapy.file.Nametag;
import dev.aapy.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 7qv_ on 9/2/2022.
 * @project SnakeHub
 */
public class LunarListener extends BukkitRunnable {

    @Override
    public void run() {
        for (Player player : Hub.getInst().getServer().getOnlinePlayers()) {
            List<String> show = null;
            for (String s : Nametag.getConfig().getConfigurationSection("LUNAR-SECTION").getKeys(false)) {
                String path = "LUNAR-SECTION." + s + ".";
                if (player.hasPermission(Nametag.getConfig().getString(path + "PERMISSION"))) {
                    show = Nametag.getConfig().getStringList(path + "LINE");
                    break;
                }
            }

            if (show == null || show.isEmpty()) return;
            show = show.stream().map(s -> s.replace("<player>", player.getDisplayName()).replace("<rank>", Hub.getInst().getPermission().getPermission().getPrefix(player))).collect(Collectors.toList());
            List<String> finalShow = show;

            for (Player target : Hub.getInst().getServer().getOnlinePlayers()) {
                LunarClientAPI.getInstance().overrideNametag(player, CC.translate(finalShow), target);
            }
        }
    }
}