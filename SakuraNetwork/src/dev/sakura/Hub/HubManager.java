package dev.sakura.Hub;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev.sakura.Main;
import dev.sakura.Config.Config;
import dev.sakura.PlayerManager.PlayerData;
import dev.sakura.PlayerManager.RankConvert;

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
		for(Hub hub : this.hubs) {
			ArrayList<Player> players = hub.getPlayers();
			for(Player p : players) {
				if(p.getUniqueId().toString().equals(player.getUniqueId().toString())) {
					return hub;
				}
			}
		}
		return null;
	}
	
	public void joinHub(Player player) {
		int hubid = (int) Math.ceil(Math.random()*this.hubs.size());
		Hub hub = this.getHub(hubid);
		
		if((hub.getPlayers().size()+1) > hub.getMaxPlayers()) {
			this.joinHub(player);
			return;
		}
		hub.join(player);
		
		player.getInventory().clear();
		player.teleport(hub.getSpawnLocation());
		player.setGameMode(GameMode.ADVENTURE);
		
		this.setupHubInventory(player);
		
		HubScoreboard.get().showScoreboard(player);
		
		player.setPlayerListName(RankConvert.get().RankToPrefix(PlayerData.get().setUUID(player.getUniqueId().toString()).fetchStats().getRank()) + ChatColor.WHITE+""+player.getName());
	}
	
	private void setupHubInventory(Player player) {
		player.getInventory().setItem(0, new HubItem(Material.COMPASS, ChatColor.LIGHT_PURPLE+"Minigame Selection", 1).registerItem().getItem());
		player.getInventory().setItem(4, new HubItem(Material.REDSTONE_TORCH_ON, ChatColor.LIGHT_PURPLE+"Player Visibility "+ChatColor.GREEN+"(On)", 1).registerItem().getItem());
		player.getInventory().setItem(8, new HubItem(Material.SLIME_BALL, ChatColor.LIGHT_PURPLE+"Hub Selector", 1).registerItem().getItem());
		player.getInventory().setItem(6, new HubItem(Material.CHEST, ChatColor.LIGHT_PURPLE+"My Stuff", 1).registerItem().getItem());
	}
	
	public void updatePlayersItem(Player player) {
		Hub hub = this.getHub(player);
		
		if(hub.isPlayerHidden(player) == false) {
			player.getInventory().setItem(4, new HubItem(Material.REDSTONE_TORCH_ON, ChatColor.LIGHT_PURPLE+"Player Visiblity "+ChatColor.GREEN+"(On)",1).registerItem().getItem());
		}
		else {
			player.getInventory().setItem(4, new HubItem(Material.REDSTONE_TORCH_ON, ChatColor.LIGHT_PURPLE+"Player Visiblity "+ChatColor.RED+"(Off)",1).registerItem().getItem());
		}
	}
}
