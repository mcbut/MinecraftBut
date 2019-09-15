package games.bevs.minecraftbut.senerario;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import games.bevs.minecraftbut.MinecraftButPlugin;
import games.bevs.minecraftbut.world.ButWorld;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Senerario implements Listener
{
	private @NonNull String name;
	private @NonNull ButWorld butWorld;
	private @NonNull Material icon;
	private @NonNull String[] description;
	private boolean enabled = false;
	private ArrayList<Integer> runnableIds = new ArrayList<>();
	
	public void repeat(Runnable run, long sprints)
	{
		int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinecraftButPlugin.getPlugin(), run, sprints, sprints);
		this.runnableIds.add(id);
	}
	
	public void start()
	{
		Bukkit.getPluginManager().registerEvents(this, MinecraftButPlugin.getPlugin());
		
		this.enabled = true;
		this.onStart();
	}
	
	public void finish()
	{
		this.onFinish();
		
		this.enabled = false;
		
		this.runnableIds.forEach(id -> Bukkit.getScheduler().cancelTask(id));
		this.runnableIds.clear();
		
		HandlerList.unregisterAll(this);
	}
	
	protected void onStart()
	{
		
	}
	
	protected void onFinish()
	{
		
	}
}
