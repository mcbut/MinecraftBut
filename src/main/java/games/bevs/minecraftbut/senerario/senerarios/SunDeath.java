package games.bevs.minecraftbut.senerario.senerarios;

import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class SunDeath extends Senerario
{

	public SunDeath(ButWorld butWorld)
	{
		super("Sun Death", butWorld, Material.LAVA_BUCKET, new String[] {"You die in the sun"}, "Sprock");
	}

	
	@Override
	public void onStart()
	{
		this.repeat(() -> {
			for (Player onlinePlayer : Bukkit.getOnlinePlayers())
			{
				long worldTime = onlinePlayer.getWorld().getTime() % 24_000;
				if(!(worldTime > 0 && worldTime < 12_800))
					return;

				if(onlinePlayer.getGameMode() != GameMode.SURVIVAL)
					continue;

				if(onlinePlayer.isDead())
					continue;

				if(this.underSunLight(onlinePlayer))
					onlinePlayer.damage(1000000.0);
			}
		}, 10l);
	}
	
	@Override
	public void onFinish()
	{
		
	}

	public boolean underSunLight(Player player)
	{
		Location loc = player.getLocation();
		Block currentBlock = loc.getBlock();
		while(currentBlock.getY() < 255)
		{
			if(currentBlock.getType() != Material.AIR)
				return false;
			currentBlock = currentBlock.getRelative(BlockFace.UP);
		}
		return true;
	}
}
