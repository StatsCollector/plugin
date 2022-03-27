package me.StatsCollector.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.StatsCollector.mysql.Mysql;

public class FileManager {

	public static File file = new File("plugins//StatsCollector//config.yml");
	public static YamlConfiguration cfgConfig = YamlConfiguration.loadConfiguration(file);

	public static HashMap<Player, Long> TimeSinceJoined = new HashMap<>();
	
	public static String GuiOpen, UpdateChecker, NoPermission, PlayerOffline;

	public static void create() {
		cfgConfig.options().copyDefaults(true);
		cfgConfig.addDefault("MySQL.host", "localhost");
		cfgConfig.addDefault("MySQL.port", "3306");
		cfgConfig.addDefault("MySQL.database", "database");
		cfgConfig.addDefault("MySQL.username", "username");
		cfgConfig.addDefault("MySQL.password", "password");

		cfgConfig.addDefault("Permission.GUI", "statscollector.gui.open");
		cfgConfig.addDefault("Permission.UpdateChecker", "statscollector.updateChecker");

		cfgConfig.addDefault("Messages.NoPermission", "&cYou dont have the permission!");
		cfgConfig.addDefault("Messages.PlayerOffline", "&cThis player is offline!");

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
		} catch (NumberFormatException e) {
			System.out.println("[StatsCollector] config.yml -> MySQL.port is not a number");
		}

		Mysql.database = cfgConfig.getString("MySQL.database");
		Mysql.username = cfgConfig.getString("MySQL.username");
		Mysql.password = cfgConfig.getString("MySQL.password");

		GuiOpen = cfgConfig.getString("Permission.GUI");
		UpdateChecker = cfgConfig.getString("Permission.UpdateChecker");

		PlayerOffline = ChatColor.translateAlternateColorCodes('&', cfgConfig.getString("Messages.PlayerOffline"));
		NoPermission = ChatColor.translateAlternateColorCodes('&', cfgConfig.getString("Messages.NoPermission"));
	}

	public static String timePlayer(Long joindate) {
		Long now = System.currentTimeMillis();
		Long date = now - joindate;

		long seconds = date / 1000 % 60;
		long minutes = date / (60 * 1000) % 60;
		long hours = date / (60 * 60 * 1000) % 24;
		long days = date / (24 * 60 * 60 * 1000);

		return days + "d " + hours + "h " + minutes + "m " + seconds + "s";
	}

}
