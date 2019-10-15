package games.bevs.minecraftbut.senerario.senerarios;

import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import games.bevs.minecraftbut.commons.Console;
import games.bevs.minecraftbut.commons.XMaterial;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.commons.utils.MathUtils;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;
import net.md_5.bungee.api.ChatColor;

public class WorldDecay extends Senerario
{
	@Optional
	private long decaysPerSecond = 10l;
	
	public WorldDecay(ButWorld butWorld) 
	{
		super("World Decay", butWorld, Material.BRICK, new String[] {"Every block the sun touches slowly breaks down"});
	}

	
	@Override
	public void onStart()
	{
		this.repeat(() -> {
			for(int i = 0; i < decaysPerSecond; i++)
			{
				int ranX = MathUtils.getRandom().nextInt(this.getButWorld().getMaxLocation().getBlockX() - this.getButWorld().getMinLocation().getBlockX()) + this.getButWorld().getMinLocation().getBlockX();
				int ranZ = MathUtils.getRandom().nextInt(this.getButWorld().getMaxLocation().getBlockZ() - this.getButWorld().getMinLocation().getBlockZ()) + this.getButWorld().getMinLocation().getBlockZ();
				Block block = this.getButWorld().getWorld().getHighestBlockAt(ranX, ranZ);
				
				if(block.getType() == Material.BEDROCK)
					continue;
				
				block.setType(Material.AIR);
			}
		}, 20l * 1);
	}
	
	@Override
	public void onFinish()
	{
		
	}
}
