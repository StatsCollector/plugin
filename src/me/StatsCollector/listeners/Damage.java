package me.StatsCollector.listeners;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.StatsCollector.utils.statistics.StatisticType;

public class Damage implements Listener {
	
	@EventHandler
	public void on(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player damager = (Player) e.getDamager();
			if (e.getEntity().getType() == EntityType.PLAYER) {
				StatisticType.DAMAGE_PLAYERS.addFloat(damager, (float) e.getDamage());
			} else if (e.getEntity().getType() == EntityType.VILLAGER) {
				StatisticType.DAMAGE_VILLAGERS.addFloat(damager, (float) e.getDamage());
			} else if (e.getEntity() instanceof Animals) {
				StatisticType.DAMAGE_ANIMALS.addFloat(damager, (float) e.getDamage());
			} else if (e.getEntity() instanceof Mob) {
				StatisticType.DAMAGE_MOBS.addFloat(damager, (float) e.getDamage());
			}
		}
	}
	
}
