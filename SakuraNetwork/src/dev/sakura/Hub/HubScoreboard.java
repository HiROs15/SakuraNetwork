package dev.sakura.Hub;

import java.sql.ResultSet;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import dev.sakura.Main;
import dev.sakura.Managers;

public class HubScoreboard {
	private static HubScoreboard instance;
	
	private ScoreboardManager manager;
	private Scoreboard board;
	private Objective objective;
	@SuppressWarnings("unused")
	private int ObjectiveTaskAnimation;
	
	private String[] headers = {
			ChatColor.RED+""+ChatColor.BOLD+"Sakura Network",
			ChatColor.GOLD+""+ChatColor.BOLD+"Sakura Network",
			ChatColor.YELLOW+""+ChatColor.BOLD+"Sakura Network",
			ChatColor.GREEN+""+ChatColor.BOLD+"Sakura Network",
			ChatColor.AQUA+""+ChatColor.BOLD+"Sakura Network",
			ChatColor.BLUE+""+ChatColor.BOLD+"Sakura Network",
			ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"Sakura Network",
			ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Sakura Network"
	};
	
	public HubScoreboard() {
		HubScoreboard.instance = this;
		manager = Main.plugin.getServer().getScoreboardManager();
		board = manager.getNewScoreboard();
		objective = board.registerNewObjective("hubboard", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(headers[0]);
		
		this.startAnimation();
	}
	
	public static HubScoreboard get() {
		return instance;
	}
	
	private void startAnimation() {
		ObjectiveTaskAnimation = Main.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			int frame = 0;
			@Override
			public void run() {
				objective.setDisplayName(headers[frame]);
				frame++;
				
				if(frame >= headers.length) {
					frame = 0;
				}
			}
		}, 0L, 20L);
	}
	
	public void showScoreboard(Player player) {
		ResultSet aon = Managers.sakuraDB.query("SELECT * FROM members WHERE uuid='"+player.getUniqueId().toString()+"'");
		
		try {
			aon.next();
			this.setRow(ChatColor.YELLOW+"Server: "+ChatColor.WHITE+""+HubManager.instance.getHub(player).getHubServerName(), 0);
			this.setRow(" ", 1);
			this.setRow(ChatColor.GOLD+"Coins: "+ChatColor.WHITE+""+aon.getInt("coins"), 2);
			this.setRow("  ", 3);
			this.setRow(ChatColor.AQUA+"Diamonds: "+ChatColor.WHITE+""+aon.getInt("diamonds"), 4);
			this.setRow("   ", 5);
		}catch(Exception e) {
			System.out.println("ERROR: "+e.getMessage());
		}
		
		player.setScoreboard(board);
	}
	
	public void hideScoreboard(Player player) {
		Scoreboard b = manager.getNewScoreboard();
		
		player.setScoreboard(b);
	}
	
	private Score setRow(String text, int score) {
		Score s = objective.getScore(text);
		s.setScore(score);
		return s;
	}
}
