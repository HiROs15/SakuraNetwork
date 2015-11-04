package dev.sakura.Hub;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HubItem {
	private ItemStack item;
	private String name;
	private int count;
	private ArrayList<String> lore;
	private Material material;
	
	public HubItem(Material material, String name, int count, String... lore) {
		this.material = material;
		this.name = name;
		this.count = count;
		this.lore = new ArrayList<String>(Arrays.asList(lore));
	}
	
	public HubItem registerItem() {
		this.item = new ItemStack(this.material);
		this.item.setAmount(this.count);
		
		ItemMeta m = this.item.getItemMeta();
		m.setDisplayName(this.name);
		m.setLore(this.lore);
		
		this.item.setItemMeta(m);
		
		return this;
	}
	
	public ItemStack getItem() {
		return this.item;
	}
}
