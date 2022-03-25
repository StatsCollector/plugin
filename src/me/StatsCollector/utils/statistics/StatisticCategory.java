package me.StatsCollector.utils.statistics;

import java.util.ArrayList;

import org.bukkit.Material;

public enum StatisticCategory {
	
	LIVES("Lives", Material.CLOCK),
	KILLS("Kills", Material.IRON_SWORD), 
	DEATHS("Deaths", Material.SKELETON_SKULL), 
	ACTIVITY("Activity", Material.PAPER), 
	WORLD("World", Material.GRASS_BLOCK), 
	MOVEMENT("Movement", Material.FEATHER), 
	INVENTORY("Inventory", Material.CHEST),
	INTERACTIONS("Interactions", Material.IRON_DOOR),
	SURVIVAL("Survival", Material.STICK),
	DAMAGE("Damage", Material.REDSTONE);
	
	private String name;
	private Material guiMaterial;
	StatisticCategory(String name, Material guiMaterial) {
		this.name = name;
		this.guiMaterial = guiMaterial;
	}
	
	public String getName() {
		return name;
	}
	
	public Material getGuiMaterial() {
		return guiMaterial;
	}
	
	public ArrayList<StatisticType> getStatistics() {
		ArrayList<StatisticType> output = new ArrayList<>();
		for(StatisticType statistic : StatisticType.values()) {
			if(statistic.getCategory() == this) output.add(statistic);
		}
		return output;
	}
}
