package dev.aapy.queue;

import dev.aapy.SnakeHub;
import dev.aapy.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.signatured.ezqueueshared.QueueInfo;
import me.signatured.ezqueuespigot.EzQueueAPI;

public class Queue {

   /* public QueueEnum getQueueSupport() {
        if (Config.getConfig().getBoolean("QUEUESYSTEM.EZQUEUE.ENABLED")) {
            return QueueEnum.EZQUEUE;
        } else {
            if (Config.getConfig().getBoolean("QUEUESYSTEM.PORTAL.ENABLED")) {
                return QueueEnum.EZQUEUE;
            } else {
                return QueueEnum.NONE;
            }
        }
    }

    public boolean inQueue(Player p) {
        switch (this.getQueueSupport()) {
            case EZQUEUE:
                return QueueInfo.getQueueInfo(EzQueueAPI.getQueue(p)) != null;
            case PORTAL:
                return me.joeleoli.portal.shared.queue.Queue.getByPlayer(p.getUniqueId()) != null;
            default:
                return false;
        }
    }

    public void sendPlayer(Player p, String server) {
        switch (this.getQueueSupport()) {
            case EZQUEUE:
                EzQueueAPI.addToQueue(p, server);
                break;
            case PORTAL:
                Bukkit.getServer().dispatchCommand(p, "joinqueue " + server);
                break;
            default:
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(server);
                p.getPlayer().sendPluginMessage(SnakeHub.getInst(), "BungeeCord", out.toByteArray());
                break;
        }
    }

    public String getQueueIn(Player p) {
        switch (this.getQueueSupport()) {
            case EZQUEUE:
                return EzQueueAPI.getQueue(p);
            case PORTAL:
                return me.joeleoli.portal.shared.queue.Queue.getByPlayer(p.getUniqueId()).getName();
            default:
                return "NoInQueue";
        }
    }

    public int getPosition(Player p) {
        switch (this.getQueueSupport()) {
            case EZQUEUE:
                return EzQueueAPI.getPosition(p);
            case PORTAL:
                return me.joeleoli.portal.shared.queue.Queue.getByPlayer(p.getUniqueId()).getPosition(p.getUniqueId());
            default:
                return 0;
        }
    }

    public int getInQueue(String queue) {
        switch (this.getQueueSupport()) {
            case EZQUEUE:
                QueueInfo info = QueueInfo.getQueueInfo(queue);
                return info.getPlayersInQueue().size();
            case PORTAL:
                return me.joeleoli.portal.shared.queue.Queue.getByName(queue).getPlayers().size();
            default:
                return 0;
        //*/
}
