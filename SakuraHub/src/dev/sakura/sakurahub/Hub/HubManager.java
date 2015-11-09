package dev.sakura.sakurahub.Hub;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import dev.sakura.Config.ConfigManager;
import dev.sakura.Config.SakuraConfig;
import dev.sakura.PlayerData.PlayerData;
import dev.sakura.PlayerManager.RankConvert;
import dev.sakura.sakurahub.SakuraHub;
import dev.sakura.sakurahub.Libs.WorldHandlers;

public class HubManager {
	private static HubManager instance;
	
	public HubManager() {
		instance = this;
		
		this.loadHubs();
	}
	
	public static HubManager get() {
		return instance;
	}
	
	private ArrayList<Hub> hubs = new ArrayList<Hub>();
	
	public Hub getHub(int id) {
		Iterator<Hub> it = hubs.iterator();
		while(it.hasNext()) {
			Hub hub = it.next();
			if(hub.getId() == id) {
				return hub;
			}
		}
		return null;
	}
	
	public Hub getHub(Player player) {
		Iterator<Hub> it = hubs.iterator();
		while(it.hasNext()) {
			Hub hub = it.next();
			if(hub.containsPlayer(player)) {
				return hub;
			}
		}
		return null;
	}
	
	public void joinHub(final Player player, int hubid) {
		int hid;
		
		if(hubid == -1) {
			hid = (int) Math.ceil(Math.random()*hubs.size());
		}
		else {
			hid = hubid;
		}
		
		Hub hub = this.getHub(hid);
		hub.join(player);
		
		Location spawn = hub.getSpawn();
		spawn.setWorld(Bukkit.getWorld(hub.getServerName()+"_hub"));
		
		player.teleport(spawn);
		
		SakuraHub.plugin.getServer().getScheduler().scheduleSyncDelayedTask(SakuraHub.plugin, new Runnable() {
			@Override
			public void run() {
				player.setGameMode(GameMode.ADVENTURE);
			}
		}, 10L);
		player.setCompassTarget(hub.getSpawn());
		player.setPlayerListName(RankConvert.get().RankToPrefix(PlayerData.get().getPlayer(player).getRank())+""+player.getName());
		player.setDisplayName(RankConvert.get().RankToPrefix(PlayerData.get().getPlayer(player).getRank())+""+player.getName());
		player.setHealth(20);
		player.setFoodLevel(20);
	}
	
	public void leaveHub(Player player) {
		Hub hub = this.getHub(player);
		
		if(hub == null) {
			return;
		}
		
		hub.leave(player);
	}
	
	public void loadHubs() {
		hubs = new ArrayList<Hub>();
		
		SakuraConfig config = ConfigManager.get().fetchClass("/hubs/hubs.yml");
		
		for(String id : config.getConfig().getConfigurationSection("hubs").getKeys(false)) {
			int i = Integer.parseInt(id);
			Hub h = new Hub(i);
			hubs.add(h);
			
			Bukkit.getServer().dispatchCommand(SakuraHub.plugin.getServer().getConsoleSender(), "mv delete "+h.getServerName()+"_hub");
			Bukkit.getServer().dispatchCommand(SakuraHub.plugin.getServer().getConsoleSender(), "mv confirm");
			
			WorldHandlers.get().copyWorld(new File("worlds_backup/hub_world"), new File(h.getServerName()+"_hub"));
			
			Bukkit.getServer().dispatchCommand(SakuraHub.plugin.getServer().getConsoleSender(), "mv import "+h.getServerName()+"_hub NORMAL");
		}
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.kickPlayer(ChatColor.RED+""+ChatColor.BOLD+"WARNING"+ChatColor.RESET+"\n"+ChatColor.YELLOW+"This server was forced to reset. Please reconnect.");
		}
	}
}
