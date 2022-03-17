package me.StatsCollector.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.StatsCollector.utils.statistics.StatisticType;

public class Inventory implements Listener {
	
	@EventHandler
	public void on(InventoryClickEvent e) {
		if (e.getInventory().getType() == InventoryType.FURNACE) {
			if (e.getSlot() == 2) {
				StatisticType.INVENTORY_FOOD_COOKED.add((Player) e.getWhoClicked(), e.getCurrentItem().getAmount());
			}
		} else if (e.getInventory().getType() == InventoryType.WORKBENCH) {
			if (e.getSlot() == 0) {
				StatisticType.INVENTORY_ITEMS_CRAFTED.add((Player) e.getWhoClicked(), e.getCurrentItem().getAmount());

			}
		}else if(e.getInventory().getType() == InventoryType.BREWING) {
			if (e.getSlot() == 0 || e.getSlot() == 1 || e.getSlot() == 2) {
				StatisticType.INVENTORY_POTIONS_MADE.add((Player) e.getWhoClicked(), e.getCurrentItem().getAmount());
			}
		}else if(e.getInventory().getType() == InventoryType.CRAFTING) {
			if (e.getSlot() == 0 ) {
				StatisticType.INVENTORY_ITEMS_CRAFTED.add((Player) e.getWhoClicked(), e.getCurrentItem().getAmount());
			}
		}
	}
	
	@EventHandler
	public void on(EntityPickupItemEvent e) {
		if(e.getEntity() instanceof Player) {
			StatisticType.INVENTORY_ITEMS_PICKED.add((Player) e.getEntity(), e.getItem().getItemStack().getAmount());
		}
	}
	
	@EventHandler
	public void on(PlayerDropItemEvent e) {
			StatisticType.INVENTORY_ITEMS_DROPPED.add((Player) e.getPlayer(), e.getItemDrop().getItemStack().getAmount());
	}
	
}
