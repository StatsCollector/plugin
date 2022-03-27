package me.StatsCollector.utils;

import org.bukkit.Bukkit;

import me.StatsCollector.StatsCollector;
import me.StatsCollector.listeners.PlayerHandler;
import me.StatsCollector.utils.statistics.StatisticType;

public class Runnable {

	static int id = 99;

    public static void start() {
        if(!isRunning()) {
            id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(StatsCollector.getInstance(), () -> {
            	Bukkit.getOnlinePlayers().forEach(all -> {
					if (PlayerHandler.travelled.containsKey(all)) {
						StatisticType.MOVEMENT_TRAVELLED.add(all, PlayerHandler.travelled.get(all));
						PlayerHandler.travelled.put(all, 0);
					}
				});
            }, 0L, 20*60*10);
        }
    }

    public static boolean isRunning() {
        return Bukkit.getServer().getScheduler().isCurrentlyRunning(id);
    }

    public static void stop() {
        Bukkit.getServer().getScheduler().cancelTask(id);
    }
}
