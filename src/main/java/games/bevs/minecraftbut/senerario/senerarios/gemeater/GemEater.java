package games.bevs.minecraftbut.senerario.senerarios.gemeater;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import games.bevs.minecraftbut.commons.Sounds;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.senerarios.gemeater.gems.CoalGem;
import games.bevs.minecraftbut.senerario.senerarios.gemeater.gems.DiamondGem;
import games.bevs.minecraftbut.senerario.senerarios.gemeater.gems.GemBase;
import games.bevs.minecraftbut.senerario.senerarios.gemeater.gems.GoldGem;
import games.bevs.minecraftbut.senerario.senerarios.gemeater.gems.IronGem;
import games.bevs.minecraftbut.senerario.senerarios.gemeater.gems.LapisGem;
import games.bevs.minecraftbut.senerario.senerarios.gemeater.gems.RedstoneGem;
import games.bevs.minecraftbut.world.ButWorld;

public class GemEater extends Senerario
{	
	private GemManager gemManager;
	
	public GemEater(ButWorld butWorld) 
	{
		super("Gem Eater", butWorld, Material.IRON_INGOT, new String[] {"You can only eat ores", "coal, iron, gold, diamond, redstone, lapis"});
		
		this.gemManager = new GemManager();
		
		this.gemManager.registerGem(new CoalGem());
		this.gemManager.registerGem(new IronGem());
		this.gemManager.registerGem(new GoldGem());
		this.gemManager.registerGem(new LapisGem());
		this.gemManager.registerGem(new RedstoneGem());
		this.gemManager.registerGem(new DiamondGem());
	}

	
	@Override
	public void onStart()
	{
		
	}
	
	@Override
	public void onFinish()
	{
		
	}
	
	
	@EventHandler
	public void onConsumeNormalFood(PlayerItemConsumeEvent e)
	{
		e.setCancelled(true);
	}
			
	@EventHandler
	public void onEat(PlayerInteractEvent e)
	{
		Player player = e.getPlayer();
		int playerFoodLevel = player.getFoodLevel();
		
		ItemStack item = e.getItem();
		
		if(item == null || item.getType() == Material.AIR)
			return;
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			GemBase gem = this.gemManager.getGem(item.getType());
			player.setFoodLevel(Math.max(playerFoodLevel + gem.getHungerRefill(), 20));
			
			player.playSound(player.getLocation(), Sounds.BURP.bukkitSound(), 10, 1);
			//remove item
			int amount = item.getAmount();
			if(amount > 1)
				item.setAmount(amount - 1);
			else
				item = null;
			
			player.setItemInHand(item);
			gem.onEat(player);
			
			e.setCancelled(true);
		}
	}
}
