package dev.sakura.Hub;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import dev.sakura.Config.Config;
import dev.sakura.Libs.ConfigLocation;

public class Hub {
	private Location spawnLocation;
	private ArrayList<Player> players = new ArrayList<Player>();
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
	
	public void join(Player player) {
		players.add(player);
	}
	
	public void leave(Player player) {
		for(Player p : players) {
			if(p.getName().equals(player.getName())) {
				players.remove(p);
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
}
