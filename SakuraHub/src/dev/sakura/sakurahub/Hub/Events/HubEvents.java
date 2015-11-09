package dev.sakura.sakurahub.Hub.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.sakura.Listeners.SakuraListener;
import dev.sakura.sakurahub.Hub.HubManager;

public class HubEvents extends SakuraListener {
	public HubEvents() {
		this.setInstance(this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		HubManager.get().joinHub(player, -1);
		event.setJoinMessage("");
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		HubManager.get().leaveHub(player);
		event.setQuitMessage("");
	}
	
	@EventHandler
	public void onPlayerBreakBlock(BlockBreakEvent event) {
		Player player = event.getPlayer();
		
		if(HubManager.get().getHub(player) != null) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerPlaceBlock(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		
		if(HubManager.get().getHub(player) != null) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerDamaged(EntityDamageEvent event) {
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getEntity();
		
		if(HubManager.get().getHub(player) != null) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerFoodChange(FoodLevelChangeEvent event) {
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getEntity();
		
		if(HubManager.get().getHub(player) != null) {
			event.setCancelled(true);
			player.setFoodLevel(20);
		}
	}
}
