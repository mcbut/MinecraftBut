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

public class TheWaterRises extends Senerario
{
	private int hieghtY = 8;
	private long waterSpeed = 45l;
	
	private Material waterMaterial = XMaterial.WATER.parseMaterial();
	private Material signMaterial = XMaterial.SIGN.parseMaterial();
	private Material signWallMaterial = XMaterial.WALL_SIGN.parseMaterial();
	private Material surgarCaneMaterial = XMaterial.SUGAR_CANE.parseMaterial();
	
	
	public TheWaterRises(ButWorld butWorld) 
	{
		super("The Water is rising", butWorld, Material.WATER_BUCKET, new String[] { "Every minute (or amount of time you set) the water level will raise" });
	}

	
	@Override
	public void onStart()
	{
		this.repeat(() -> {
			this.hieghtY--;
			this.hieghtY--;
			fill();
			fill();
			fill();
			Bukkit.broadcastMessage(ChatColor.BOLD + "The water has risen");
		}, 20l * waterSpeed);
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
		
		if(option.equalsIgnoreCase("waterStartLevel"))
		{
			this.hieghtY = argAsInt;
			Console.log("Lava height set to " + this.hieghtY);
		}
		
		if(option.equalsIgnoreCase("waterSpeed"))
		{
			this.waterSpeed = argAsInt;
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
		
		sender.sendMessage(withBaseCommand("WaterStartLevel", "<Y (number)>"));
		sender.sendMessage(withBaseCommand("WaterSpeed", "<Seconds (number)>"));
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
					block.setType(waterMaterial);
				else if(block.getType() == signMaterial || block.getType() == signWallMaterial || block.getType() == Material.LADDER || block.getType() == surgarCaneMaterial)
					block.setType(waterMaterial);
			}
		}
		
		hieghtY++;
	}
}
