package games.bevs.bees;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.bees.listener.BeeListener;
import games.bevs.bees.manager.BeeManager;

public class BeePlugin extends JavaPlugin
{
	private BeeManager beeManager;
	
	@Override
	public void onEnable()
	{
		beeManager = new BeeManager(this);
		
		BeeListener BeeListener = new BeeListener(beeManager);
		
		Bukkit.getPluginManager().registerEvents(BeeListener, this);
	}
	
	@Override
	public void onDisable()
	{
		this.beeManager.removeAll();
	}
}
