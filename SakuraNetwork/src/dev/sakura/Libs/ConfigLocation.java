package dev.sakura.Libs;

import org.bukkit.Location;

import dev.sakura.Main;
import dev.sakura.Config.Config;

public class ConfigLocation {
	public static ConfigLocation get() {
		return new ConfigLocation();
	}
	
	public Location ConfigToLocation(String path, String File) {
		Config c = Config.get().setFile(File);
		
		Location loc = new Location(
				Main.plugin.getServer().getWorld(c.getConfig().getString(path+".world")),
				c.getConfig().getDouble(path+".x"),
				c.getConfig().getDouble(path+".y"),
				c.getConfig().getDouble(path+".z"),
				(float) c.getConfig().getDouble(path+".yaw"),
				(float) c.getConfig().getDouble(path+".pitch")
				);
		return loc;
	}
	
	public void LocationToConfig(String path, String File, Location loc) {
		Config c = Config.get().setFile(File);
		
		c.getConfig().set(path+".world",loc.getWorld().getName());
		c.getConfig().set(path+".x", loc.getX());
		c.getConfig().set(path+".y", loc.getY());
		c.getConfig().set(path+".y", loc.getY());
		c.getConfig().set(path+".z", loc.getZ());
		c.getConfig().set(path+".yaw", loc.getYaw());
		c.getConfig().set(path+".pitch", loc.getPitch());
		
		c.saveConfig();
	}
}
