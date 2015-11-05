package dev.sakura.Hub;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev.sakura.Main;
import dev.sakura.Managers;
import dev.sakura.Config.Config;

public class HubManager {
	public static HubManager instance;
	
	private ArrayList<Hub> hubs = new ArrayList<Hub>();
	
	public ArrayList<Material> blockedBlockInteractions = new ArrayList<Material>();
	
	public HubManager() {
		instance = this;
		
		this.setupHubs();
		
		blockedBlockInteractions.add(Material.TRAPPED_CHEST);
		blockedBlockInteractions.add(Material.CHEST);
		blockedBlockInteractions.add(Material.TRAP_DOOR);
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
		return hubs.get(id-1);
	}
	
	public Hub getHub(Player player) {
		for(Hub h : hubs) {
			if(h.containsPlayer(player) == true) {
				return h;
			}
		}
		return null;
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
		
		Hub hub = this.getHub(hubid);
		hub.join(player);
		
		player.teleport(hub.getSpawnLocation());
		
		player.setGameMode(GameMode.ADVENTURE);
		player.setHealth(20);
		player.setFoodLevel(20);
		player.getInventory().clear();
		
		this.setupHubInventory(player);
		
		hub.updateVisiblePlayers(player);
	}
	
	public int findOpenHub() {
		boolean found = false;
		int rounds = 0;
		
		while(found == false) {
			int i = (int) Math.ceil(Math.random()*hubs.size());
			
			if(getHub(i).getOnlinePlayers() < getHub(i).getMaxPlayers()) {
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
	
	private void setupHubInventory(Player player) {
		player.getInventory().clear();
		
		player.getInventory().setItem(0, new HubItem(Material.COMPASS, ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Minigame Selector", 1).registerItem().getItem());
		updateTogglePlayersItem(player);
	}
	
	public void leaveHub(Player player) {
		if(this.isPlayerInHub(player)) {
			for(Hub hub : hubs) {
				if(hub.containsPlayer(player)) {
					hub.leave(player);
				}
			}
		}
	}
	
	public void updateTogglePlayersItem(Player player) {
		ResultSet aon = Managers.sakuraDB.query("SELECT * FROM members WHERE uuid='"+player.getUniqueId().toString()+"'");
		String state = "";
		
		try {
			aon.next();
			state = aon.getString("hubhideplayers");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		if(state.equals("on")) {
			player.getInventory().setItem(2, new HubItem(Material.REDSTONE_TORCH_ON, ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Toggle Players"+ChatColor.RESET+""+ChatColor.GREEN+" (On)", 1).registerItem().getItem());
		}
		if(state.equals("off")) {
			player.getInventory().setItem(2, new HubItem(Material.REDSTONE_TORCH_ON, ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Toggle Players"+ChatColor.RESET+""+ChatColor.RED+" (Off)", 1).registerItem().getItem());
		}
	}
}
