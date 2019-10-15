package games.bevs.minecraftbut.senerario.senerarios;

import org.bukkit.Material;
import org.bukkit.block.Block;

import games.bevs.minecraftbut.commons.XMaterial;
import games.bevs.minecraftbut.commons.utils.MathUtils;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;

public class AnvilRain extends Senerario
{
	private int hieghtY = 140;
	
	
	public AnvilRain(ButWorld butWorld) 
	{
		super("Anvil Rain", butWorld, XMaterial.ANVIL.parseMaterial(), new String[] {"Anvils will spawn from the sky every minute"} );
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart()
	{
		this.repeat(() -> {
			
			
//			for(int x = this.getButWorld().getMinLocation().getBlockX(); x < this.getButWorld().getMaxLocation().getBlockX(); x+= MathUtils.getRandom().nextInt(8))
//			{
//				for(int z = this.getButWorld().getMinLocation().getBlockZ(); z < this.getButWorld().getMaxLocation().getBlockZ(); z+= MathUtils.getRandom().nextInt(8))
//				{
//					if(MathUtils.getRandom().nextInt(10) < 8) continue;
//					Block block = this.getButWorld().getWorld().getBlockAt( x, hieghtY, z);
//					
//					Material mat = FALLING_BLOCK_MATERIALS[MathUtils.getRandom().nextInt(lengthOfMats)];
//					block.getWorld().spawnFallingBlock(block.getLocation(), mat, (byte) 0);
//				}
//			}
//			
//			if(MathUtils.getRandom().nextInt(10) >= 8) 
//				hieghtY--;
		}, 20l * 5);
	}
	
	@Override
	public void onFinish()
	{
		
	}
}
