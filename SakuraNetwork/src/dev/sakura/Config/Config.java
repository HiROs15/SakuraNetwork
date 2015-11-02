package dev.sakura.Config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import dev.sakura.Main;

public class Config {
	private static Config instance;
	public static Config get() {
		instance = new Config();
		return instance;
	}
	
	private FileConfiguration config = null;
	private File file = null;
	private String filename;
	
	public Config setFile(String filename) {
		this.filename = filename;
		return instance;
	}
	
	public String getFile() {
		return this.filename;
	}
	
	public void reloadConfig() {
		if(file == null) {
			file = new File(Main.plugin.getDataFolder()+""+this.filename);
		}
		config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	public FileConfiguration getConfig() {
		if(config == null) {
			reloadConfig();
		}
		return config;
	}
	
	public void saveConfig() {
		try {
			if(file == null) {
				reloadConfig();
			}
			
			config.save(file);
		} catch(Exception e) {}
	}
}
