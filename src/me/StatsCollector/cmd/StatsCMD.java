package me.StatsCollector.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.StatsCollector.utils.FileManager;
import me.StatsCollector.utils.Inventories;

public class StatsCMD implements CommandExecutor, TabCompleter {

	public static Inventory mainInventory;
	public static Inventory categoryInventory;
	public static Player targetPlayer;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		if (p.hasPermission(FileManager.GuiOpen)) {
			if (args.length == 0) {
				targetPlayer = p;
				mainInventory = Inventories.MainGui(p);
				p.openInventory(mainInventory);
			} else if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
					
				if(target != null) {
					targetPlayer = target;
					mainInventory = Inventories.MainGui(target);
					p.openInventory(mainInventory);	
				} else {
					p.sendMessage(FileManager.PlayerOffline);
				}
			}
		} else {
			p.sendMessage(FileManager.NoPermission);
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			List<String> options = new ArrayList<>();
			for(Player p : Bukkit.getOnlinePlayers()) {
				options.add(p.getName());
			}
			return options;
		}
		return new ArrayList<>();
	}
	
}