package me.StatsCollector.listeners;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

import me.StatsCollector.utils.statistics.StatisticType;

public class Deaths implements Listener {
	
	@EventHandler
	public void on(EntityDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(p.getKiller() != null) {
				StatisticType.DEATHS_BY_PLAYERS.add(p, 1);
			}
		}
	}

	@EventHandler
	public void on(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {			
			Player p = (Player) e.getEntity();

			DamageCause[] suicideCauses = {DamageCause.FALL, DamageCause.DROWNING, DamageCause.CUSTOM};
			for(DamageCause cause : suicideCauses) {
				if(e.getCause() == cause) {
					if((p.getHealth() - e.getDamage()) < 0D) {
						StatisticType.DEATHS_BY_SUICIDE.add(p, 1);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void on(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {			
			Player p = (Player) e.getEntity();
			
			if(e.getCause() == DamageCause.ENTITY_ATTACK) {
				if((p.getHealth() - e.getDamage()) < 0D) {
					if(e.getDamager() instanceof Monster || e.getDamager() instanceof Animals) {
						StatisticType.DEATHS_BY_MOBS.add(p, 1);
					}
				}
			}
		}
	}
	
}
