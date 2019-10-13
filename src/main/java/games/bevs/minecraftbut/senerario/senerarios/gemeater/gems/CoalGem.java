package games.bevs.minecraftbut.senerario.senerarios.gemeater.gems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CoalGem extends GemBase
{

	public CoalGem() 
	{
		super(Material.COAL, 1);
	}

	@Override
	public void onEat(Player player) 
	{
		player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20 * 10, 1, true, true), true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 4, 1, true, true), true);
	}

}
