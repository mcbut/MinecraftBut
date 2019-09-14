package games.bevs.minecraftbut.senerarios;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;

public class NoJump extends Senerario
{
	public NoJump(ButWorld butWorld) 
	{
		super("No Jump", butWorld);
	}

	private void applyNoJump(Player player)
	{
		if(player.getGameMode() == GameMode.SURVIVAL)
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000, 20 * 60 * 60, false));
		}
	}
	
	
	@Override
	public void onStart()
	{
		Bukkit.getOnlinePlayers().forEach(player -> {
			applyNoJump(player);
		});
	}
	
	@Override
	public void onFinish()
	{
		Bukkit.getOnlinePlayers().forEach(player -> {
			if(player.getGameMode() == GameMode.SURVIVAL)
			{
				player.removePotionEffect(PotionEffectType.JUMP);
			}
		});
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		applyNoJump(player);
	}
}
