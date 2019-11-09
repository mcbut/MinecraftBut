package games.bevs.minecraftbut.senerario.senerarios;

import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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

public class MaducerWorld extends Senerario
{
	@Optional
	private int decaysPerSecond = 40;
	
	public MaducerWorld(ButWorld butWorld) 
	{
		super("Maducer World", butWorld, Material.BRICK, new String[] {"The world slowly turns to bedrock"});
	}

	
	@Override
	public void onStart()
	{
		this.repeat(() -> {
			for(int i = 0; i < decaysPerSecond; i++)
			{
				int ranX = MathUtils.getRandom().nextInt(this.getButWorld().getMaxLocation().getBlockX() - this.getButWorld().getMinLocation().getBlockX()) + this.getButWorld().getMinLocation().getBlockX();
				int ranY = MathUtils.getRandom().nextInt(this.getButWorld().getWorld().getMaxHeight());
				int ranZ = MathUtils.getRandom().nextInt(this.getButWorld().getMaxLocation().getBlockZ() - this.getButWorld().getMinLocation().getBlockZ()) + this.getButWorld().getMinLocation().getBlockZ();
				Block block = this.getButWorld().getWorld().getBlockAt(ranX, ranY, ranZ);
				if(block == null) continue;
				
				if(block.getType() == Material.BEDROCK)
					continue;
				
				block.setType(Material.BEDROCK);
			}
		}, 20l * 1);
	}
	
	@Override
	public void onFinish()
	{
		
	}
}
