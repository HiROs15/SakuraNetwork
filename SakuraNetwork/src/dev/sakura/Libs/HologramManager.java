package dev.sakura.Libs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.WorldServer;

public class HologramManager {
	  
    public final Location location;
  
    private final double distance = 0.25;
  
    private final String text;

    public HologramManager( Location location, String message ) {
        this.location = location;
      
        this.text = message;
    }
  
    public void spawnHologram( int BlockHeight ) {
        WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();
      
        EntityArmorStand armorStand = new EntityArmorStand( worldServer );
        armorStand.setLocation( location.getX(), location.getY() + (BlockHeight / 2) * distance, location.getZ(), 0, 0 );
      
        PacketPlayOutSpawnEntity packetSpawnEntity = new PacketPlayOutSpawnEntity( armorStand, 0 );
      
        armorStand.setCustomName( this.text );
        armorStand.setCustomNameVisible( true );
        armorStand.setInvisible( true );
      
        for( Player all : location.getWorld().getPlayers() ) {
            EntityPlayer player = (( CraftPlayer ) all ).getHandle();
          
            player.playerConnection.sendPacket( packetSpawnEntity );
        }
      
    }
  
}