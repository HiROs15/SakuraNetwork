package dev.sakura.Hub.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.sakura.Hub.HubManager;
import dev.sakura.Listeners.SakuraListener;

public class HubEvents extends SakuraListener {
	public HubEvents() {
		this.setInstance(this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(HubManager.instance.isPlayerInHub(event.getPlayer())) {
			
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
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
		}
	}
}
