package games.bevs.minecraftbut.senerario.senerarios.deathout;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import games.bevs.minecraftbut.commons.XMaterial;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;

public class DeathOut extends Senerario
{
	@Optional
	private boolean kickOnDeath = false;
	@Optional
	private boolean spectatorOnDeath = true;
	
	public DeathOut(ButWorld butWorld) 
	{
		super("Death Out", butWorld, XMaterial.CACTUS.parseMaterial(), new String[] {"You're out if you die"}, "Skeppy" );
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
		e.getEntity().spigot().respawn();
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e)
	{
		Player player = e.getPlayer();
		if(player.getGameMode() != GameMode.SURVIVAL)
			return;
		
		if(this.spectatorOnDeath)
			player.setGameMode(GameMode.SPECTATOR);
		if(this.kickOnDeath)
			player.kickPlayer("You died");
		
	}
}
