package games.bevs.minecraftbut.senerario.senerarios.linkedinvs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;

/**
 * All players share an inventory
 * Requires 1.8 only
 */
public class LinkedInventories extends Senerario
{	
	public LinkedInventories(ButWorld butWorld) 
	{
		super("Linked Inventories", butWorld, Material.CHEST, new String[] {"All players share Inventories"}, "GeorgeNotFound");
	}
	
	
	@Override
	public void onStart()
	{
		Bukkit.getOnlinePlayers().forEach(player -> player.getInventory().clear());
	}
	
	@Override
	public void onFinish()
	{
		
	}
	
	public void updateInventoryForAll(Player player, long delay)
	{
		this.delay(() -> {
			for(Player onlinePlayer : Bukkit.getOnlinePlayers())
			{
				if(player == onlinePlayer) continue;
				onlinePlayer.getInventory().setArmorContents(player.getInventory().getArmorContents());
				onlinePlayer.getInventory().setContents(player.getInventory().getContents());
				onlinePlayer.updateInventory();
			}
		}, delay);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e)
	{
		Player player = e.getPlayer();
		this.updateInventoryForAll(player, 2);
	}
	
	@EventHandler
	public void onPick(PlayerPickupItemEvent e)
	{
		Player player = e.getPlayer();
		this.updateInventoryForAll(player, 2);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e)
	{
		Player player = e.getPlayer();
		this.updateInventoryForAll(player, 2);
	}
	
	@EventHandler
	public void onPlace(PlayerItemConsumeEvent e)
	{
		Player player = e.getPlayer();
		this.updateInventoryForAll(player, 2);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		Player player = e.getPlayer();
		this.updateInventoryForAll(player, 2);
	}
	
	@EventHandler
	public void onDrag(InventoryDragEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		this.updateInventoryForAll(player, 2);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		Player player = e.getEntity();
		this.updateInventoryForAll(player, 2);
	}
	
	@EventHandler
	public void onInventory(InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		this.updateInventoryForAll(player, 2);
	}
}
