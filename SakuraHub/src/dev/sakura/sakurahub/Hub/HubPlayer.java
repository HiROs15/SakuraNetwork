package dev.sakura.sakurahub.Hub;

import org.bukkit.entity.Player;

public class HubPlayer {
	private Player player;
	private boolean playersVisible = true;
	
	public HubPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public boolean hidePlayers() {
		return this.playersVisible;
	}
}
