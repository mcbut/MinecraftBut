package games.bevs.minecraftbut.senerario.senerarios.gemeater.gems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RedstoneGem extends GemBase
{

	public RedstoneGem() 
	{
		super(Material.REDSTONE, 1);
	}

	@Override
	public void onEat(Player player) 
	{
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 1, true, true), true);
	}

}
