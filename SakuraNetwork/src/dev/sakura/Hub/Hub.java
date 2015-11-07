package dev.sakura.Hub;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import dev.sakura.Config.Config;
import dev.sakura.Libs.ConfigLocation;

public class Hub {
	private Location spawnLocation;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Player> hiddenPlayers = new ArrayList<Player>();
	private HashMap<Player, Long> hidePlayersTimer = new HashMap<Player, Long>();
	private int id;
	private String serverName;
	private int maxPlayers = 50;
	
	public Hub(int id) {
		Config c = Config.get().setFile(File.separator+"hubs"+File.separator+"hubs.yml");
		
		this.id = id;
		
		this.spawnLocation = ConfigLocation.get().ConfigToLocation("hubs."+id+".spawn", File.separator+"hubs"+File.separator+"hubs.yml");
		
		this.serverName = c.getConfig().getString("hubs."+id+".server_name");
	}
	
	public Location getSpawnLocation() {
		return this.spawnLocation;
	}
	
	public int getHubId() {
		return this.id;
	}
	
	public int getMaxPlayers() {
		return this.maxPlayers;
	}
	
	public String getHubServerName() {
		return this.serverName;
	}
	
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	public ArrayList<Player> getHiddenPlayers() {
		return this.hiddenPlayers;
	}
	
	public boolean isPlayerHidden(Player player) {
		for(Player p : hiddenPlayers) {
			if(p.getUniqueId().toString().equals(player.getUniqueId().toString())) {
				return true;
			}
		}
		return false;
	}
	
	public void join(Player player) {
		players.add(player);
		
		this.updateVisiblePlayers(player);
	}
	
	public void leave(Player player) {
		Iterator<Player> it = this.players.iterator();
		while(it.hasNext()) {
			Player p = it.next();
			if(p.getUniqueId().toString().equals(player.getUniqueId().toString())) {
				it.remove();
			}
		}
	}
	
	public int getOnlinePlayers() {
		return this.players.size();
	}
	
	public boolean containsPlayer(Player player) {
		for(Player p : players) {
			if(p.getUniqueId().toString().equals(player.getUniqueId().toString())) {
				return true;
			}
		}
		return false;
	}
	
	private void updateVisiblePlayers(Player player) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.hidePlayer(player);
			player.hidePlayer(p);
		}
		
		for(Player p : this.players) {
			p.showPlayer(player);
			player.showPlayer(p);
		}
		
		if(this.isPlayerHidden(player)) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				player.hidePlayer(p);
			}
		}
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(this.containsPlayer(p)) {
				if(this.isPlayerHidden(p)) {
					p.hidePlayer(player);
				}
			}
		}
	}
	
	public void togglePlayers(Player player) {
		Long time = (System.currentTimeMillis()/1000L);
		
		for(Map.Entry<Player, Long> key : this.hidePlayersTimer.entrySet()) {
			Player p = key.getKey();
			if(p.getUniqueId().toString().equals(player.getUniqueId().toString())) {
				if(key.getValue() > time) {
					player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"HUB> "+ChatColor.RESET+""+ChatColor.GRAY+"You must wait 60 seconds before you can toggle players again!");
					return;
				}
				else {
					this.hidePlayersTimer.remove(key);
				}
			}
		}
		
		if(this.isPlayerHidden(player) == true) {
			Iterator<Player> it = this.hiddenPlayers.iterator();
			while(it.hasNext()) {
				Player p = it.next();
				if(p.getUniqueId().toString().equals(player.getUniqueId().toString())) {
					it.remove();
				}
			}
			
			player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"HUB> "+ChatColor.RESET+""+ChatColor.GRAY+"Players are now visible.");
			this.updateVisiblePlayers(player);
			HubManager.instance.updatePlayersItem(player);
			
			this.hidePlayersTimer.put(player, time+60);
		}
		else {
			this.hiddenPlayers.add(player);
			
			player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"HUB> "+ChatColor.RESET+""+ChatColor.GRAY+"Players are now hidden.");
			this.updateVisiblePlayers(player);
			HubManager.instance.updatePlayersItem(player);
			
			this.hidePlayersTimer.put(player, time+60);
		}
	}
}
