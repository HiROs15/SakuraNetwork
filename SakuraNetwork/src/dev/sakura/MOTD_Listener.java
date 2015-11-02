package dev.sakura;

import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

import dev.sakura.Listeners.SakuraListener;

public class MOTD_Listener extends SakuraListener {
	public MOTD_Listener() {
		this.setInstance(this);
	}
	
	@EventHandler
	public void onPlayerPing(ServerListPingEvent event) {
		event.setMaxPlayers(9999);
	}
}
