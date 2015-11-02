package dev.sakura.Setup;

import java.io.File;

import org.bukkit.entity.Player;

import com.connorlinfoot.titleapi.TitleAPI;

import dev.sakura.Main;
import dev.sakura.Config.Config;
import dev.sakura.Variables.StartingStats;

public class PlayerSetup {
	public static PlayerSetup get() {
		return new PlayerSetup();
	}
	
	public void setupPlayer(Player player) {
		Config c = Config.get().setFile(File.separator+"player-data"+File.separator+""+player.getUniqueId().toString()+".dat");
		c.getConfig().set("rank", "member");
		c.getConfig().set("coins", 0);
		c.getConfig().set("diamonds", StartingStats.get().start_diamonds);
		c.saveConfig();
		
		this.displayWelcomeMessage(player);
	}
	
	private void displayWelcomeMessage(Player player) {
		TitleAPI.sendTitle(player, 1, 10, 1, StartingStats.get().title, StartingStats.get().subtitle);
		Main.plugin.getLogger().warning("Does This Work!");
	}
}
