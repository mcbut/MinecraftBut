package games.bevs.bees.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import games.bevs.bees.manager.BeeManager;
import games.bevs.bees.types.Bee;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BeeListener implements Listener
{
	private @Getter BeeManager beeManager;
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e)
	{
		Player player = e.getPlayer();
		Location location = player.getLocation();
		
		if(e.isSneaking()) 
			return;
		
		Bee bee = new Bee();
		bee.spawn(location);
		bee.setPlayer(player);
		this.getBeeManager().addBee(bee);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e)
	{
		
	}
}
