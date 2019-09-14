package games.bevs.minecraftbut.senerarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;
import net.md_5.bungee.api.ChatColor;

public class TheLavaRises extends Senerario
{
	private int hieghtY = 50;
	public TheLavaRises(ButWorld butWorld) 
	{
		super("The Lava is rising", butWorld);
	}

	
	@Override
	public void onStart()
	{
		this.repeat(() -> {
			for(int x = this.getButWorld().getMinLocation().getBlockX(); x < this.getButWorld().getMaxLocation().getBlockX(); x++)
			{
				for(int z = this.getButWorld().getMinLocation().getBlockZ(); z < this.getButWorld().getMaxLocation().getBlockZ(); z++)
				{
					Block block = this.getButWorld().getWorld().getBlockAt( x, hieghtY, z);
					if(!block.getType().isSolid())
						block.setType(Material.STATIONARY_LAVA);
					
				}
			}
			
			hieghtY++;
			Bukkit.broadcastMessage(ChatColor.BOLD + "Lava has gone up");
		}, 20l * 5);
	}
	
	@Override
	public void onFinish()
	{
		
	}
}
