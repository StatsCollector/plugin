package me.StatsCollector.utils.apis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.StatsCollector.mysql.Mysql;

public class CounterAPI {

	public static int get(String nametable, String uuid, String thing) {
		try {
			PreparedStatement st = Mysql.con.prepareStatement("SELECT * FROM " + nametable + " WHERE UUID = ?");
			st.setString(1, uuid);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				return rs.getInt(thing);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public static void set(String nametable, String uuid, String thing, int amount) {
		try {
			PreparedStatement st = Mysql.con
					.prepareStatement("UPDATE " + nametable + " SET " + thing + " = ? WHERE UUID = ?");
			st.setString(2, uuid);
			st.setInt(1, amount);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void add(String nametable, String uuid, String thing, int amount) {
		set(nametable, uuid, thing, amount + get(nametable, uuid, thing));
	}
	public static void remove(String nametable, String uuid, String thing, int amount) {
		set(nametable, uuid, thing, get(nametable, uuid, thing) - amount);
	}

	public static float getFloat(String nametable, String uuid, String thing) {
		try {
			PreparedStatement st = Mysql.con.prepareStatement("SELECT * FROM " + nametable + " WHERE UUID = ?");
			st.setString(1, uuid);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				return rs.getFloat(thing);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public static void setFloat(String nametable, String uuid, String thing, float amount) {
		try {
			PreparedStatement st = Mysql.con
					.prepareStatement("UPDATE " + nametable + " SET " + thing + " = ? WHERE UUID = ?");
			st.setString(2, uuid);
			st.setFloat(1, amount);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void addFloat(String nametable, String uuid, String thing, float amount) {
		setFloat(nametable, uuid, thing, amount + getFloat(nametable, uuid, thing));
	}
	public static void removeFloat(String nametable, String uuid, String thing, float amount) {
		setFloat(nametable, uuid, thing, getFloat(nametable, uuid, thing) - amount);
	}

}
