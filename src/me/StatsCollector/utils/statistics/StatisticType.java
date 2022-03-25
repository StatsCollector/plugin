package me.StatsCollector.utils.statistics;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.StatsCollector.utils.apis.CounterAPI;

public enum StatisticType {
	
	/* Lives */
	TIME_SPEND(StatisticCategory.LIVES, "TimeSpend", "Time Spend", Material.CLOCK),
	HEALTH(StatisticCategory.LIVES, "Health", "Health", Material.LEATHER_CHESTPLATE),
	FOOD_LEVEL(StatisticCategory.LIVES, "FoodLevel", "Food Level", Material.APPLE),	
	INVENTORY_ITEMS(StatisticCategory.LIVES, "InventoryItems", "Inventory Items", Material.STICK),
	FIRST_TIME_JOINED(StatisticCategory.LIVES, "FirstTimeJoined", "First Time Joined", Material.ENCHANTED_BOOK),

	/* Kills */
	KILLS_PLAYERS(StatisticCategory.KILLS, "Players", "Players", Material.PLAYER_HEAD),
	KILLS_MOBS(StatisticCategory.KILLS, "Mobs", "Mobs", Material.SKELETON_SKULL),
	KILLS_ANIMALS(StatisticCategory.KILLS, "Animals", "Animals", Material.LEATHER),
	KILLS_VILLAGERS(StatisticCategory.KILLS, "Villagers", "Villagers", Material.EMERALD),
	
	/* Deaths */
	DEATHS_BY_MOBS(StatisticCategory.DEATHS, "ByMobs", "By Mobs", Material.SKELETON_SKULL),
	DEATHS_BY_PLAYERS(StatisticCategory.DEATHS, "ByPlayers", "By Players", Material.PLAYER_HEAD),
	DEATHS_BY_SUICIDE(StatisticCategory.DEATHS, "BySuicide", "By Suicide", Material.LAVA_BUCKET),
	
	/* Activity */
	ACTIVITY_SENT(StatisticCategory.ACTIVITY, "Sent", "Sent", Material.WRITABLE_BOOK),
	ACTIVITY_KICKED(StatisticCategory.ACTIVITY, "Kicked", "Kicked", Material.STONE_AXE),
	ACTIVITY_EXECUTED(StatisticCategory.ACTIVITY, "Executed", "Executed", Material.COMMAND_BLOCK),
	ACTIVITY_JOINED(StatisticCategory.ACTIVITY, "Joined", "Joined", Material.OAK_SIGN),
	ACTIVITY_QUIT(StatisticCategory.ACTIVITY, "Quit", "Quit", Material.OAK_SIGN),
	
	/* Movement */
	MOVEMENT_TRAVELLED(StatisticCategory.MOVEMENT, "Travelled", "Travelled", Material.LEATHER_BOOTS),
	MOVEMENT_WORLD_VISITED(StatisticCategory.MOVEMENT, "WorldVisited", "World Visited", Material.COMPASS),
	MOVEMENT_TELEPORTED(StatisticCategory.MOVEMENT, "Teleported", "Teleported", Material.FEATHER),

	/* Inventory */
	INVENTORY_POTIONS_MADE(StatisticCategory.INVENTORY, "PotionsMade", "Potions Made", Material.POTION),
	INVENTORY_ITEMS_CRAFTED(StatisticCategory.INVENTORY, "ItemsCrafted", "Items Crafted", Material.CRAFTING_TABLE),
	INVENTORY_FOOD_COOKED(StatisticCategory.INVENTORY, "FoodCooked", "Food Cooked", Material.COOKED_CHICKEN),
	INVENTORY_ITEMS_PICKED(StatisticCategory.INVENTORY, "ItemsPicked", "Items Picked", Material.CHEST),
	INVENTORY_ITEMS_DROPPED(StatisticCategory.INVENTORY, "ItemsDropped", "Items Dropped", Material.HOPPER),
	
