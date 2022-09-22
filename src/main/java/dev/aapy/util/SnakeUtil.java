package dev.aapy.util;

import dev.aapy.SnakeHub;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author 7qv_ on 14/3/2022.
 * @project SnakeHub
 */
public class SnakeUtil {
    public static String getDate() {
        return (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
    }

    public static String getHour() {
        return (new SimpleDateFormat("HH:mm")).format(new Date());
    }


    public static BukkitTask asyncTimer(Callable callable, long delay, long value) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(SnakeHub.getInst(), callable::call, delay, value);
    }

    public interface Callable {
        void call();
    }

}
