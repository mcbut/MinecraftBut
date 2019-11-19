package games.bevs.minecraftbut.senerario.senerarios;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;

public class OneHit extends Senerario
{
	public OneHit(ButWorld butWorld) 
	{
		super("One Hit", butWorld, Material.BONE, new String[] {"You are given only one heart"}, "Sprock");
	}

	private void applyOneHit(Player player)
	{
		if(player.getGameMode() == GameMode.SURVIVAL)
		{
			player.setMaxHealth(2);
		}
	}
	
	
	@Override
	public void onStart()
	{
		Bukkit.getOnlinePlayers().forEach(player -> {
			applyOneHit(player);
		});
	}
	
	@Override
	public void onFinish()
	{
		Bukkit.getOnlinePlayers().forEach(player -> {
			if(player.getGameMode() == GameMode.SURVIVAL)
			{
				player.setMaxHealth(20);
			}
		});
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		applyOneHit(player);
	}
}
