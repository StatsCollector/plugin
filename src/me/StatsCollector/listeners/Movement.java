package me.StatsCollector.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import me.StatsCollector.utils.statistics.StatisticType;

public class Movement implements Listener{
	
	@EventHandler
	public void on (PlayerChangedWorldEvent e) {
		StatisticType.MOVEMENT_WORLD_VISITED.add(e.getPlayer(), 1);
	}
	
	@EventHandler
	public void on(PlayerTeleportEvent e) {
		StatisticType.MOVEMENT_TELEPORTED.add(e.getPlayer(), 1);
	}
	
}
