package me.StatsCollector.utils.statistics;

import org.bukkit.entity.Player;

import me.StatsCollector.utils.apis.CounterAPI;

public enum StatisticType {
	
	/* Kills */
	KILLS_PLAYERS(StatisticCategory.KILLS, "Players", "Players"),
	KILLS_MOBS(StatisticCategory.KILLS, "Mobs", "Mobs"),
	KILLS_ANIMALS(StatisticCategory.KILLS, "Animals", "Animals"),
	KILLS_VILLAGERS(StatisticCategory.KILLS, "Villagers", "Villagers"),
	
	/* Deaths */
	DEATHS_BY_MOBS(StatisticCategory.DEATHS, "ByMobs", "By Mobs"),
	DEATHS_BY_PLAYERS(StatisticCategory.DEATHS, "ByPlayers", "By Players"),
	DEATHS_BY_SUICIDE(StatisticCategory.DEATHS, "BySuicide", "By Suicide"),
	
	/* Activity */
	ACTIVITY_SENT(StatisticCategory.ACTIVITY, "Sent", "Sent"),
	ACTIVITY_KICKED(StatisticCategory.ACTIVITY, "Kicked", "Kicked"),
	ACTIVITY_EXECUTED(StatisticCategory.ACTIVITY, "Executed", "Executed"),
	ACTIVITY_JOINED(StatisticCategory.ACTIVITY, "Joined", "Joined"),
	ACTIVITY_QUIT(StatisticCategory.ACTIVITY, "Quit", "Quit"),
	
	/* Movement */
	MOVEMENT_TRAVELLED(StatisticCategory.MOVEMENT, "Travelled", "Travelled"),
	MOVEMENT_WORLD_VISITED(StatisticCategory.MOVEMENT, "WorldVisited", "World Visited"),
	MOVEMENT_TELEPORTED(StatisticCategory.MOVEMENT, "Teleported", "Teleported"),

	/* Inventory */
	INVENTORY_POTIONS_MADE(StatisticCategory.INVENTORY, "PotionsMade", "Potions Made"),
	INVENTORY_ITEMS_CRAFTED(StatisticCategory.INVENTORY, "ItemsCrafted", "Items Crafted"),
	INVENTORY_FOOD_COOKED(StatisticCategory.INVENTORY, "FoodCooked", "Food Cooked"),
	INVENTORY_ITEMS_PICKED(StatisticCategory.INVENTORY, "ItemsPicked", "Items Picked"),
	INVENTORY_ITEMS_DROPPED(StatisticCategory.INVENTORY, "ItemsDropped", "Items Dropped"),
	
	/* Interactions */
	INTERACTIONS_DOORS(StatisticCategory.INTERACTIONS, "Doors", "Doors"),
	INTERACTIONS_CHESTS(StatisticCategory.INTERACTIONS, "Chests", "Chests"),
	INTERACTIONS_LEVERS(StatisticCategory.INTERACTIONS, "Levers", "Levers"),
	INTERACTIONS_FENCEGATES(StatisticCategory.INTERACTIONS, "FenceGates", "Fencegates"),
	INTERACTIONS_BUTTONS(StatisticCategory.INTERACTIONS, "Buttons", "Buttons"),
	INTERACTIONS_TRAPDOORS(StatisticCategory.INTERACTIONS, "Trapdoors", "Trapdoors"),
	INTERACTIONS_DROPPERS(StatisticCategory.INTERACTIONS, "Droppers", "Droppers"),
	INTERACTIONS_DISPENSERS(StatisticCategory.INTERACTIONS, "Dispensers", "Dispensers"),
	INTERACTIONS_HOPPERS(StatisticCategory.INTERACTIONS, "Hoppers", "Hoppers"),
	INTERACTIONS_FURNACES(StatisticCategory.INTERACTIONS, "Furnaces", "Furnaces"),
	INTERACTIONS_CRAFTTABLES(StatisticCategory.INTERACTIONS, "CraftTables", "Crafting Tables"),
	
	/* Survival */
	SURVIVAL_FOODS_EATEN(StatisticCategory.SURVIVAL, "FoodsEaten", "Foods Eaten"),
	SURVIVAL_HEARTS_REGAINED(StatisticCategory.SURVIVAL, "HeartsRegained", "Hearts Regained"),
	SURVIVAL_HEARTS_LOST(StatisticCategory.SURVIVAL, "HeartsLost", "Hearts Lost"),
	SURVIVAL_ARMORS_WORN(StatisticCategory.SURVIVAL, "ArmorsWorn", "Armors Worn"),
	
	/* Damage */
	DAMAGE_PLAYERS(StatisticCategory.DAMAGE, "Players", "Players"),
	DAMAGE_MOBS(StatisticCategory.DAMAGE, "Mobs", "Mobs"),
	DAMAGE_ANIMALS(StatisticCategory.DAMAGE, "Animals", "Animals"),
	DAMAGE_VILLAGERS(StatisticCategory.DAMAGE, "Villagers", "Villagers"),
	
	/* World */
	WORLD_BLOCKS_PLACED(StatisticCategory.WORLD, "BlocksPlaced", "Blocks Placed"),
	WORLD_BLOCKS_BROKEN(StatisticCategory.WORLD, "BlocksBroken", "Blocks Broken"),
	WORLD_BLOCKS_IGNITED(StatisticCategory.WORLD, "BlocksIgnited", "Blocks Ignited"),
	WORLD_ORES_MINED(StatisticCategory.WORLD, "OresMined", "Ores Mined"),
	WORLD_SEEDS_PLANTED(StatisticCategory.WORLD, "SeedsPlanted", "Seeds Planted"),
	WORLD_FISHING_SUCCESSES(StatisticCategory.WORLD, "FishingSuccesses", "Fishing Successes"),
	WORLD_TIMES_RESPAWNED(StatisticCategory.WORLD, "TimesRespawned", "Times Respawned");
	
	private StatisticCategory category;
	private String name;
	private String ingameName;
	
	StatisticType(StatisticCategory category, String name, String ingameName) {
		this.category = category;
		this.name = name;
		this.ingameName = ingameName;
	}
	
	public StatisticCategory getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	public String getIngameName() {
		return ingameName;
	}
	public int getValue(Player valuesFrom) {
		return CounterAPI.get(category.getName(), valuesFrom.getUniqueId().toString(), getName());
	}
	public float getFloatValue(Player valuesFrom) {
		return CounterAPI.getFloat(category.getName(), valuesFrom.getUniqueId().toString(), getName());
	}
	
	public void reset(Player target) {
		CounterAPI.set(category.getName(), target.getUniqueId().toString(), getName(), 0);
	}
	
	public void set(Player target, int amount) {
		CounterAPI.set(category.getName(), target.getUniqueId().toString(), getName(), amount);
	}
	public void setFloat(Player target, float amount) {
		CounterAPI.setFloat(category.getName(), target.getUniqueId().toString(), getName(), amount);
	}
	
	public void add(Player target, int amount) {
		CounterAPI.add(category.getName(), target.getUniqueId().toString(), getName(), amount);
	}
	public void addFloat(Player target, float amount) {
		CounterAPI.addFloat(category.getName(), target.getUniqueId().toString(), getName(), amount);
	}
	
	public void remove(Player target, int amount) {
		CounterAPI.remove(category.getName(), target.getUniqueId().toString(), getName(), amount);
	}
	public void removeFloat(Player target, float amount) {
		CounterAPI.removeFloat(category.getName(), target.getUniqueId().toString(), getName(), amount);
	}
}
