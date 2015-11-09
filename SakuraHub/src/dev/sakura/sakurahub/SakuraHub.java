package dev.sakura.sakurahub;

import org.bukkit.plugin.java.JavaPlugin;

import dev.sakura.sakurahub.Commands.HubCommandManager;
import dev.sakura.sakurahub.Configs.HubConfigManager;
import dev.sakura.sakurahub.Hub.HubManager;
import dev.sakura.sakurahub.Listeners.HubListenerManager;

public class SakuraHub extends JavaPlugin {
	public static SakuraHub plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		startupManagers();
	}
	
	@Override
	public void onDisable() {
	}
	
	public void startupManagers() {
		new HubCommandManager();
		new HubConfigManager();
		new HubListenerManager();
		new HubManager();
	}
}
