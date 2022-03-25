package me.StatsCollector.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class Mysql {

	public static String host, port, database, username, password;

	public static Connection con;

	public static void connect() {
		if (!isConnect())
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?requireSSL=true&useSSL=false&sslMode=VERIFY_IDENTITY&enabledTLSProtocols=TLSv1.2", username, password);
				Bukkit.getConsoleSender().sendMessage("§7StatsCollector MYSQL is §2ONLINE!");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static void createTable(String nametable, String attribute) {
		if (isConnect())
			try {
				PreparedStatement ps = con.prepareStatement(
						"CREATE TABLE IF NOT EXISTS "+nametable+" ("+attribute+")");
				ps.executeUpdate();
				Bukkit.getConsoleSender().sendMessage("§7" +nametable+ " created succesfully!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static void createUser(String uuid, String nametable, String attribute, String values) throws SQLException {
		ResultSet rs = con.prepareStatement("SELECT * FROM "+nametable+" WHERE UUID='" + uuid + "'").executeQuery();
		if (rs.next())
			return;
		PreparedStatement ps = con
				.prepareStatement("INSERT INTO "+nametable+" ("+attribute+") VALUES ('" + uuid + "',"+values+")");
		ps.execute();
	}

	public static boolean isConnect() {
		return (con != null);
	}

	public static void end() {
		if (isConnect())
			try {
				con.close();
				Bukkit.getConsoleSender().sendMessage("§7StatsCollector MYSQL is §cOFFLINE!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}
