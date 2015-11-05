package dev.sakura.Hub.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.sakura.Chat.ChatManager;
import dev.sakura.Hub.Hub;
import dev.sakura.Hub.HubManager;
import dev.sakura.Listeners.SakuraListener;

public class HubEvents extends SakuraListener {
	public HubEvents() {
		this.setInstance(this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(HubManager.instance.isPlayerInHub(event.getPlayer())) {
			Player player = event.getPlayer();
			HubManager.instance.leaveHub(player);
			HubManager.instance.joinHub(player);
		}
		else {
			HubManager.instance.joinHub(event.getPlayer());
			event.setJoinMessage("");
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		if(HubManager.instance.isPlayerInHub(player)) {
			HubManager.instance.leaveHub(player);
			event.setQuitMessage("");
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getEntity();
		
		if(HubManager.instance.isPlayerInHub(player)) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerStarve(FoodLevelChangeEvent event) {
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getEntity();
		
		if(HubManager.instance.isPlayerInHub(player)) {
			event.setCancelled(true);
			player.setFoodLevel(20);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if(!HubManager.instance.isPlayerInHub(player)) {
			return;
		}
		
		//Item Click Handlers
		if((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)) {
			if(event.getMaterial() == Material.REDSTONE_TORCH_ON) {
				HubManager.instance.getHub(player).toggleHiddenPlayers(player);
			}
		}
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		
		if(HubManager.instance.isPlayerInHub(player)) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerSendMessage(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if(HubManager.instance.isPlayerInHub(player)) {
			Hub hub = HubManager.instance.getHub(player);
			
			ChatManager.get().sendMessage(player, event.getMessage(), hub.getPlayers());
			
			event.setCancelled(true);
		}
	}
}
