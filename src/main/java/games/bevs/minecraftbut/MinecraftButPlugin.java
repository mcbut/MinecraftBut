package games.bevs.minecraftbut;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerarios.NoJump;
import games.bevs.minecraftbut.world.ButWorld;

public class MinecraftButPlugin extends JavaPlugin
{
	private ButWorld butWorld;
	private Senerario senerario;
	
	@Override
	public void onEnable()
	{
		this.butWorld = new ButWorld(Bukkit.getWorlds().get(0));
		
		this.senerario = new NoJump(this.butWorld);
		this.senerario.start();
	}
	
	@Override
	public void onDisable()
	{
		this.senerario.finish();
	}
	
	public static JavaPlugin getPlugin()
	{
		return JavaPlugin.getPlugin(MinecraftButPlugin.class);
	}
}		
