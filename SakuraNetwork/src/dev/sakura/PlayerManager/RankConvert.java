package dev.sakura.PlayerManager;

import org.bukkit.ChatColor;

public class RankConvert {
	public static RankConvert get() {
		return new RankConvert();
	}
	
	public int StringToRank(String rank) {
		int num = 0;
		
		switch(rank) {
		case "member":
			num = 0;
			break;
		case "specicalmember":
			num = 1;
			break;
		case "squire":
			num = 2;
			break;
		case "knight":
			num = 3;
			break;
		case "king":
			num = 4;
			break;
		case "mega":
			num = 5;
			break;
		case "mod":
			num = 6;
			break;
		case "admin":
			num = 7;
			break;
		case "buildteam":
			num = 8;
			break;
		case "sakuramember":
			num = 9;
			break;
		case "developer":
			num = 10;
			break;
		}
		
		return num;
	}
	
	public String RankToPrefix(String rank) {
		String prefix = "";
		
		switch(rank) {
		case "member": 
			prefix = "";
			break;
		case "specialmember":
			prefix = ChatColor.GRAY+""+ChatColor.BOLD+"SPECIAL";
			break;
		case "squire":
			prefix = ChatColor.BLUE+""+ChatColor.BOLD+"SQUIRE";
			break;
		case "knight":
			prefix = ChatColor.GOLD+""+ChatColor.BOLD+"KNIGHT";
			break;
		case "king":
			prefix = ChatColor.GREEN+""+ChatColor.BOLD+"KING";
			break;
		case "mega":
			prefix = ChatColor.DARK_AQUA+""+ChatColor.BOLD+"MEGA";
			break;
		case "mod":
			prefix = ChatColor.DARK_BLUE+""+ChatColor.BOLD+"MOD";
			break;
		case "admin":
			prefix = ChatColor.RED+""+ChatColor.BOLD+"ADMIN";
			break;
		case "buildteam":
			prefix = ChatColor.DARK_RED+""+ChatColor.BOLD+"BUILD TEAM";
			break;
		case "sakuramember":
			prefix = ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"SAKURA MEMBER";
			break;
		case "developer":
			prefix = ChatColor.AQUA+""+ChatColor.BOLD+"DEVELOPER";
			break;
		}
		
		if(rank.equals("member")) {
			return "";
		} else {
			return ChatColor.WHITE+"["+prefix+""+ChatColor.RESET+""+ChatColor.WHITE+"] "+ChatColor.RESET+"";
		}
	}
}
