package me.StatsCollector.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.StatsCollector.cmd.StatsCMD;
import me.StatsCollector.utils.Inventories;
import me.StatsCollector.utils.statistics.StatisticCategory;
import me.StatsCollector.utils.statistics.StatisticType;

public class InventoryHandler implements Listener {
	
	@EventHandler
	public void on(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack item = e.getCurrentItem();
		int slot = e.getSlot();
		
		if (inv.getType() == InventoryType.FURNACE) {
			if (slot == 2) {
				StatisticType.INVENTORY_FOOD_COOKED.add(p, item.getAmount());
			}
		} else if (inv.getType() == InventoryType.WORKBENCH) {
			if (slot == 0) {
				StatisticType.INVENTORY_ITEMS_CRAFTED.add(p, item.getAmount());

			}
		}else if(inv.getType() == InventoryType.BREWING) {
			if (slot == 0 || slot == 1 || slot == 2) {
				StatisticType.INVENTORY_POTIONS_MADE.add(p, item.getAmount());
			}
		}else if(inv.getType() == InventoryType.CRAFTING) {
			if (slot == 0 ) {
				StatisticType.INVENTORY_ITEMS_CRAFTED.add(p, item.getAmount());
			}
		}
		
		if(inv == StatsCMD.mainInventory) {
			if(item != null && item.getType() != Material.AIR) {
				if(item.getType() != Material.ARROW) {
					if(item.getItemMeta().getDisplayName().length() < 2) return;
					String name = item.getItemMeta().getDisplayName().substring(2, item.getItemMeta().getDisplayName().length());
					if(StatsCMD.targetPlayer != null && getCategoryFromName(name) != null) {
						StatsCMD.categoryInventory = Inventories.CategoryGui(StatsCMD.targetPlayer, getCategoryFromName(name));
						p.openInventory(StatsCMD.categoryInventory);
					}
					
				} else {
					p.closeInventory();
				}
			}
			e.setCancelled(true);
		} else if(inv == StatsCMD.categoryInventory) {
			if(item != null && item.getType() != Material.AIR) {
				if(item.getType() == Material.ARROW) {
					p.openInventory(StatsCMD.mainInventory);
				}
			}
			e.setCancelled(true);
		}
	}
	
	public static StatisticCategory getCategoryFromName(String name) {
		for(StatisticCategory category : StatisticCategory.values()) {
			if(category.getName().equals(name)) return category;
		}
		return null;
	}
}
