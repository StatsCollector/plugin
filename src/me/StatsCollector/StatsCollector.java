package me.StatsCollector;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.StatsCollector.cmd.StatsCMD;
import me.StatsCollector.listeners.*;
import me.StatsCollector.mysql.Mysql;
import me.StatsCollector.utils.FileManager;
import me.StatsCollector.utils.bstats.Metrics;

public class StatsCollector extends JavaPlugin {

	@Override
	public void onEnable() {
		FileManager.create();
		Mysql.connect();
		createTables();
		initListener();
		initCommands();

		new Metrics(this, 14620);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		Mysql.end();
		super.onDisable();
	}
	
	private void createTables() {
		Mysql.createTable("Activity", "UUID VARCHAR(64), Sent INT, Kicked INT, Executed INT, Joined INT, Quit INT");
		Mysql.createTable("Damage", "UUID VARCHAR(64), Players FLOAT, Mobs FLOAT, Animals FLOAT, Villagers FLOAT");
		Mysql.createTable("Deaths", "UUID VARCHAR(64), ByMobs INT, ByPlayers INT, BySuicide INT");
		Mysql.createTable("Interactions", "UUID VARCHAR(64), Doors INT, Chests INT, Levers INT, FenceGates INT, Buttons INT, Trapdoors INT, Droppers INT, Dispensers INT, Hoppers INT, Furnaces INT, CraftTables INT");
		Mysql.createTable("Inventory", "UUID VARCHAR(64), PotionsMade INT, ItemsCrafted INT, FoodCooked INT, ItemsPicked INT, ItemsDropped INT");
		Mysql.createTable("Kills", "UUID VARCHAR(64), Players INT, Mobs INT, Animals INT, Villagers INT");
		Mysql.createTable("Movement", "UUID VARCHAR(64), Travelled INT, WorldVisited INT, Teleported INT");
		Mysql.createTable("Survival", "UUID VARCHAR(64), FoodsEaten INT, HeartsRegained FLOAT, HeartsLost FLOAT, ArmorsWorn INT");
		Mysql.createTable("World", "UUID VARCHAR(64), BlocksPlaced INT, BlocksBroken INT, BlocksIgnited INT, OresMined INT, SeedsPlanted INT, FishingSuccesses INT, TimesRespawned INT");
	}
	
	private void initListener() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Activity(), this);
		pm.registerEvents(new Damage(), this);
		pm.registerEvents(new Deaths(), this);
		pm.registerEvents(new Interactions(), this);
		pm.registerEvents(new Inventory(), this);
		pm.registerEvents(new Join(), this);
		pm.registerEvents(new Kills(), this);
		pm.registerEvents(new Movement(), this);
		pm.registerEvents(new Survival(), this);
		pm.registerEvents(new World(), this);
	}
	
	private void initCommands() {
		getCommand("stats").setExecutor(new StatsCMD());
		getCommand("stats").setTabCompleter(new StatsCMD());
	}
	
}
