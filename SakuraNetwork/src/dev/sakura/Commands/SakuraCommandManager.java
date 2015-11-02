package dev.sakura.Commands;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import dev.sakura.Hub.Commands.Command_sethub;
import dev.sakura.PlayerManager.PlayerData;
import dev.sakura.PlayerManager.RankConvert;
import dev.sakura.Variables.ChatPrefix;

public class SakuraCommandManager {
	private ArrayList<SakuraCommand> commands = new ArrayList<SakuraCommand>();
	
	private ArrayList<String> enabled_commands = new ArrayList<String>();
	
	public static SakuraCommandManager get() {
		return new SakuraCommandManager();
	}
	
	public SakuraCommandManager() {
		//Add Commands Here!
		commands.add(new Command_sethub());
		
		//Add Enabled Commands HERE
		enabled_commands.add("reload");
		enabled_commands.add("stop");
	}
	
	public boolean handleCommands(Player player, String message) {
		String[] commanda = message.split(" ");
		String command = commanda[0].substring(1);
		
		String rank = PlayerData.get().setUUID(player.getUniqueId().toString()).fetchStats().getRank();
		
		for(SakuraCommand cmd : commands) {
			if(cmd.getCommand().equalsIgnoreCase(command)) {
				cmd.setupCommand(message);
				
				if(RankConvert.get().StringToRank(cmd.getMinimumRank()) > RankConvert.get().StringToRank(rank)) {
					player.sendMessage(cmd.noPermission());
					return false;
				}
				
				if(message.equalsIgnoreCase("/"+command)) {
					cmd.help(player);
				}
				else {
					cmd.command(player);
				}
				return false;
			}
		}
		
		for(String cmd : enabled_commands) {
			if(command.equalsIgnoreCase(cmd)) {
				return true;
			}
		}
		
		player.sendMessage(ChatPrefix.ERROR+"That command does not exist on this server.");
		return false;
	}
}
