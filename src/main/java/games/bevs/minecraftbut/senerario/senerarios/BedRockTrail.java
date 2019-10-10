package games.bevs.minecraftbut.senerario.senerarios;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import games.bevs.minecraftbut.commons.Console;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;

public class BedRockTrail extends Senerario
{
	private HashMap<UUID, Location> lastLocation = new HashMap<>();
	
	private double speed = 0.2;
	public BedRockTrail(ButWorld butWorld) 
	{
		super("Bedrock Trail", butWorld, Material.BEDROCK, new String[] {"Where ever you move, you leave a bedrock trail"});
	}

	
	@Override
	public void onStart()
	{
	
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
		
		
		if(option.equalsIgnoreCase("speed"))
		{
			this.speed = argAsInt;
			if(this.isEnabled())
			{
				this.finish();
				this.start();
			}
			Console.log("Bedrock check speed set to " + argAsInt);
			
		}
	}
	
	@Override
	protected void onHelp(Player player)
	{
		super.onHelp(player);
		
		player.sendMessage(withBaseCommand("speed", "<Seconds (number)>"));
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e)
	{
		Player player = e.getPlayer();
		
		if(player.getGameMode() == GameMode.CREATIVE)
			return;
		Location lastLoc = lastLocation.get(player.getUniqueId());
		if(lastLoc == null)
		{
			this.lastLocation.put(player.getUniqueId(), player.getLocation());
			return;
		}
		
		Location lastLoc2D = lastLoc.clone();
		lastLoc2D.setY(0);
		
		Location loc2d = player.getLocation().clone();
		loc2d.setY(0);
		
		if(lastLoc2D.distance(loc2d) < 1.30)
			return;
		
		Block block = lastLoc.getBlock();
		block.setType(Material.BEDROCK);
		block.getRelative(BlockFace.UP).setType(Material.BEDROCK);
		
		this.lastLocation.put(player.getUniqueId(), player.getLocation());
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e)
	{
		
	}
}
