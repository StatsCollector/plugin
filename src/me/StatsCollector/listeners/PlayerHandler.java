package me.StatsCollector.listeners;

import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import me.StatsCollector.mysql.Mysql;
import me.StatsCollector.utils.FileManager;
import me.StatsCollector.utils.MaterialUtils;
import me.StatsCollector.utils.statistics.StatisticType;

public class PlayerHandler implements Listener {
	
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
		
		FileManager.TimeSinceJoined.put(p, System.currentTimeMillis());
		StatisticType.ACTIVITY_JOINED.add(e.getPlayer(), 1);
	}
	
	@EventHandler
	
	public void on(PlayerQuitEvent e) {
		FileManager.TimeSinceJoined.remove(e.getPlayer());
		StatisticType.ACTIVITY_QUIT.add(e.getPlayer(), 1);
	}
	
	@EventHandler
	
	public void on(PlayerKickEvent e) {
		StatisticType.ACTIVITY_KICKED.add(e.getPlayer(), 1);
	}
	
	@EventHandler
	public void on(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();

		StatisticType.ACTIVITY_EXECUTED.add(p, 1);

		int totalDistanceTravelled = ((p.getStatistic(Statistic.WALK_ONE_CM) / 100)
				+ (p.getStatistic(Statistic.WALK_UNDER_WATER_ONE_CM) / 100)
				+ (p.getStatistic(Statistic.WALK_ON_WATER_ONE_CM) / 100)
				+ (p.getStatistic(Statistic.SPRINT_ONE_CM) / 100) + (p.getStatistic(Statistic.CROUCH_ONE_CM) / 100)
				+ (p.getStatistic(Statistic.CLIMB_ONE_CM) / 100) + (p.getStatistic(Statistic.FALL_ONE_CM) / 100)
				+ (p.getStatistic(Statistic.FLY_ONE_CM) / 100));
		
		if(e.getMessage().contains("stats")) 
			StatisticType.MOVEMENT_TRAVELLED.add(p, totalDistanceTravelled);
		
		if(e.getMessage().equalsIgnoreCase("kill") || e.getMessage().equalsIgnoreCase("kill " + e.getPlayer().getName()))
			StatisticType.DEATHS_BY_SUICIDE.add(e.getPlayer(), 1);
	}
	
	@EventHandler
	public void on(AsyncPlayerChatEvent e) {
		StatisticType.ACTIVITY_SENT.add(e.getPlayer(), 1);
	}
	
	@EventHandler
	public void on(PlayerFishEvent e) {
		if(e.getCaught() != null) {
			StatisticType.WORLD_FISHING_SUCCESSES.add(e.getPlayer(), 1);

		}
	}
	
	@EventHandler
	public void on(PlayerRespawnEvent e) {
		StatisticType.WORLD_TIMES_RESPAWNED.add(e.getPlayer(), 1);
	}
	
	@EventHandler
	public void on(PlayerTeleportEvent e) {
		StatisticType.MOVEMENT_TELEPORTED.add(e.getPlayer(), 1);
	}
	
	@EventHandler
	public void on(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			if (e.getClickedBlock().getType() == Material.CHEST
					|| e.getClickedBlock().getType() == Material.CHEST_MINECART) {
				StatisticType.INTERACTIONS_CHESTS.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.LEVER) {
				StatisticType.INTERACTIONS_LEVERS.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.DROPPER) {
				StatisticType.INTERACTIONS_DROPPERS.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.DISPENSER) {
				StatisticType.INTERACTIONS_DISPENSERS.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.HOPPER) {
				StatisticType.INTERACTIONS_HOPPERS.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.FURNACE
					|| e.getClickedBlock().getType() == Material.FURNACE_MINECART) {
				StatisticType.INTERACTIONS_FURNACES.add(e.getPlayer(), 1);
			} else if (e.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
				StatisticType.INTERACTIONS_CRAFTTABLES.add(e.getPlayer(), 1);
			} else {
				for (Material mat : MaterialUtils.doors) {
					if (e.getClickedBlock().getType() == mat) {
						StatisticType.INTERACTIONS_DOORS.add(e.getPlayer(), 1);
					}
				}

				for (Material mat : MaterialUtils.trapdoor) {
					if (e.getClickedBlock().getType() == mat) {
						StatisticType.INTERACTIONS_TRAPDOORS.add(e.getPlayer(), 1);
					}
				}

				for (Material mat : MaterialUtils.fencegates) {
					if (e.getClickedBlock().getType() == mat) {
						StatisticType.INTERACTIONS_FENCEGATES.add(e.getPlayer(), 1);
					}
				}

				for (Material mat : MaterialUtils.buttons) {
					if (e.getClickedBlock().getType() == mat) {
						StatisticType.INTERACTIONS_BUTTONS.add(e.getPlayer(), 1);
					}
				}
			}
		}
		
		if (e.getItem() != null) {
			for (Material mat : MaterialUtils.armors) {
				if (e.getItem().getType() == mat) {
					StatisticType.SURVIVAL_ARMORS_WORN.add(e.getPlayer(), 1);
				}
			}
		}
	}
	
	@EventHandler
	public void on(PlayerDropItemEvent e) {
			StatisticType.INVENTORY_ITEMS_DROPPED.add((Player) e.getPlayer(), e.getItemDrop().getItemStack().getAmount());
	}
	
	@EventHandler
	public void on(PlayerItemConsumeEvent e) {
		for (Material mat : MaterialUtils.foods) {
			if (e.getItem().getType() == mat) {
				StatisticType.SURVIVAL_FOODS_EATEN.addFloat(e.getPlayer(), 1);
			}
		}
	}
}
