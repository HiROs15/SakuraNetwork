package dev.sakura.PlayerManager;

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
		case "vip":
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
}
