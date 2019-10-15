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
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;
import net.md_5.bungee.api.ChatColor;

public class TheLavaRises extends Senerario
{
	private int hieghtY = 173;
	private long lavaSpeed = 45l;
	
	private Material lavaMaterial = XMaterial.LAVA.parseMaterial();
	private Material signMaterial = XMaterial.SIGN.parseMaterial();
	private Material signWallMaterial = XMaterial.WALL_SIGN.parseMaterial();
	private Material surgarCaneMaterial = XMaterial.SUGAR_CANE.parseMaterial();
	
	public TheLavaRises(ButWorld butWorld) 
	{
		super("The Lava is rising", butWorld, Material.LAVA_BUCKET, new String[] {"Every minute (or the amount of time you set) the lava level will riase"});
	}

	
	@Override
	public void onStart()
	{
		this.repeat(() -> {
			fill();
		}, 20l * lavaSpeed);
	}
	
	@Override
	public void onFinish()
	{
		
	}
	
	@Override
	protected void onCommand(Player player, String option, String[] args)
	{
		super.onCommand(player, option, args);
		
		if(args.length != 1)
		{
			this.onHelp(player);
			player.sendMessage(CC.red + "incorrect number of args");
		}
		
		String firstArg = args[0];
		int argAsInt = 0;
		if(NumberUtils.isNumber(firstArg))
		{
			argAsInt = Integer.parseInt(firstArg);
		}
		else
		{
			this.onHelp(player);
			player.sendMessage(CC.red + "Must be a number");
			return;
		}
		
		if(option.equalsIgnoreCase("LavaStartLevel"))
		{
			this.hieghtY = argAsInt;
			Console.log("Lava height set to " + this.hieghtY);
		}
		
		if(option.equalsIgnoreCase("LavaSpeed"))
		{
			this.lavaSpeed = argAsInt;
			if(this.isEnabled())
			{
				this.finish();
				this.start();
			}
			Console.log("Lava speed set to " + argAsInt);
			
		}
	}
	
	@Override
	protected void onHelp(CommandSender sender)
	{
		super.onHelp(sender);
		
		sender.sendMessage(withBaseCommand("LavaStartLevel", "<Y (number)>"));
		sender.sendMessage(withBaseCommand("LavaSpeed", "<Seconds (number)>"));
	}
	
	private void fill()
	{
		//reached max height
		if(hieghtY >= 256)
			return;
			
		for(int x = this.getButWorld().getMinLocation().getBlockX(); x < this.getButWorld().getMaxLocation().getBlockX(); x++)
		{
			for(int z = this.getButWorld().getMinLocation().getBlockZ(); z < this.getButWorld().getMaxLocation().getBlockZ(); z++)
			{
				Block block = this.getButWorld().getWorld().getBlockAt( x, hieghtY, z);
				if(!block.getType().isSolid())
					block.setType(lavaMaterial);
				else if(block.getType() == signMaterial || block.getType() == signWallMaterial || block.getType() == Material.LADDER || block.getType() == surgarCaneMaterial)
					block.setType(lavaMaterial);
			}
		}
		
		hieghtY++;
		Bukkit.broadcastMessage(ChatColor.BOLD + "Lava has gone up");
	}
	
	
}
