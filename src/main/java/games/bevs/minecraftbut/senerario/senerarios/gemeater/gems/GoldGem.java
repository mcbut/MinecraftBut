package games.bevs.minecraftbut.senerario.senerarios.gemeater.gems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GoldGem extends GemBase
{

	public GoldGem() 
	{
		super(Material.GOLD_INGOT, 2);
	}

	@Override
	public void onEat(Player player) 
	{
		player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 5, 0, true, true), true);
	}

}
