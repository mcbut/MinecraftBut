package games.bevs.minecraftbut.senerario.senerarios.linkedinvs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.world.ButWorld;
import net.minecraft.server.v1_8_R3.PlayerInventory;

/**
 * All players share an inventory
 * Requires 1.8 only
 */
public class LinkedInventories extends Senerario
{	
	public LinkedInventories(ButWorld butWorld) 
	{
		super("Linked Inventories", butWorld, Material.CHEST, new String[] {"All players share Inventories"});
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
	
	@EventHandler
	public void onInventory(PlayerInventoryEvent e)
	{
		Player player = e.getPlayer();
		for(Player onlinePlayer : Bukkit.getOnlinePlayers())
		{
			if(player == onlinePlayer) continue;
			onlinePlayer.getInventory().setArmorContents(player.getInventory().getArmorContents());
			onlinePlayer.getInventory().setContents(player.getInventory().getContents());
		}
	}
}
