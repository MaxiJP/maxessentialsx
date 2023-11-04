package dev.maxprime.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MaxDB {
	
	private static Plugin msx = Bukkit.getServer().getPluginManager().getPlugin("MaxEssentialsX");
	private static String prefix = msx.getConfig().getString("prefix");
	
	public static Connection connection = null;
	
	public static void maxDBinit() {
		
		String url = "jdbc:mysql://localhost/msx";
		String user = "root";
		String password = "";
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			
			msx.getLogger().info(prefix + "MaxDB connected successfully! Creating tables (if needed)...");
			
			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS maxessentials_stats(uuid varchar(36) primary key, coins int, xp int, wins int)";
			statement.execute(sql);
			
			statement.close();
			
			msx.getLogger().info(prefix + "Done! MaxDB should be configured.");
			
		} catch (SQLException e) {
			msx.getLogger().info("Shoot.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	
	
}
