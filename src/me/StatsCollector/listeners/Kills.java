package me.StatsCollector.listeners;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.StatsCollector.utils.statistics.StatisticType;

public class Kills implements Listener {

	@EventHandler
	public void on(EntityDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			Player killer = e.getEntity().getKiller();
			if (e.getEntity().getType() == EntityType.PLAYER) {
				StatisticType.KILLS_PLAYERS.add(killer, 1);
			} else if (e.getEntity().getType() == EntityType.VILLAGER) {
				StatisticType.KILLS_VILLAGERS.add(killer, 1);
			} else if (e.getEntity() instanceof Animals) {
				StatisticType.KILLS_ANIMALS.add(killer, 1);
			} else if (e.getEntity() instanceof Mob) {
				StatisticType.KILLS_MOBS.add(killer, 1);
			}
		}
	}
	
}
