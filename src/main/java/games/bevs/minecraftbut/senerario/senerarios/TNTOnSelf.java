package games.bevs.minecraftbut.senerario.senerarios;

import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import games.bevs.minecraftbut.commons.Console;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;

public class TNTOnSelf extends Senerario
{
	@Optional
	private int triggerBelow = 200;
	@Optional
	private int secondsBetweenTNT = 60;
	
	public TNTOnSelf(ButWorld butWorld) 
	{
		super("TNT On Self", butWorld, Material.TNT, new String[] {"TNT will spawn on you every minute"});
	}

	
	@Override
	public void onStart()
	{
		this.repeat(() -> {
			for(Player player : Bukkit.getOnlinePlayers())
			{
				Location location = player.getLocation();
				
				//Only target players in survival
				if(player.getGameMode() != GameMode.SURVIVAL)
					continue;
				
				if(location.getBlockY() < this.triggerBelow)
					player.getWorld().spawn(location, TNTPrimed.class);
			}
		}, 20l * secondsBetweenTNT);
	}
	
	@Override
	public void onFinish()
	{
		
	}
	
//	@Override
//	protected void onCommand(Player player, String option, String[] args)
//	{
//		super.onCommand(player, option, args);
//		
//		if(args.length != 1)
//		{
//			this.onHelp(player);
//			player.sendMessage(CC.red + "incorrect number of args");
//		}
//		
//		String firstArg = args[0];
//		int argAsInt = 0;
//		if(NumberUtils.isNumber(firstArg))
//		{
//			argAsInt = Integer.parseInt(firstArg);
//		}
//		else
//		{
//			this.onHelp(player);
//			player.sendMessage(CC.red + "Must be a number");
//			return;
//		}
//		
//		if(option.equalsIgnoreCase("triggerBelow"))
//		{
//			this.triggerBelow = argAsInt;
//			Console.log("TNT will go off under " + this.triggerBelow);
//		}
//		
//		if(option.equalsIgnoreCase("LavaSpeed"))
//		{
//			this.secondsBetweenTNT = argAsInt;
//			if(this.isEnabled())
//			{
//				this.finish();
//				this.start();
//			}
//			Console.log("Tnt will spawn every " + argAsInt + " seconds");
//			
//		}
//	}
	
//	@Override
//	protected void onHelp(Player player)
//	{
//		super.onHelp(player);
//		
//		player.sendMessage(withBaseCommand("triggerBelow", "<Y (number)>"));
//		player.sendMessage(withBaseCommand("secondsBetweenTNT", "<Seconds (number)>"));
//	}
	
}
