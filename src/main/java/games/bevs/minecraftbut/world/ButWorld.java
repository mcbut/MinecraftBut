package games.bevs.minecraftbut.world;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import lombok.Getter;

public class ButWorld 
{
	@Getter
	private World world;
	private int size = 150;
	
	@Getter
	private Location minLocation,
					 maxLocation;
	
	public ButWorld(World world)
	{
		this.world = world;
		
		int heightsBlock = this.world.getHighestBlockYAt(0, 0);
		this.world.setSpawnLocation(0, heightsBlock + 3, 0);
		
		WorldBorder worldBorder = this.world.getWorldBorder();
		worldBorder.setCenter(0, 0);
		worldBorder.setSize(this.size);
		
		this.minLocation = new Location(world, -this.size / 2, 0, -this.size / 2);
		this.maxLocation = new Location(world,  this.size / 2, 0,  this.size / 2);
		
		Bukkit.broadcastMessage(ChatColor.BOLD + "World: " + this.world.getName());
	}
}
