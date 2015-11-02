package dev.sakura.Hub.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.sakura.Hub.HubManager;
import dev.sakura.Listeners.SakuraListener;

public class HubEvents extends SakuraListener {
	public HubEvents() {
		this.setInstance(this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(HubManager.instance.isPlayerInHub(event.getPlayer())) {
			event.getPlayer().sendMessage("Youre in a hub");
		}
		else {
			HubManager.instance.joinHub(event.getPlayer());
			event.setJoinMessage("");
		}
	}
}
