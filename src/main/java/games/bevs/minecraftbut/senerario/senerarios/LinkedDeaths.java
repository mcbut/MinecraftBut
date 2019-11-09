package games.bevs.minecraftbut.senerario.senerarios;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;

public class LinkedDeaths extends Senerario
{
	@Optional
	private long cooldownAfterFirstDeath = 8000l;
	private long lastDeathTimestamp = 0l;
	
	public LinkedDeaths(ButWorld butWorld) 
	{
		super("Linked Deaths", butWorld, Material.FISHING_ROD, new String[] {"One player dies, everyone dies"});
	}


	
	
	@Override
	public void onStart()
	{

	}
	
	@Override
	public void onFinish()
	{

	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		Player player = e.getEntity();
		if(System.currentTimeMillis() - this.lastDeathTimestamp < cooldownAfterFirstDeath)
			return;
		this.lastDeathTimestamp = System.currentTimeMillis();
		
		Bukkit.broadcastMessage(CC.b + player.getName() + " has messed it up for everyone!");
		Bukkit.getOnlinePlayers().forEach(other -> {
			if(other.getGameMode() == GameMode.SURVIVAL)
				other.damage(10000);
		});
	}
}
