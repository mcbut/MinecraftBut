package games.bevs.minecraftbut.senerario.senerarios;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;

public class FasterAndFaster extends Senerario
{
	@Optional(reload=true)
	private int secondsPerLevelUp = 40;	
	
	@Optional
	private int currentLevel = 0;	
	
	public FasterAndFaster(ButWorld butWorld) 
	{
		super("Everyones Speed increases", butWorld, Material.LEATHER_BOOTS, new String[] { "Every minute your speed gets faster" });
	}

	
	@Override
	public void onStart()
	{
		this.repeat(() -> {
			currentLevel++;
			for(Player player : Bukkit.getOnlinePlayers())
				if(player.getGameMode() == GameMode.SURVIVAL)
					applySpeed(player);
		}, 20l * secondsPerLevelUp);
	}
	
	@Override
	public void onFinish()
	{
		Bukkit.getOnlinePlayers().forEach(player -> player.removePotionEffect(PotionEffectType.SPEED));
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		applySpeed(player);
	}
	
	public void applySpeed(Player player)
	{
		player.removePotionEffect(PotionEffectType.SPEED);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 100, this.currentLevel, true, false));
	}
}
