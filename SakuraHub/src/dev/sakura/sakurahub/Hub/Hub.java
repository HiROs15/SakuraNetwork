package dev.sakura.sakurahub.Hub;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import dev.sakura.Config.SakuraConfig;
import dev.sakura.Libs.ConfigLocation;
import dev.sakura.sakurahub.Configs.HubConfigManager;

public class Hub {
	private String serverName;
	private Location spawn;
	private int id;
	private ArrayList<HubPlayer> players = new ArrayList<HubPlayer>();
	private int maxPlayers = 50;
	
	public Hub(int id) {
		this.id = id;
		SakuraConfig config = HubConfigManager.get().fetchClass("/hubs/hubs.yml");
		this.serverName = config.getConfig().getString("hubs."+id+".serverName");
		this.spawn = ConfigLocation.get().ConfigToLocation("hubs."+id+".spawn", "/hubs/hubs.yml");
	}
	
	public Location getSpawn() {
		return this.spawn;
	}
	
	public String getServerName() {
		return this.serverName;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void join(Player player) {
		players.add(new HubPlayer(player));
	}
	
	public int getMaxPlayers() {
		return this.maxPlayers;
	}
	
	public void leave(Player player) {
		Iterator<HubPlayer> it = players.iterator();
		while(it.hasNext()) {
			HubPlayer p = it.next();
			if(p.getPlayer().getUniqueId().toString().equals(player.getUniqueId().toString())) {
				it.remove();
			}
		}
	}
	
	public boolean containsPlayer(Player player) {
		Iterator<HubPlayer> it = players.iterator();
		while(it.hasNext()) {
			HubPlayer p = it.next();
			if(p.getPlayer().getUniqueId().toString().equals(player.getUniqueId().toString())) {
				return true;
			}
		}
		return false;
	}
}
