package dev.sakura.Hub.Commands;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import dev.sakura.Commands.SakuraCommand;
import dev.sakura.Hub.Entities.Hub_Rewards_Villager;
import net.minecraft.server.v1_8_R3.World;

public class Command_hub_test extends SakuraCommand {
	public Command_hub_test() {
		this.setCommand("testspawn");
		this.setRank("developer");
	}

	@Override
	public void help(Player player) {
		
	}
	
	@Override
	public void command(Player player) {
		@SuppressWarnings("unused")
		String[] args = this.getArgs();
		
		World world = ((CraftWorld)player.getLocation().getWorld()).getHandle();
		Hub_Rewards_Villager entity = new Hub_Rewards_Villager(world);
		entity.setProfession(1);
		
		Location loc = player.getLocation();
		
		entity.setPosition(loc.getX(), loc.getY(), loc.getZ());
		world.addEntity(entity);
		
		player.sendMessage("Entity has spawned!");
	}
}
