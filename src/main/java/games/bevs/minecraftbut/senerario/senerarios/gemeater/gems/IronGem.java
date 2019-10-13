package games.bevs.minecraftbut.senerario.senerarios.gemeater.gems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IronGem extends GemBase
{

	public IronGem() 
	{
		super(Material.IRON_INGOT, 2);
	}

	@Override
	public void onEat(Player player) 
	{
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 60, 0, true, true), true);
	}

}
