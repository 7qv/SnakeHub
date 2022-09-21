package dev.aapy.scoreboard;

import dev.aapy.SnakeHub;
import dev.aapy.customtimer.CustomTimer;
import dev.aapy.customtimer.TimerFormatter;
import dev.aapy.file.Scoreboard;
import dev.aapy.manager.managers.ScoreBoardManager;
import dev.aapy.scoreboard.assamble.AssembleAdapter;
import dev.aapy.util.ActionBar;
import dev.aapy.util.CC;
import dev.aapy.util.SnakeUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 7qv_ on 7/2/2022.
 * @project SnakeHub
 */

public class ScoreBoardProvider implements AssembleAdapter {

    private final ScoreBoardManager scoreboard = SnakeHub.getInst().getManager().getScoreboard();

    @Override
    public String getTitle(Player player) {
        return Scoreboard.getConfig().getBoolean("SCOREBOARD.ANIMATED.TITLE.ENABLED") ? AnimationTask.getScoreboardTitle() : CC.translate(Scoreboard.getConfig().getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        final List<String> list = new ArrayList<>();
        Collection<CustomTimer> customtimer = SnakeHub.getInst().getCustomTimerManager().getCustomtimer();

        if (SnakeHub.queue.inQueue(player)) {
            for (String queue : Scoreboard.getConfig().getStringList("SCOREBOARD.LINES.QUEUE")) {

                queue = queue.replace("<server-queue>", String.valueOf(SnakeHub.queue.getQueueIn(player)));
                queue = queue.replace("<position-queue>", String.valueOf(SnakeHub.queue.getPosition(player)));
                queue = queue.replace("<size-queue>", String.valueOf(SnakeHub.queue.getInQueue(SnakeHub.queue.getQueueIn(player))));

                queue = queue.replace("<arrow>", StringEscapeUtils.unescapeJava("\\u2a20"));
                queue = queue.replace("<placeholder:data>", SnakeUtil.getDate());
                queue = queue.replace("<placeholder:time>", SnakeUtil.getHour());
                queue = queue.replace("<players_online>", Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers());
                queue = queue.replace("<rank>", SnakeHub.getInst().getPermission().getPermission().getPrefix(player));
                queue = queue.replace("<player>", player.getName());
                queue = queue.replace("<footers>", Scoreboard.getConfig().getBoolean("SCOREBOARD.ANIMATED.FOOTER.ENABLED") ? AnimationTask.getScoreboardFooter() : CC.translate(Scoreboard.getConfig().getString("SCOREBOARD.FOOTER")));

                list.add(PlaceholderAPI.setPlaceholders(player, queue));
            }
        } else {
            for (String lines : Scoreboard.getConfig().getStringList("SCOREBOARD.LINES.NORMAL")) {
                lines = lines.replace("<arrow>", StringEscapeUtils.unescapeJava("\\u2a20"));
                lines = lines.replace("<placeholder:data>", SnakeUtil.getDate());
                lines = lines.replace("<placeholder:time>", SnakeUtil.getHour());
                lines = lines.replace("<players_online>", Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers());
                lines = lines.replace("<rank>", SnakeHub.getInst().getPermission().getPermission().getPrefix(player));
                lines = lines.replace("<player>", player.getName());
                lines = lines.replace("<footers>", Scoreboard.getConfig().getBoolean("SCOREBOARD.ANIMATED.FOOTER.ENABLED") ? AnimationTask.getScoreboardFooter() : CC.translate(Scoreboard.getConfig().getString("SCOREBOARD.FOOTER")));

                list.add(PlaceholderAPI.setPlaceholders(player, lines));
            }
        }

        if(!customtimer.isEmpty()) {
            for (final CustomTimer timer : customtimer) {
                if (Scoreboard.getConfig().getBoolean("SCOREBOARD.LINES.CUSTOMTIMER")) {
                 ActionBar.sendActionBar(player, timer.getScoreboard() + CC.translate("&7: &f" + TimerFormatter.getRemaining(timer.getRemaining(), true)));
                } else {
                    list.add(timer.getScoreboard() + CC.translate("&7: &f" + TimerFormatter.getRemaining(timer.getRemaining(), true)));
                   }
                }
            }

        return list;
    }
}