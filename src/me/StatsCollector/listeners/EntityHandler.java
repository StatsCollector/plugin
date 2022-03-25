package me.StatsCollector.listeners;

import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.StatsCollector.utils.statistics.StatisticType;

public class EntityHandler implements Listener {

	@EventHandler
	public void on(EntityRegainHealthEvent e) {
		if (e.getEntity() instanceof Player) {
			StatisticType.SURVIVAL_HEARTS_REGAINED.addFloat((Player) e.getEntity(), (float) e.getAmount());
		}
	}

	@EventHandler
	public void on(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(e.getDamage() < 1000D)
				StatisticType.SURVIVAL_HEARTS_LOST.addFloat(p, (float) e.getDamage());
			
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
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(p.getKiller() != null) {
				StatisticType.DEATHS_BY_PLAYERS.add(p, 1);
			}
		}
	}
	
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
	
	@EventHandler
	public void on(EntityPickupItemEvent e) {
		if(e.getEntity() instanceof Player) {
			StatisticType.INVENTORY_ITEMS_PICKED.add((Player) e.getEntity(), e.getItem().getItemStack().getAmount());
		}
	}
	
}
