package games.bevs.minecraftbut.senerario.senerarios.gemeater.gems;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import games.bevs.minecraftbut.commons.utils.MathUtils;

/**
 * Has a chance of randomly enchanting an item
 * within your inventory
 */
public class LapisGem extends GemBase
{
	private static final Enchantment[] ALLOWED_ENCHANTS = { Enchantment.DURABILITY, Enchantment.DAMAGE_ALL, Enchantment.ARROW_INFINITE, Enchantment.DIG_SPEED, Enchantment.ARROW_KNOCKBACK, Enchantment.KNOCKBACK, Enchantment.DAMAGE_ARTHROPODS, Enchantment.FIRE_ASPECT, Enchantment.LOOT_BONUS_BLOCKS, Enchantment.SILK_TOUCH};
	
	public LapisGem() 
	{
		super(Material.LAPIS_ORE, 1);
	}

	@Override
	public void onEat(Player player) 
	{
		Random ran =  MathUtils.getRandom();
		int randomSlot = ran.nextInt(36);
		ItemStack item = player.getInventory().getItem(randomSlot);
		if(item == null || item.getType() == Material.AIR)
			return;
		int chanceOfEnchant = ran.nextInt(5);
		//1 / 5 chance of enchanting
		if(chanceOfEnchant == 2)
		{
			item.addUnsafeEnchantment(ALLOWED_ENCHANTS[ran.nextInt(ALLOWED_ENCHANTS.length)], ran.nextInt(100) <= 5 ? ran.nextInt(120) : (ran.nextInt(2) + 1));
		}
	}

}
