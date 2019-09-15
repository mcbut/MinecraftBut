package games.bevs.minecraftbut.senerarios;

import org.bukkit.block.Block;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;

import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;
import net.md_5.bungee.api.ChatColor;

public class EnderDragonAppears extends Senerario
{
	private int heightY = 140;
	public EnderDragonAppears(ButWorld butWorld) 
	{
		super("An Enderdragon appears", butWorld);
	}

	
	@Override
	public void onStart()
	{
		Block block = this.getButWorld().getWorld().getBlockAt(0, heightY, 0);
		
		this.repeat(() -> {
			EnderDragon enderDragon = (EnderDragon) block.getWorld().spawn(block.getLocation(), EnderDragon.class);
			enderDragon.setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "Subscribe!");
			enderDragon.setCustomNameVisible(true);
			
		}, 20l * 60 * 3);
	}
	
	@Override
	public void onFinish()
	{
		for(Entity entity : this.getButWorld().getWorld().getEntities())
		{
			if(entity instanceof EnderDragon)
				entity.remove();
		}
	}
}
