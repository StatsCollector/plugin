package me.StatsCollector.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import me.StatsCollector.utils.apis.ItemBuilder;
import me.StatsCollector.utils.statistics.StatisticCategory;
import me.StatsCollector.utils.statistics.StatisticType;

public class Inventories {
	
	public static Inventory MainGui(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "§8Stats: " + p.getName());
		
		inv.setItem(26,
				new ItemBuilder(Material.ARROW).setName("§cClose").toItemStack());


		for (StatisticCategory category : StatisticCategory.values()) {
			ItemBuilder builder = new ItemBuilder(category.getGuiMaterial()).setName("§7" + category.getName())
					.addLoreLine("").addLoreLine("§7Events Listed");
			for (StatisticType statistic : category.getStatistics()) {
				builder.addLoreLine("§c - " + statistic.getIngameName());
			}
			inv.addItem(builder.toItemStack());
		}
		
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) inv.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
					.setName("§n").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
		}

		return inv;

	}
	
	public static Inventory CategoryGui(Player p, StatisticCategory category) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "§8Stats: " + p.getName());
		
		inv.setItem(26,
				new ItemBuilder(Material.ARROW).setName("§cBack").toItemStack());
		
		if(category == StatisticCategory.LIVES) {
			inv.addItem(new ItemBuilder(StatisticType.TIME_SPEND.getGuiMaterial()).setName("§7" + StatisticType.TIME_SPEND.getIngameName())
					.setLore("§n", "§7Time: §c"+ FileManager.timePlayer(FileManager.TimeSinceJoined.get(p))).toItemStack());
			
			inv.addItem(new ItemBuilder(StatisticType.HEALTH.getGuiMaterial()).setName("§7" + StatisticType.HEALTH.getIngameName())
					.setLore("§n", "§7Amount: §c" + p.getHealth()).toItemStack());
			
			inv.addItem(new ItemBuilder(StatisticType.FOOD_LEVEL.getGuiMaterial()).setName("§7" + StatisticType.FOOD_LEVEL.getIngameName())
					.setLore("§n", "§7Amount: §c" + p.getFoodLevel()).toItemStack());
			
			int invAmount = 0;
			for(ItemStack itemStack : p.getInventory().getContents()) {
				if(itemStack != null && itemStack.getType() != Material.AIR) invAmount += itemStack.getAmount();
			}
			
			inv.addItem(new ItemBuilder(StatisticType.INVENTORY_ITEMS.getGuiMaterial()).setName("§7" + StatisticType.INVENTORY_ITEMS.getIngameName())
					.setLore("§n", "§7Amount: §c" + invAmount).toItemStack());
			
			Date date = new Date(p.getFirstPlayed());
			SimpleDateFormat dateFormated = new SimpleDateFormat("dd.MM.yyyy");
			SimpleDateFormat timeFormated = new SimpleDateFormat("hh:mm:ss");
			
			inv.addItem(new ItemBuilder(StatisticType.FIRST_TIME_JOINED.getGuiMaterial()).setName("§7" + StatisticType.FIRST_TIME_JOINED.getIngameName())
					.setLore("§n", "§7Date: §c" + dateFormated.format(date), "§7Time: §c" + timeFormated.format(date)).toItemStack());
		} else {
			for(StatisticType statistic : category.getStatistics()) {
				inv.addItem(new ItemBuilder(statistic.getGuiMaterial()).setName("§7" + statistic.getIngameName()).setLore("§n", "§7Amount: §c" + statistic.getFloatValue(p)).toItemStack());
			}
		}
		
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) inv.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
					.setName("§n").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
		}
		
		return inv;
	}
	
}
