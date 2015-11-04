package dev.sakura.PlayerManager;

import java.sql.ResultSet;

import dev.sakura.Managers;
import dev.sakura.Database.DBUtils;

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
		ResultSet aon = Managers.sakuraDB.query("SELECT * FROM members WHERE uuid='"+this.uuid+"'");
		if(DBUtils.get().getRows(aon) == 0) {
			return null;
		}
		
		try {
			this.rank = aon.getString("rank");
			this.coins = aon.getInt("coins");
			this.diamonds = aon.getInt("diamonds");
		} catch(Exception e) {}
		
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
