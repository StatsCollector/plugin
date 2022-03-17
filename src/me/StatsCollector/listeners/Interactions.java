package me.StatsCollector.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.StatsCollector.utils.MaterialUtils;
import me.StatsCollector.utils.statistics.StatisticType;

public class Interactions implements Listener {

	@EventHandler
	public void on(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			if (e.getClickedBlock().getType() == Material.CHEST
					|| e.getClickedBlock().getType() == Material.CHEST_MINECART) {
				StatisticType.INTERACTIONS_CHESTS.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.LEVER) {
				StatisticType.INTERACTIONS_LEVERS.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.DROPPER) {
				StatisticType.INTERACTIONS_DROPPERS.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.DISPENSER) {
				StatisticType.INTERACTIONS_DISPENSERS.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.HOPPER) {
				StatisticType.INTERACTIONS_HOPPERS.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.FURNACE
					|| e.getClickedBlock().getType() == Material.FURNACE_MINECART) {
				StatisticType.INTERACTIONS_FURNACES.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
				StatisticType.INTERACTIONS_CRAFTTABLES.add(e.getPlayer(), 1);
			} else {
				for (Material mat : MaterialUtils.doors) {
					if (e.getClickedBlock().getType() == mat) {
						StatisticType.INTERACTIONS_DOORS.add(e.getPlayer(), 1);
					}
				}

				for (Material mat : MaterialUtils.trapdoor) {
					if (e.getClickedBlock().getType() == mat) {
						StatisticType.INTERACTIONS_TRAPDOORS.add(e.getPlayer(), 1);
					}
				}

				for (Material mat : MaterialUtils.fencegates) {
					if (e.getClickedBlock().getType() == mat) {
						StatisticType.INTERACTIONS_FENCEGATES.add(e.getPlayer(), 1);
					}
				}

				for (Material mat : MaterialUtils.buttons) {
					if (e.getClickedBlock().getType() == mat) {
						StatisticType.INTERACTIONS_BUTTONS.add(e.getPlayer(), 1);
					}
				}
			}
		}
	}
}
