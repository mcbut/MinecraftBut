package games.bevs.minecraftbut.senerario.senerarios;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;

import games.bevs.minecraftbut.commons.utils.MathUtils;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;

public class TNTRain extends Senerario
{
	private int hieghtY = 140;
	public TNTRain(ButWorld butWorld) 
	{
		super("TNT Rain", butWorld, Material.TNT, new String[] {"TNT will rain from the sky continusly"});
	}

	@Override
	public void onStart()
	{
		this.repeat(() -> {
			for(int x = this.getButWorld().getMinLocation().getBlockX(); x < this.getButWorld().getMaxLocation().getBlockX(); x+= MathUtils.getRandom().nextInt(8))
			{
				for(int z = this.getButWorld().getMinLocation().getBlockZ(); z < this.getButWorld().getMaxLocation().getBlockZ(); z+= MathUtils.getRandom().nextInt(8))
				{
					if(MathUtils.getRandom().nextInt(10) < 8) continue;
					Block block = this.getButWorld().getWorld().getBlockAt( x, hieghtY, z);
					block.getLocation().getWorld().spawn(block.getLocation(), TNTPrimed.class);
					
				}
			}
			
			if(MathUtils.getRandom().nextInt(10) >= 8) 
				hieghtY--;
		}, 20l * 5);
	}
	
	@Override
	public void onFinish()
	{
		
	}
}
