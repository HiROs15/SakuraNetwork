package dev.sakura.Hub.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
import dev.sakura.Hub.Menus.MyStuff.MyStuffMenu;
import dev.sakura.Listeners.SakuraListener;

public class HubEvents extends SakuraListener {
	public HubEvents() {
		this.setInstance(this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(HubManager.instance.getHub(event.getPlayer()) != null) {
			Player player = event.getPlayer();
			HubManager.instance.getHub(player).leave(player);
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
		
		if(HubManager.instance.getHub(player) != null) {
			HubManager.instance.getHub(player).leave(player);
			event.setQuitMessage("");
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getEntity();
		
		if(HubManager.instance.getHub(player) != null) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerStarve(FoodLevelChangeEvent event) {
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getEntity();
		
		if(HubManager.instance.getHub(player) != null) {
			event.setCancelled(true);
			player.setFoodLevel(20);
		}
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		
		if(HubManager.instance.getHub(player) != null) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerSendMessage(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if(HubManager.instance.getHub(player) != null) {
			Hub hub = HubManager.instance.getHub(player);
			
			ChatManager.get().sendMessage(player, event.getMessage(), hub.getPlayers());
			
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if(HubManager.instance.getHub(player) == null) {
			return;
		}
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
			if(event.getMaterial() == Material.REDSTONE_TORCH_ON) {
				event.setCancelled(true);
				HubManager.instance.getHub(player).togglePlayers(player);
			}
			
			if(event.getMaterial() == Material.CHEST) {
				event.setCancelled(true);
				MyStuffMenu.get().openMenu(player);
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		
		if(HubManager.instance.getHub(player) != null) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		
		if(HubManager.instance.getHub(player) != null) {
			event.setCancelled(true);
		}
	}
}
