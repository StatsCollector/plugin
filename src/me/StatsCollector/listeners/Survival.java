package me.StatsCollector.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import me.StatsCollector.utils.MaterialUtils;
import me.StatsCollector.utils.statistics.StatisticType;

public class Survival implements Listener {

	@EventHandler
	public void on(PlayerItemConsumeEvent e) {
		for (Material mat : MaterialUtils.foods) {
			if (e.getItem().getType() == mat) {
				StatisticType.SURVIVAL_FOODS_EATEN.addFloat(e.getPlayer(), 1);
			}
		}
	}

	@EventHandler
	public void on(EntityRegainHealthEvent e) {
		if (e.getEntity() instanceof Player) {
			StatisticType.SURVIVAL_HEARTS_REGAINED.addFloat((Player) e.getEntity(), (float) e.getAmount());
		}
	}

	@EventHandler
	public void on(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if(e.getDamage() < 1000D)
				StatisticType.SURVIVAL_HEARTS_LOST.addFloat((Player) e.getEntity(), (float) e.getDamage());
		}
	}

	@EventHandler
	public void on(PlayerInteractEvent e) {
		if (e.getItem() != null) {
			for (Material mat : MaterialUtils.armors) {
				if (e.getItem().getType() == mat) {
					StatisticType.SURVIVAL_ARMORS_WORN.add(e.getPlayer(), 1);
				}
			}
		}
	}

	@EventHandler
	public void on(InventoryClickEvent e) {
		// ArmorsWorn noch zu machen
	}

}