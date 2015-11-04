package dev.sakura.Setup;

import java.sql.ResultSet;

import org.bukkit.entity.Player;

import com.connorlinfoot.titleapi.TitleAPI;

import dev.sakura.Managers;
import dev.sakura.Database.DBUtils;
import dev.sakura.Hub.HubManager;
import dev.sakura.Variables.StartingStats;

public class PlayerSetup {
	public static PlayerSetup get() {
		return new PlayerSetup();
	}
	
	public void setupPlayer(Player player) {
		ResultSet aon = Managers.sakuraDB.query("SELECT * FROM members WHERE uuid='"+player.getUniqueId().toString()+"'");
		
		if(DBUtils.get().getRows(aon) == 0) {
			Managers.sakuraDB.update("INSERT INTO members (uuid, coins, diamonds) VALUES ('"+player.getUniqueId().toString()+"','0','"+StartingStats.get().start_diamonds+"')");
			this.displayWelcomeMessage(player);
			HubManager.instance.joinHub(player);
		}
	}
	
	private void displayWelcomeMessage(Player player) {
		TitleAPI.sendTitle(player, 20, 100, 20, StartingStats.get().title, StartingStats.get().subtitle);
	}
}
