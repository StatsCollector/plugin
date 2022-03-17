package me.StatsCollector.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.StatsCollector.utils.FileManager;
import me.StatsCollector.utils.apis.ItemBuilder;
import me.StatsCollector.utils.apis.StatsCollectorAPI;
import me.StatsCollector.utils.statistics.StatisticCategory;
import me.StatsCollector.utils.statistics.StatisticType;

public class StatsCMD implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		Player p = (Player) sender;
		if (p.hasPermission(FileManager.GuiOpen)) {
			if (args.length == 0) {
				p.openInventory(openGuiInv(p));
				// sendAllStats(p, p);
				return true;
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reset")) {
					StatsCollectorAPI.resetAll(p);
					p.sendMessage("§cRESET ALL STATISTICS!");
					return true;
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("reset")) {
					if (isStatistic(args[1])) {
						StatisticType statistic = StatsCollectorAPI.getStatisticByName(args[1]);
						statistic.reset(p);
						p.sendMessage(
								"§cRESET " + statistic.getCategory().getName() + " - " + statistic.getName() + "!");
						return true;
					} else {
						Player target = Bukkit.getPlayer(args[1]);

						if (target != null) {
							StatsCollectorAPI.resetAll(target);
							target.sendMessage("§cRESET ALL STATISTICS!");
							p.sendMessage("§cRESET ALL STATISTICS FROM " + target.getName() + "!");
							return true;
						} else {
							p.sendMessage("§cPlayer not found");
							return false;
						}
					}
				}
			} else if (args.length == 3) {
				if (args[0].equalsIgnoreCase("reset")) {
					if (isStatistic(args[1])) {
						Player target = Bukkit.getPlayer(args[1]);

						if (target != null) {
							StatisticType statistic = StatsCollectorAPI.getStatisticByName(args[1]);
							statistic.reset(target);
							target.sendMessage(
									"§cRESET " + statistic.getCategory().getName() + " - " + statistic.getName() + "!");
							p.sendMessage("§cRESET " + statistic.getCategory().getName() + " - " + statistic.getName()
									+ " FROM " + target.getName() + "!");
							return true;
						} else {
							p.sendMessage("§cPlayer not found");
							return false;
						}
					}
				}
			}
		} else
			p.sendMessage(FileManager.NoPermission);
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			ArrayList<String> options = new ArrayList<>();
			options.add("reset");

			for (Player p : Bukkit.getOnlinePlayers()) {
				options.add(p.getName());
			}

			for (StatisticType statistic : StatisticType.values()) {
				options.add(statistic.getName());
			}

			return options;
		}
		return new ArrayList<>();
	}

	private boolean isStatistic(String arg) {
		for (StatisticType statistic : StatisticType.values()) {
			if (statistic.getName().equalsIgnoreCase(arg))
				return true;
		}
		return false;
	}

	private Inventory openGuiInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "§7Stats: " + p.getName());

		inv.setItem(0,
				new ItemBuilder(Material.CLOCK).setName("§7Lives").addLoreLine("§k").addLoreLine("§7Events Listed")
						.addLoreLine("§c - Time Spent").addLoreLine("§c - Health").addLoreLine("§c - Food Level")
						.addLoreLine("§c - Inventory Items").addLoreLine("§c - First Time Joined").toItemStack());

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

		return inv;

	}

}