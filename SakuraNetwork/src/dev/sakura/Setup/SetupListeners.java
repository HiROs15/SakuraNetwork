package dev.sakura.Setup;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.sakura.Main;
import dev.sakura.Listeners.SakuraListener;

public class SetupListeners extends SakuraListener {
	public SetupListeners() {
		this.setInstance(this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		String uuid = player.getUniqueId().toString();
		
		//Switch to database
		
		File fp = new File(Main.plugin.getDataFolder()+""+File.separator+"player-data"+""+File.separator+""+uuid+".dat");
		if(!fp.exists()) {
			PlayerSetup.get().setupPlayer(player);
		}
	}
}
