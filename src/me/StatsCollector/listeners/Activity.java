package me.StatsCollector.listeners;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.StatsCollector.utils.statistics.StatisticType;

public class Activity implements Listener {

	@EventHandler
	public void on(AsyncPlayerChatEvent e) {
		StatisticType.ACTIVITY_SENT.add(e.getPlayer(), 1);
	}

	@EventHandler
	public void on(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();

		StatisticType.ACTIVITY_EXECUTED.add(p, 1);

		int totalDistanceTravelled = ((p.getStatistic(Statistic.WALK_ONE_CM) / 100)
				+ (p.getStatistic(Statistic.WALK_UNDER_WATER_ONE_CM) / 100)
				+ (p.getStatistic(Statistic.WALK_ON_WATER_ONE_CM) / 100)
				+ (p.getStatistic(Statistic.SPRINT_ONE_CM) / 100) + (p.getStatistic(Statistic.CROUCH_ONE_CM) / 100)
				+ (p.getStatistic(Statistic.CLIMB_ONE_CM) / 100) + (p.getStatistic(Statistic.FALL_ONE_CM) / 100)
				+ (p.getStatistic(Statistic.FLY_ONE_CM) / 100));
		
		if(e.getMessage().contains("stats")) 
			StatisticType.MOVEMENT_TRAVELLED.add(p, totalDistanceTravelled);
		
		if(e.getMessage().equalsIgnoreCase("kill") || e.getMessage().equalsIgnoreCase("kill " + e.getPlayer().getName()))
			StatisticType.DEATHS_BY_SUICIDE.add(e.getPlayer(), 1);
	}

	@EventHandler
	public void on(PlayerJoinEvent e) {
		StatisticType.ACTIVITY_JOINED.add(e.getPlayer(), 1);
	}

	@EventHandler
	public void on(PlayerQuitEvent e) {
		StatisticType.ACTIVITY_QUIT.add(e.getPlayer(), 1);
	}

	@EventHandler
	public void on(PlayerKickEvent e) {
		StatisticType.ACTIVITY_KICKED.add(e.getPlayer(), 1);
	}
	
}