	/* Interactions */
	INTERACTIONS_DOORS(StatisticCategory.INTERACTIONS, "Doors", "Doors", Material.IRON_DOOR),
	INTERACTIONS_CHESTS(StatisticCategory.INTERACTIONS, "Chests", "Chests", Material.CHEST),
	INTERACTIONS_LEVERS(StatisticCategory.INTERACTIONS, "Levers", "Levers", Material.LEVER),
	INTERACTIONS_FENCEGATES(StatisticCategory.INTERACTIONS, "FenceGates", "Fencegates", Material.OAK_FENCE_GATE),
	INTERACTIONS_BUTTONS(StatisticCategory.INTERACTIONS, "Buttons", "Buttons", Material.OAK_BUTTON),
	INTERACTIONS_TRAPDOORS(StatisticCategory.INTERACTIONS, "Trapdoors", "Trapdoors", Material.OAK_TRAPDOOR),
	INTERACTIONS_DROPPERS(StatisticCategory.INTERACTIONS, "Droppers", "Droppers", Material.DROPPER),
	INTERACTIONS_DISPENSERS(StatisticCategory.INTERACTIONS, "Dispensers", "Dispensers", Material.DISPENSER),
	INTERACTIONS_HOPPERS(StatisticCategory.INTERACTIONS, "Hoppers", "Hoppers", Material.HOPPER),
	INTERACTIONS_FURNACES(StatisticCategory.INTERACTIONS, "Furnaces", "Furnaces", Material.FURNACE),
	INTERACTIONS_CRAFTTABLES(StatisticCategory.INTERACTIONS, "CraftTables", "Crafting Tables", Material.CRAFTING_TABLE),
	
	/* Survival */
	SURVIVAL_FOODS_EATEN(StatisticCategory.SURVIVAL, "FoodsEaten", "Foods Eaten", Material.APPLE),
	SURVIVAL_HEARTS_REGAINED(StatisticCategory.SURVIVAL, "HeartsRegained", "Hearts Regained", Material.GOLDEN_APPLE),
	SURVIVAL_HEARTS_LOST(StatisticCategory.SURVIVAL, "HeartsLost", "Hearts Lost", Material.REDSTONE),
	SURVIVAL_ARMORS_WORN(StatisticCategory.SURVIVAL, "ArmorsWorn", "Armors Worn", Material.CHAINMAIL_HELMET),
	
	/* Damage */
	DAMAGE_PLAYERS(StatisticCategory.DAMAGE, "Players", "Players", Material.PLAYER_HEAD),
	DAMAGE_MOBS(StatisticCategory.DAMAGE, "Mobs", "Mobs", Material.SKELETON_SKULL),
	DAMAGE_ANIMALS(StatisticCategory.DAMAGE, "Animals", "Animals", Material.LEATHER),
	DAMAGE_VILLAGERS(StatisticCategory.DAMAGE, "Villagers", "Villagers", Material.EMERALD),
	
	/* World */
	WORLD_BLOCKS_PLACED(StatisticCategory.WORLD, "BlocksPlaced", "Blocks Placed", Material.BEDROCK),
	WORLD_BLOCKS_BROKEN(StatisticCategory.WORLD, "BlocksBroken", "Blocks Broken", Material.GLASS),
	WORLD_BLOCKS_IGNITED(StatisticCategory.WORLD, "BlocksIgnited", "Blocks Ignited", Material.FLINT_AND_STEEL),
	WORLD_ORES_MINED(StatisticCategory.WORLD, "OresMined", "Ores Mined", Material.IRON_ORE),
	WORLD_SEEDS_PLANTED(StatisticCategory.WORLD, "SeedsPlanted", "Seeds Planted", Material.CARROT),
	WORLD_FISHING_SUCCESSES(StatisticCategory.WORLD, "FishingSuccesses", "Fishing Successes", Material.FISHING_ROD),
	WORLD_TIMES_RESPAWNED(StatisticCategory.WORLD, "TimesRespawned", "Times Respawned", Material.DIRT);
	
	private StatisticCategory category;
	private String name;
	private String ingameName;
	private Material guiMaterial;
	
	StatisticType(StatisticCategory category, String name, String ingameName, Material guiMaterial) {
		this.category = category;
		this.name = name;
		this.ingameName = ingameName;
		this.guiMaterial = guiMaterial;
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
	public Material getGuiMaterial() {
		return guiMaterial;
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
