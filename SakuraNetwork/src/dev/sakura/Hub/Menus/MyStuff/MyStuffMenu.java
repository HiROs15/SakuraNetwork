package dev.sakura.Hub.Menus.MyStuff;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.sakura.Main;
import dev.sakura.Libs.Menu.ItemMenu;
import dev.sakura.Libs.Menu.ItemMenu.OptionClickEventHandler;

public class MyStuffMenu {
	public static MyStuffMenu get() {
		return new MyStuffMenu();
	}
	
	public MyStuffMenu() {
		setupHomeMenu();
	}
	
	public void openMenu(Player player) {
		menu_mystuff_home.open(player);
	}
	
	
	
		
	
	// Home Menu - Start
	public ItemMenu menu_mystuff_home = new ItemMenu(9,"My Stuff", new OptionClickEventHandler() {
		@Override
		public void onOptionClick(ItemMenu.OptionClickEvent event) {
			String name = event.getName();
			
			if(name.equals(ChatColor.GOLD+"Particle Effects")) {
				
			}
		}
	}, Main.plugin);
	
	private void setupHomeMenu() {
		menu_mystuff_home.setOption(0, new ItemStack(Material.NETHER_STAR), ChatColor.GOLD+"Particle Effects", ChatColor.BLUE+"Click to view and activate particle effects in the hub!");
	}
	
	//Home Menu - End
	
	//Particle Effects Menu - Start
	public ItemMenu menu_particle_effects = new ItemMenu(9, "Particle Effects", new OptionClickEventHandler() {
		@Override
		public void onOptionClick(ItemMenu.OptionClickEvent event) {
			
		}
	}, Main.plugin);
	public void setupParticleEffectMenu() {
		
	}
}
