package games.bevs.minecraftbut.senerario.senerarios.gemeater.gems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import games.bevs.minecraftbut.commons.utils.CC;

public class EmeraldGem extends GemBase
{

	public EmeraldGem() 
	{
		super(Material.EMERALD, 1);
	}

	@Override
	public void onEat(Player player) 
	{
		Villager village = player.getWorld().spawn(player.getLocation(), Villager.class);
		village.setCustomNameVisible(true);
		village.setCustomName(CC.bYellow + "Demonized");
	}

}
