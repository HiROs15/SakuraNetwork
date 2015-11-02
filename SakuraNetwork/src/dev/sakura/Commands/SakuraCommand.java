package dev.sakura.Commands;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public abstract class SakuraCommand {
	private String command;
	private String minimumRank;
	
	private String message;
	private String[] args;
	
	public void setCommand(String s) {
		this.command = s;
	}
	
	public void setRank(String rank) {
		this.minimumRank = rank;
	}
	
	public String getCommand() {
		return this.command;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String[] getArgs() {
		return this.args;
	}
	
	public String getMinimumRank() {
		return this.minimumRank;
	}
	
	public String noPermission() {
		return ChatColor.RED+"You do not have permission to do this.";
	}
	
	public abstract void help(Player player);
	
	public abstract void command(Player player);
	
	public void setupCommand(String message) {
		this.message = message.replace("/"+this.getCommand()+" ","");
		
		this.args = message.split(" ");
	}
}
