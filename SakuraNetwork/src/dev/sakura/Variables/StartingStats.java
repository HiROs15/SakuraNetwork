package dev.sakura.Variables;

import org.bukkit.ChatColor;

public class StartingStats {
	public static StartingStats get() {
		return new StartingStats();
	}
	
	public int start_diamonds = 1000;
	public boolean displayWelcome = true;
	public String title = ChatColor.GOLD+"Welcome to the "+ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Sakura Network";
	public String subtitle = ChatColor.YELLOW+"Enjoy 1000 starting diamonds for joining in "+ChatColor.BLUE+"ALPHA";
}
