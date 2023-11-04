package dev.maxprime.db;

public class PlayerStats {
	
	private String uuid;
	private int coins;
	private int xp;
	private int wins;
	
	public PlayerStats(String uuid, int coins, int xp, int wins) {
		this.uuid = uuid;
		this.coins = coins;
		this.xp = xp;
		this.wins = wins;
	}
	
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public int getXp() {
		return xp;
	}
	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	

}
