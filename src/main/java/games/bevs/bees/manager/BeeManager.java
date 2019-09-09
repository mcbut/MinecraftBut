package games.bevs.bees.manager;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.bees.types.Bee;

public class BeeManager 
{
	private List<Bee> bees = new LinkedList<>();
	
	public BeeManager(JavaPlugin plugin)
	{
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
			this.bees.forEach(bee -> bee.update());
		}, 1l, 1l);
	}
	
	public void addBee(Bee bee)
	{
		this.bees.add(bee);
	}
	
	public void removeAll()
	{
		this.bees.forEach(bee -> bee.remove());
		this.bees.clear();
	}
}
