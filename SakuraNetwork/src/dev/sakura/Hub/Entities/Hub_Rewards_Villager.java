package dev.sakura.Hub.Entities;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

public class Hub_Rewards_Villager extends EntityVillager {
	public Hub_Rewards_Villager(World world) {
		super(world);
		
		try {
			Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
			Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
			
			bField.setAccessible(true);
			cField.setAccessible(true);
			
			bField.set(this.goalSelector, new UnsafeList<PathfinderGoalSelector>());
			cField.set(this.goalSelector, new UnsafeList<PathfinderGoalSelector>());
		} catch(Exception e) {}
		
		this.goalSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityPlayer.class, 10.0F));
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
	}
	
	@Override
	public String z() {
		return "";
	}
}
