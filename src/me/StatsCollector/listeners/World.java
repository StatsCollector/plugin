package me.StatsCollector.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.StatsCollector.utils.MaterialUtils;
import me.StatsCollector.utils.statistics.StatisticType;

public class World implements Listener {
	
	@EventHandler
	public void on(BlockPlaceEvent e) {
		StatisticType.WORLD_BLOCKS_PLACED.add(e.getPlayer(), 1);
		
		for(Material mat : MaterialUtils.ores) {
			if(e.getBlock().getType() == mat) {
				StatisticType.WORLD_ORES_MINED.add(e.getPlayer(), 1);

			}
		}
		
		for(Material mat : MaterialUtils.seeds) {
			if(e.getItemInHand().getType() == mat) {
				StatisticType.WORLD_SEEDS_PLANTED.add(e.getPlayer(), 1);
			}
		}
	}
	
	@EventHandler
	public void on(BlockBreakEvent e) {
		StatisticType.WORLD_BLOCKS_BROKEN.add(e.getPlayer(), 1);
	}
	
	@EventHandler
	public void on(BlockIgniteEvent e) {
		if(e.getCause() == IgniteCause.FLINT_AND_STEEL) {
			StatisticType.WORLD_BLOCKS_IGNITED.add(e.getPlayer(), 1);
		}
	}
	
	@EventHandler
	public void on(PlayerFishEvent e) {
		if(e.getCaught() != null) {
			StatisticType.WORLD_FISHING_SUCCESSES.add(e.getPlayer(), 1);

		}
	}
	
	@EventHandler
	public void on(PlayerRespawnEvent e) {
		StatisticType.WORLD_TIMES_RESPAWNED.add(e.getPlayer(), 1);
	}
	
}
