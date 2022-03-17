package me.StatsCollector.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import me.StatsCollector.mysql.Mysql;

public class FileManager {
	
	public static File file = new File("plugins//StatsCollector//config.yml");
	public static YamlConfiguration cfgConfig = YamlConfiguration.loadConfiguration(file);
	
	public static String GuiOpen, Live,Kills,Deaths, Damage, World,Movement, Inventory,Activity,Survival,Interaction,Moderation, NoPermission;
	
	public static void create() {
		cfgConfig.options().copyDefaults(true);
		cfgConfig.addDefault("MySQL.host", "mysql.mc-host24.de");
		cfgConfig.addDefault("MySQL.port", "3306");
		cfgConfig.addDefault("MySQL.database", "db_256912");
		cfgConfig.addDefault("MySQL.username", "db_256912");
		cfgConfig.addDefault("MySQL.password", "dc32506fa4");
		
		cfgConfig.addDefault("Permission.GUI", "GUI.open");
		cfgConfig.addDefault("Permission.Live", "GUI.Live");
		cfgConfig.addDefault("Permission.Kills", "GUI.Kills");
		cfgConfig.addDefault("Permission.Deaths", "GUI.Deaths");
		cfgConfig.addDefault("Permission.Damage", "GUI.Damage");
		cfgConfig.addDefault("Permission.World", "GUI.World");
		cfgConfig.addDefault("Permission.Movement", "GUI.Movement");
		cfgConfig.addDefault("Permission.Inventory", "GUI.Inventory");
		cfgConfig.addDefault("Permission.Activity", "GUI.Activity");
		cfgConfig.addDefault("Permission.Survival", "GUI.Survival");
		cfgConfig.addDefault("Permission.Interaction", "GUI.Interaction");	
		cfgConfig.addDefault("Permission.Moderation", "GUI.Moderation");
		cfgConfig.addDefault("Permission.Not", "&cYou dont have the permission!");
		try {
			cfgConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		init();
	}

	private static void init() {
		Mysql.host = cfgConfig.getString("MySQL.host");
		
		try {
			Mysql.port = cfgConfig.getString("MySQL.port");
		} catch(NumberFormatException e) {
			System.out.println("[StatsCollector] config.yml -> MySQL.port is not a number");
		}
		
		Mysql.database = cfgConfig.getString("MySQL.database");
		Mysql.username = cfgConfig.getString("MySQL.username");
		Mysql.password = cfgConfig.getString("MySQL.password");
		
		GuiOpen = cfgConfig.getString("Permission.GUI");
		Live = cfgConfig.getString("Permission.Live");
		Kills = cfgConfig.getString("Permission.Kills");
		Deaths = cfgConfig.getString("Permission.Deaths");
		Damage = cfgConfig.getString("Permission.Damage");
		World = cfgConfig.getString("Permission.World");
		Movement = cfgConfig.getString("Permission.Movement");
		Inventory = cfgConfig.getString("Permission.Inventory");
		Activity = cfgConfig.getString("Permission.Activity");
		Survival = cfgConfig.getString("Permission.Survival");
		Interaction = cfgConfig.getString("Permission.Interaction");
		Moderation = cfgConfig.getString("Permission.Moderation");
		
		NoPermission = ChatColor.translateAlternateColorCodes('&', cfgConfig.getString("Permission.Not"));
	}
}