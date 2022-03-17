package me.StatsCollector.utils.apis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.StatsCollector.mysql.Mysql;
import me.StatsCollector.utils.statistics.StatisticCategory;
import me.StatsCollector.utils.statistics.StatisticType;

public class StatsCollectorAPI {
	
	public static StatisticType getStatisticByName(String name) {
		for(StatisticType statistic : StatisticType.values()) {
			if(statistic.getName().equalsIgnoreCase(name)) return statistic;
		}
		return null;
	}
	public static ArrayList<StatisticType> getStatisticsByCategory(StatisticCategory category) {
		ArrayList<StatisticType> output = new ArrayList<>();
		for(StatisticType statistic : StatisticType.values()) {
			if(statistic.getCategory() == category) 
				output.add(statistic);
		}
		
		if(output == null || output.isEmpty())
			return null;
		
		return output;
	}
	
	public static void resetAll(Player player) {
		for(StatisticType statistic : StatisticType.values()) {
			statistic.reset(player);
		}
	}
	public static void deleteAll(Player player) throws SQLException {
		for(StatisticType statistic : StatisticType.values()) {
			ResultSet rs = Mysql.con.prepareStatement("SELECT * FROM "+statistic.getCategory().getName()+" WHERE UUID ='" + player.getUniqueId().toString() + "'").executeQuery();
			if(rs.next()) return;
			PreparedStatement ps = Mysql.con.prepareStatement("DELETE * FROM "+statistic.getCategory().getName()+" WHERE UUID ='" + player.getUniqueId().toString() + "'");
			ps.execute();
		}
	}
	public static void delete(StatisticType statistic, Player player) throws SQLException {
		ResultSet rs = Mysql.con.prepareStatement("SELECT * FROM "+statistic.getCategory().getName()+" WHERE UUID ='" + player.getUniqueId().toString() + "'").executeQuery();
		if(rs.next()) return;
		PreparedStatement ps = Mysql.con.prepareStatement("DELETE * FROM "+statistic.getCategory().getName()+" WHERE UUID ='" + player.getUniqueId().toString() + "'");
		ps.execute();
	}

}
