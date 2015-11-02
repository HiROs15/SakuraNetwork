package dev.sakura;

import org.bukkit.plugin.java.JavaPlugin;

import dev.sakura.Config.ConfigManager;
import dev.sakura.Listeners.ListenerManager;

public class Main extends JavaPlugin {
	public static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		//Setup config files
		ConfigManager.get().setupConfigs();
		
		//Register Listeners
		ListenerManager.get();
		
		//Register the Managers
		Managers.get();
	}
	
	@Override
	public void onDisable() {
		
	}
}