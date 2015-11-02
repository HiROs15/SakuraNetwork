package dev.sakura.Variables;

import org.bukkit.ChatColor;

public class StartingStats {
	public static StartingStats get() {
		return new StartingStats();
	}
	
	public int start_diamonds = 1000;
	public boolean displayWelcome = true;
	public String title = "Welcome to the Sakura Network";
	public String subtitle = "Enjoy 1000 starting diamonds for joining early!";
	public ChatColor titleColor = ChatColor.LIGHT_PURPLE;
	public ChatColor subtitleColor = ChatColor.GREEN;
}
