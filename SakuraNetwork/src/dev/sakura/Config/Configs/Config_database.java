package dev.sakura.Config.Configs;

import java.io.File;

import dev.sakura.Config.BaseConfigFile;
import dev.sakura.Config.Config;

public class Config_database extends BaseConfigFile {
	public Config_database() {
		this.setName(File.separator+"database.yml");
	}
	
	@Override
	public void setupConfig() {
		Config c = Config.get().setFile(this.getName());
		c.getConfig().set("setup", true);
		c.getConfig().set("host", "");
		c.getConfig().set("username", "");
		c.getConfig().set("password", "");
		c.getConfig().set("database", "");
		c.saveConfig();
	}
}
