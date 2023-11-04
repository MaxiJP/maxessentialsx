package dev.maxprime;

// Bukkit/Spigot imports
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import dev.maxprime.db.MaxDB;
import dev.maxprime.commands.FlyCommand;
import dev.maxprime.commands.GamemodeCommand;
import dev.maxprime.commands.TestCommand;

// LuckPerms API Imports
// TODO: Replace this with a new MaxPerms API, but the plugin doesn't even exist yet, so I shall hold me horses on that.
//import net.luckperms.api.LuckPerms;
//import net.luckperms.api.LuckPermsProvider;
//import net.luckperms.api.cacheddata.CachedMetaData;

public class MaxEssentialsX extends JavaPlugin implements Listener {
	
	public String prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"));
	public double spawnx = getConfig().getDouble("spawnx");
	public double spawny = getConfig().getDouble("spawny");
	public double spawnz = getConfig().getDouble("spawnz");
	public String ConfigSlock = getConfig().getString("slocked");
	public boolean isSlocked;
//	LuckPerms api;
	
	public String getPrefix() {
		return prefix;
	}
	
	public void msxPrint(String message) {
		getLogger().info(message);
	}

	public void isSlocked(String s) {
		if (s == "y") {
			isSlocked = true;
		} else if (s == "n") {
			isSlocked = false;
		}
	}
	
	public Location getSpawn(World world) {
		Location spawn = new Location(world, spawnx, spawny, spawnz, 180f, 0f);
		return spawn;
	}
	
	@Override
	public void onEnable() {
//		api = LuckPermsProvider.get();
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		MaxDB.maxDBinit();
		
		msxPrint(prefix + "\\u001B[34mSetting up commands...");
		
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("maxessentialsx").setExecutor(new TestCommand());
		getCommand("msx").setExecutor(new TestCommand());
		getCommand("gmc").setExecutor(new GamemodeCommand());
		getCommand("gms").setExecutor(new GamemodeCommand());
		getCommand("gma").setExecutor(new GamemodeCommand());
		getCommand("gmsp").setExecutor(new GamemodeCommand());
		getCommand("maxfly").setExecutor(new FlyCommand());
		
		msxPrint(prefix + "Done! Ur good to go Max!");
	}
	
	@Override
	public void onDisable() {
		msxPrint("Max says goodbye. =[");
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (isSlocked) {
			p.kickPlayer(prefix + ChatColor.WHITE + "Sorry, MaxPvP is currently slocked for development.");
		} else {
			e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&9>&a>&f Hiya! &f" + p.getDisplayName() + "&f has joined the lobby. &a <&9<"));
			p.playSound(getSpawn(p.getWorld()), Sound.FIREWORK_TWINKLE, 1f, 1f);
			p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&9 Welcome, " + p.getDisplayName() + "!"), ChatColor.translateAlternateColorCodes('&', "&aTo the MaxPvP Server. Have fun!"));
		}
	}
	
	@EventHandler
	public void onJoinEvent(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&9>&a>&f " + p.getDisplayName() + "&f has left the lobby. &a <&9<"));
	}
	
	@EventHandler
	public void formatChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
	    e.setFormat(ChatColor.translateAlternateColorCodes('&', p.getDisplayName() + "&f: "  + "%2$s"));
	}
	
	@EventHandler
	public void onPlayerSpawnEvent(PlayerSpawnLocationEvent e) {
		Player p = e.getPlayer();
		e.setSpawnLocation(getSpawn(p.getWorld()));
	}
	
}
