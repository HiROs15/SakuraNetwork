package dev.sakura.PlayerManager;

import java.io.File;

import dev.sakura.Config.Config;

public class PlayerData {
	public static PlayerData get() {
		return new PlayerData();
	}
	
	private String rank;
	private int coins;
	private int diamonds;
	private String uuid;
	
	public PlayerData setUUID(String uuid) {
		this.uuid = uuid;
		
		return this;
	}
	
	public String getUUID() {
		return this.uuid;
	}
	
	public PlayerData fetchStats() {
		Config c = Config.get().setFile(File.separator+"player-data"+File.separator+""+this.getUUID()+".dat");
		this.rank = c.getConfig().getString("rank");
		this.coins = c.getConfig().getInt("coins");
		this.diamonds = c.getConfig().getInt("diamonds");
		
		return this;
	}
	
	public String getRank() {
		return this.rank;
	}
	
	public int getCoins() {
		return this.coins;
	}
	
	public int getDiamonds() {
		return this.diamonds;
	}
}
