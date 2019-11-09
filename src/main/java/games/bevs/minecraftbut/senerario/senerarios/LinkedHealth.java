package games.bevs.minecraftbut.senerario.senerarios;

import java.util.HashMap;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;

import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;

public class LinkedHealth extends Senerario
{	
	@Optional
	private long cooldownAfterFirstDeath = 500l;
	private long lastDeathTimestamp = 0l;
	
	public LinkedHealth(ButWorld butWorld) 
	{
		super("Linked Health", butWorld, Material.APPLE, new String[] {"All players share health"});
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
	public void onDamage(EntityDamageEvent e)
	{
		if(!(e.getEntity() instanceof Player))
			return;
		
		if(System.currentTimeMillis() - this.lastDeathTimestamp < cooldownAfterFirstDeath)
			return;
		this.lastDeathTimestamp = System.currentTimeMillis();
		
		double damageTaken = e.getFinalDamage();
		Bukkit.getOnlinePlayers().forEach(other -> {
			if(other.getGameMode() == GameMode.SURVIVAL)
				other.damage(damageTaken);
		});
	}
}
