package dev.sakura.Hub;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import dev.sakura.Main;
import dev.sakura.Managers;
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
			if(p.getName().equals(player.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public void join(Player player) {
		players.add(player);
		
		ResultSet aon = Managers.sakuraDB.query("SELECT * FROM members WHERE uuid='"+player.getUniqueId().toString()+"'");
		
		try {
			if(aon.getString("hub_hide_players").equals("on")) {
				hiddenPlayers.add(player);
			}
		} catch(Exception e) {}
	}
	
	public void leave(Player player) {
		for(Player p : players) {
			if(p.getName().equals(player.getName())) {
				players.remove(p);
				
				if(this.isPlayerHidden(p)) {
					hiddenPlayers.remove(p);
				}
			}
		}
	}
	
	public int getOnlinePlayers() {
		return this.players.size();
	}
	
	public boolean containsPlayer(Player player) {
		for(Player p : players) {
			if(p.getName().equals(p.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public void updateVisiblePlayers(Player player) {
		for(Player p : Main.plugin.getServer().getOnlinePlayers()) {
			if(this.containsPlayer(p) && this.isPlayerHidden(p)) {
				player.showPlayer(p);
				p.showPlayer(player);
			} else {
				player.hidePlayer(p);
				p.hidePlayer(player);
			}
		}
	}
	
	public void toggleHiddenPlayers(Player player) {
		Long time = (System.currentTimeMillis()/1000L);
		
		//Check if player can toggle again!
		for(Map.Entry<Player, Long> key : this.hidePlayersTimer.entrySet()) {
			if(key.getKey().getName().equals(player.getName())) {
				if(key.getValue() > time) {
					player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"HUB> "+ChatColor.RESET+""+ChatColor.GRAY+"You must wait 60 seconds before you can toggle players again.");
					return;
				} else {
					this.hidePlayersTimer.remove(key.getKey());
				}
			}
		}
		
		ResultSet aon = Managers.sakuraDB.query("SELECT * FROM members WHERE uuid='"+player.getUniqueId().toString()+"'");
		String state = "";
		
		try {
			aon.next();
			state = aon.getString("hubhideplayers");
		} catch(Exception e) {}
		
		if(state.equals("off")) {
			player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"HUB> "+ChatColor.RESET+""+ChatColor.GRAY+"You have turned on player visibility.");
			this.hiddenPlayers.add(player);
			Managers.sakuraDB.update("UPDATE members SET hubhideplayers='on' WHERE uuid='"+player.getUniqueId().toString()+"'");
			
			this.updateVisiblePlayers(player);
			
			HubManager.instance.updateTogglePlayersItem(player);
			
			this.hidePlayersTimer.put(player, (System.currentTimeMillis()/1000L)+60);
		}
		if(state.equals("on")) {
			player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"HUB> "+ChatColor.RESET+""+ChatColor.GRAY+"You have turned off player visibility.");

			for(Player p : hiddenPlayers) {
				if(p.getName().equals(player.getName())) {
					hiddenPlayers.remove(p);
				}
			}
			
			Managers.sakuraDB.update("UPDATE members SET hubhideplayers='off' WHERE uuid='"+player.getUniqueId().toString()+"'");
			
			this.updateVisiblePlayers(player);
			
			HubManager.instance.updateTogglePlayersItem(player);
			this.hidePlayersTimer.put(player, (System.currentTimeMillis()/1000L));
		}
	}
}
