package games.bevs.minecraftbut.senerario.senerarios;

import org.bukkit.Material;
import org.bukkit.block.Block;

import games.bevs.minecraftbut.commons.XMaterial;
import games.bevs.minecraftbut.commons.utils.MathUtils;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;

public class RainingBlocks extends Senerario
{
	private static final Material[] FALLING_BLOCK_MATERIALS = new Material[] { Material.DIAMOND_BLOCK, Material.DIAMOND_ORE, Material.GOLD_ORE, Material.IRON_ORE, Material.COAL_ORE, Material.COBBLESTONE, Material.STONE, Material.ANVIL, Material.BARRIER, Material.BEDROCK, Material.DIRT, Material.GRASS, Material.GRAVEL, XMaterial.MELON.parseMaterial(), Material.SAND, Material.SANDSTONE, XMaterial.CRAFTING_TABLE.parseMaterial(), Material.IRON_BLOCK, Material.COAL_BLOCK, Material.JUKEBOX, Material.NOTE_BLOCK, Material.GLOWSTONE, Material.FURNACE, Material.ENDER_CHEST, Material.CHEST, Material.HOPPER, XMaterial.BIRCH_WOOD.parseMaterial(), XMaterial.BIRCH_LOG.parseMaterial(), Material.BRICK, Material.DISPENSER  };
	private int hieghtY = 140;
	
	
	public RainingBlocks(ButWorld butWorld) 
	{
		super("Raining Blocks", butWorld, XMaterial.COMMAND_BLOCK.parseMaterial(), new String[] {"Random blocks will fall from the sky"}, "Sprock");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart()
	{
		int lengthOfMats = FALLING_BLOCK_MATERIALS.length;
		this.repeat(() -> {
			
			
			for(int x = this.getButWorld().getMinLocation().getBlockX(); x < this.getButWorld().getMaxLocation().getBlockX(); x+= MathUtils.getRandom().nextInt(8))
			{
				for(int z = this.getButWorld().getMinLocation().getBlockZ(); z < this.getButWorld().getMaxLocation().getBlockZ(); z+= MathUtils.getRandom().nextInt(8))
				{
					if(MathUtils.getRandom().nextInt(10) < 8) continue;
					Block block = this.getButWorld().getWorld().getBlockAt( x, hieghtY, z);
					
					Material mat = FALLING_BLOCK_MATERIALS[MathUtils.getRandom().nextInt(lengthOfMats)];
					block.getWorld().spawnFallingBlock(block.getLocation(), mat, (byte) 0);
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
