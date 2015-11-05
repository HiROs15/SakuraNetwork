package dev.sakura.Chat;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import dev.sakura.PlayerManager.PlayerData;
import dev.sakura.PlayerManager.RankConvert;

public class ChatManager {
	public static ChatManager get() {
		return new ChatManager();
	}
	
	public void sendMessage(Player sender, String message, ArrayList<Player> players) {
		String rank = PlayerData.get().setUUID(sender.getUniqueId().toString()).fetchStats().getRank();
		String prefix = RankConvert.get().RankToPrefix(rank);
		String msg = prefix+""+ChatColor.YELLOW+""+sender.getName()+""+ChatColor.WHITE+" "+message;
		
		for(Player p : players) {
			p.sendMessage(msg);
		}
	}
}
