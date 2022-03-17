package me.StatsCollector.listeners;

import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.StatsCollector.mysql.Mysql;

public class Join implements Listener{

	@EventHandler
	public void on(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		try {
			Mysql.createUser(p.getUniqueId().toString(), "Kills", "UUID, Players, Mobs, Animals, Villagers", " 0, 0, 0, 0");
			Mysql.createUser(p.getUniqueId().toString(), "Deaths", "UUID, ByMobs, ByPlayers, BySuicide", " 0, 0, 0");
			Mysql.createUser(p.getUniqueId().toString(), "Activity", "UUID, Sent, Kicked, Executed, Joined, Quit", " 0, 0, 0, 0, 0");
			Mysql.createUser(p.getUniqueId().toString(), "World", "UUID, BlocksPlaced, BlocksBroken, BlocksIgnited, OresMined, SeedsPlanted, FishingSuccesses, TimesRespawned", " 0, 0, 0, 0, 0, 0, 0");
			Mysql.createUser(p.getUniqueId().toString(), "Movement", "UUID, Travelled, WorldVisited, Teleported", " 0, 0, 0");
			Mysql.createUser(p.getUniqueId().toString(), "Inventory", "UUID, PotionsMade, ItemsCrafted, FoodCooked, ItemsPicked, ItemsDropped", " 0, 0, 0, 0, 0");
			Mysql.createUser(p.getUniqueId().toString(), "Interactions", "UUID, Doors, Chests, Levers, FenceGates, Buttons, Trapdoors, Droppers, Dispensers, Hoppers, Furnaces, CraftTables", " 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0");
			Mysql.createUser(p.getUniqueId().toString(), "Survival", "UUID, FoodsEaten, HeartsRegained, HeartsLost, ArmorsWorn", " 0, 0, 0, 0");
			Mysql.createUser(p.getUniqueId().toString(), "Damage", "UUID, Players, Mobs, Animals, Villagers", " 0, 0, 0, 0");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
