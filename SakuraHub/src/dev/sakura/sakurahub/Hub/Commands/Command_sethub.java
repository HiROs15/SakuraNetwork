package dev.sakura.sakurahub.Hub.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import dev.sakura.Commands.SakuraCommand;
import dev.sakura.Config.ConfigManager;
import dev.sakura.Config.SakuraConfig;
import dev.sakura.Libs.ConfigLocation;

public class Command_sethub extends SakuraCommand {
	public Command_sethub() {
		super("sethub","developer");
	}
	
	private void setHub(Player player, String hubid, String serverName) {
		Location loc = player.getLocation();
		SakuraConfig config = ConfigManager.get().fetchClass("/hubs/hubs.yml");
		
		config.getConfig().set("hubs."+hubid+".serverName", serverName);
		config.getConfig().set("hubs."+hubid+".id", hubid);
		config.getConfig().set("hubs."+hubid+".maxPlayers", 50);
		config.saveConfig();
		
		ConfigLocation.get().LocationToConfig("hubs."+hubid+".spawn", "/hubs/hubs.yml", loc);
	}
	
	@Override
	public void command(Player player, String command, String[] args) {
		if(args.length == 1) {
			player.sendMessage(ChatColor.YELLOW+"========== "+ChatColor.BLUE+""+ChatColor.BOLD+"Command Help "+ChatColor.RESET+""+ChatColor.YELLOW+"==========");
		} else {
			this.setHub(player, args[1], args[2]);
			player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"SUCCESS> "+ChatColor.RESET+""+ChatColor.GRAY+"You have setup a hub with the ID: "+args[1]+" and a server name: "+args[2]+".");
		}
	}
}
