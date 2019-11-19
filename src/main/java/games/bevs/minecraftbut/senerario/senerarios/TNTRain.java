package games.bevs.minecraftbut.senerario.senerarios;


import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;

import games.bevs.minecraftbut.commons.XMaterial;
import games.bevs.minecraftbut.commons.utils.MathUtils;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;

public class TNTRain extends Senerario
{
	private int hieghtY = 140;
	public TNTRain(ButWorld butWorld) 
	{
		super("TNT Rain", butWorld, XMaterial.TNT.parseMaterial(), new String[] {"TNT will rain from the sky continusly"}, "WilburSoot");
	}

	@Override
	public void onStart()
	{
		this.repeat(() -> {
			for(int x = this.getButWorld().getMinLocation().getBlockX(); x < this.getButWorld().getMaxLocation().getBlockX(); x+= (MathUtils.getRandom().nextInt(10) + 1))
			{
				for(int z = this.getButWorld().getMinLocation().getBlockZ(); z < this.getButWorld().getMaxLocation().getBlockZ(); z+= (MathUtils.getRandom().nextInt(10) + 1))
				{
					if(MathUtils.getRandom().nextInt(10) < 8) continue;
					int highest = this.getButWorld().getWorld().getHighestBlockYAt(x, z);
					int y = highest + 40;
					Block block = this.getButWorld().getWorld().getBlockAt( x, y, z);
					block.getLocation().getWorld().spawn(block.getLocation(), TNTPrimed.class);
					
				}
			}
			
			if(MathUtils.getRandom().nextInt(10) >= 7) 
				hieghtY--;
		}, 20l * 60);
	}
	

}
