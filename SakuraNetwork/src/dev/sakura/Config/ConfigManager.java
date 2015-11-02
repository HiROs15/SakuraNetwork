package dev.sakura.Config;

import java.io.File;
import java.util.ArrayList;

import dev.sakura.Main;
import dev.sakura.Config.Configs.Config_database;
import dev.sakura.Config.Configs.Config_hubs;

public class ConfigManager {
	private ArrayList<BaseConfigFile> configs = new ArrayList<BaseConfigFile>();
	
	public ConfigManager() {
		//Add configs Here!
		configs.add(new Config_database());
		configs.add(new Config_hubs());
	}
	
	public static ConfigManager get() {
		return new ConfigManager();
	}
	
	public void setupConfigs() {
		for(BaseConfigFile a : configs) {
			File f = new File(Main.plugin.getDataFolder()+""+a.getName());
			if(!f.exists()) {
				try {
					a.setupConfig();
				} catch(Exception e) {}
			}
		}
	}
}
