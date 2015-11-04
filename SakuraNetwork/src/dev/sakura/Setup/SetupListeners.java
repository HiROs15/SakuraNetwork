package dev.sakura.Setup;

import java.sql.ResultSet;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.sakura.Managers;
import dev.sakura.Database.DBUtils;
import dev.sakura.Listeners.SakuraListener;

public class SetupListeners extends SakuraListener {
	public SetupListeners() {
		this.setInstance(this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		String uuid = player.getUniqueId().toString();
		
		ResultSet aon = Managers.sakuraDB.query("SELECT * FROM members WHERE uuid='"+uuid+"'");
		if(DBUtils.get().getRows(aon) == 0) {
			event.setJoinMessage("");
			PlayerSetup.get().setupPlayer(player);
		}
	}
}
