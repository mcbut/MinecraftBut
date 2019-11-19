package games.bevs.minecraftbut.senerario.senerarios;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;

/**
 * 
 */
public class ShrinkingInventory extends Senerario
{	
	
	/**
	 * 35 32 29 26 23 20 17 14 11 8 5 2
	 * 34 31 28 25 22 19 16 13 10 7 4 1
	 * 33 30 27 24 21 18 15 12 9 6 3 0
	 * 
	 * 9 10 11 12 13 14 15 16 17
	 * 18 19 20 21 22 23 24 25 26 
	 * 27 28 29 30 31 32 33 34 35
	 * 0 1 2 3 4 5 6 7 8
	 */
	private static int[] MAPPINGS = {8, 35, 26, 17, 
									 7, 34, 25, 16,
									 6, 33, 24, 15,
									 5, 32, 23, 14,
									 4, 31, 22, 13,
									 3, 30, 21, 12,
									 2, 29, 20, 11,
									 1, 28, 19, 10,
									 0, 27, 18, 9
									 };
	@Optional(reload = true)
	private int secondsTillSpotFills = 60 * 2;
	@Optional
	private int fillSlotIndex = 0;
	
	private static final ItemStack BLOCKER_ITEM = new ItemStackBuilder(Material.BEDROCK).displayName(CC.bRed + "BLOCKED SLOT").build();
	
	
	public ShrinkingInventory(ButWorld butWorld) 
	{
		super("Shrinking Inventories", butWorld, Material.CHEST, new String[] {"You get less and less inventory space"}, "GeorgeNotFound");
	}
	
	
	@Override
	public void onStart()
	{
		this.repeat(() -> {
			Bukkit.getOnlinePlayers().forEach(player -> {
				fillToSlot(player);
			});
		}, secondsTillSpotFills * 20);
	}
	
	@Override
	public void onFinish()
	{
		Bukkit.getOnlinePlayers().forEach(player -> {
			List<ItemStack> itemsToRemove = new ArrayList<>(30);
			for(ItemStack item : player.getInventory())
				if(item.getType() == Material.BEDROCK)
					itemsToRemove.add(item);
			itemsToRemove.forEach(item -> player.getInventory().remove(item));
		});
	}
	

	public void fillToSlot(Player player)
	{
		for(int i = 0; i < this.fillSlotIndex; i++)
		{
			int slot = MAPPINGS[i];
			player.getInventory().setItem(slot, BLOCKER_ITEM);
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		List<ItemStack> itemsToRemove = new ArrayList<>(30);
		for(ItemStack item : e.getDrops())
			if(item.getType() == Material.BEDROCK)
				itemsToRemove.add(item);
		itemsToRemove.forEach(item -> e.getDrops().remove(item));
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e)
	{
		fillToSlot(e.getPlayer());
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e)
	{
		Player player = e.getPlayer();
		if(player.getGameMode() != GameMode.SURVIVAL) return;
		if(e.getItemDrop() == null) return;
		if(e.getItemDrop().getItemStack() == null) return;
		if(e.getItemDrop().getItemStack().getType() == Material.BEDROCK) 
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onInventory(InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		if(e.getInventory() == null) return;
		ItemStack item = e.getCurrentItem();
		if(item == null) return;
		if(item.getType() == Material.BEDROCK)
			e.setCancelled(true);
	
	}
}
