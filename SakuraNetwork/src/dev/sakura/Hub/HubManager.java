package dev.sakura.Hub;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import dev.sakura.Main;
import dev.sakura.Config.Config;

public class HubManager {
	public static HubManager instance;
	
	private ArrayList<Hub> hubs = new ArrayList<Hub>();
	
	public HubManager() {
		instance = this;
		
		this.setupHubs();
	}
	
	private void setupHubs() {
		Config c = Config.get().setFile(File.separator+"hubs"+File.separator+"hubs.yml");
		
		if(!c.getConfig().getBoolean("setup")) {
			Main.plugin.getLogger().warning("SakuraNetwork: Hubs need to be setup!");
			return;
		}
		
		for(String id : c.getConfig().getConfigurationSection("hubs").getKeys(false)) {
			hubs.add(new Hub(Integer.parseInt(id)));
		}
		
		Main.plugin.getLogger().info("All hubs are loaded.");
	}
	
	public Hub getHub(int id) {
		return hubs.get(id);
	}
	
	public boolean isPlayerInHub(Player player) {
		for(Hub h : hubs) {
			if(h.containsPlayer(player)) {
				return true;
			}
		}
		return false;
	}
	
	public void joinHub(Player player) {
		int hubid = this.findOpenHub();
		
		if(hubid == 0) {
			player.kickPlayer(ChatColor.RED+""+ChatColor.BOLD+"ERROR "+ChatColor.RESET+""+ChatColor.GRAY+"We were unable to send you to a hub server. Please try again.");
			return;
		}
	}
	
	public int findOpenHub() {
		boolean found = false;
		int rounds = 0;
		
		while(found == false) {
			int i = (int) Math.ceil(Math.random()*hubs.size());
			
			Main.plugin.getLogger().info("Id: "+i);
			
			if(getHub(i-1).getOnlinePlayers() < getHub(i-1).getMaxPlayers()) {
				found = true;
				return i;
			}
			
			if(rounds == 10) {
				found = false;
				return 0;
			}
			
			rounds++;
		}
		return 0;
	}
}
