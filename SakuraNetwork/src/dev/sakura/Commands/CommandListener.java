package dev.sakura.Commands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import dev.sakura.Listeners.SakuraListener;

public class CommandListener extends SakuraListener {
	public CommandListener() {
		this.setInstance(this);
	}
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		boolean res = SakuraCommandManager.get().handleCommands(event.getPlayer(), event.getMessage());
		
		if(!res) {
			event.setCancelled(true);
		}
	}
}
