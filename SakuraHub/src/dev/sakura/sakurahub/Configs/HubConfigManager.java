package dev.sakura.sakurahub.Configs;

import dev.sakura.Config.ConfigManager;
import dev.sakura.sakurahub.Configs.Configs.Config_Hub;

public class HubConfigManager extends ConfigManager {
	private static HubConfigManager instance;
	
	public static HubConfigManager getInstance() {
		return instance;
	}
	
	public HubConfigManager() {
		instance = this;
		
		this.addConfig(new Config_Hub());
	}
}
