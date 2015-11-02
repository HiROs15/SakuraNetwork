package dev.sakura.Config.Configs;

import java.io.File;

import dev.sakura.Config.BaseConfigFile;
import dev.sakura.Config.Config;

public class Config_hubs extends BaseConfigFile {
	public Config_hubs() {
		this.setName(File.separator+"hubs"+File.separator+"hubs.yml");
	}
	
	@Override
	public void setupConfig() {
		Config c = Config.get().setFile(this.getName());
		
		c.saveConfig();
	}
}
