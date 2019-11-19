package games.bevs.minecraftbut.senerario.senerarios;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import games.bevs.minecraftbut.commons.XMaterial;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;

public class MobsMultiple extends Senerario
{
	@Optional
	private int multiplier = 10;
	
	public MobsMultiple(ButWorld butWorld) 
	{
		super("Mobs multiply", butWorld, XMaterial.EGG.parseMaterial(), new String[] {"Mobs multiply when you hit them"}, "Badboyhalo");
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
	public void onDamage(EntityDamageByEntityEvent e)
	{
		Entity damager = e.getDamager();
		if(!(damager instanceof Player)) return;
		if(!(e.getEntity() instanceof LivingEntity)) return;
		if(e.getEntity() instanceof Player) return;
		
		Entity entity = e.getEntity();
		for(int i =  0; i < this.multiplier; i++)
			entity.getWorld().spawnEntity(entity.getLocation(), entity.getType());
	}
}
