package dev.sakura.Hub.Commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import dev.sakura.Commands.SakuraCommand;
import dev.sakura.Config.Config;
import dev.sakura.Libs.ConfigLocation;

public class Command_sethub extends SakuraCommand {
	public Command_sethub() {
		this.setCommand("sethub");
		this.setRank("developer");
	}
	
	@Override
	public void help(Player player) {
		player.sendMessage(ChatColor.YELLOW+"========== "+ChatColor.GREEN+""+ChatColor.BOLD+"Command Help "+ChatColor.RESET+""+ChatColor.YELLOW+"==========");
		player.sendMessage(ChatColor.AQUA+"Usage: "+ChatColor.WHITE+"/sethub [hub id] [hub server name]");
		player.sendMessage(ChatColor.GRAY+"This will set a hub spawn at your standing location.");
	}
	
	@Override
	public void command(Player player) {
		String[] args = this.getArgs();
		
		Config c = Config.get().setFile(File.separator+"hubs"+File.separator+"hubs.yml");
		
		if(c.getConfig().getBoolean("hubs."+args[1]+".setup")) {
			player.sendMessage(ChatColor.RED+"This hub is already setup. Use /delhub to reset it.");
			return;
		}
		
		Location loc = player.getLocation();
		
		if(args.length < 3) {
			player.sendMessage(ChatColor.RED+"You're missing arguments. Please use help if needed.");
			return;
		}
		
		c.getConfig().set("hubs."+args[1]+".server_name", args[2]);
		c.getConfig().set("hubs."+args[1]+".setup", true);
		
		// Gloabal setup
		c.getConfig().set("setup", true);
		
		c.saveConfig();
		
		ConfigLocation.get().LocationToConfig("hubs."+args[1]+".spawn", File.separator+"hubs"+File.separator+"hubs.yml", loc);
		
		player.sendMessage(ChatColor.GREEN+"Hub has been setup. All hubs have been reloaded.");
	}
}
