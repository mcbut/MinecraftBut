package games.bevs.minecraftbut.senerario.senerarios.gemeater.gems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DiamondGem extends GemBase
{

	public DiamondGem() 
	{
		super(Material.DIAMOND, 20);
	}

	@Override
	public void onEat(Player player) 
	{
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 10, 2, true, true), true);
	}

}
