package dev.sakura.Listeners;

import org.bukkit.event.Listener;

public abstract class SakuraListener implements Listener {
	private SakuraListener instance;
	
	public void setInstance(SakuraListener i) {
		this.instance = i;
	}
	
	public SakuraListener getInstance() {
		return this.instance;
	}
}
