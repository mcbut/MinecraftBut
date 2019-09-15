package games.bevs.minecraftbut;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.minecraftbut.senerario.ScenerarioManager;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.senerarios.EnderDragonAppears;
import games.bevs.minecraftbut.senerario.senerarios.NoJump;
import games.bevs.minecraftbut.senerario.senerarios.OneHit;
import games.bevs.minecraftbut.senerario.senerarios.RainingBlocks;
import games.bevs.minecraftbut.senerario.senerarios.TNTRain;
import games.bevs.minecraftbut.senerario.senerarios.TheLavaRises;
import games.bevs.minecraftbut.senerario.senerarios.TheWaterRises;
import games.bevs.minecraftbut.world.ButWorld;
import lombok.Getter;

@Getter
public class MinecraftButPlugin extends JavaPlugin
{
	private ButWorld butWorld;
	private Senerario senerario;
	private ScenerarioManager scenerarioManager;
	
	@Override
	public void onEnable()
	{
		this.butWorld = new ButWorld(Bukkit.getWorlds().get(0));
		
		this.scenerarioManager = new ScenerarioManager();
		
		this.populateScenerarios(this.butWorld);
	}
	
	@Override
	public void onDisable()
	{
		this.senerario.finish();
	}
	
	public static MinecraftButPlugin getPlugin()
	{
		return JavaPlugin.getPlugin(MinecraftButPlugin.class);
	}
	
	private void populateScenerarios(ButWorld butWorld)
	{
		this.scenerarioManager.registerSenerario(new EnderDragonAppears(butWorld));
		this.scenerarioManager.registerSenerario(new NoJump(butWorld));
		this.scenerarioManager.registerSenerario(new OneHit(butWorld));
		this.scenerarioManager.registerSenerario(new RainingBlocks(butWorld));
		this.scenerarioManager.registerSenerario(new TheLavaRises(butWorld));
		this.scenerarioManager.registerSenerario(new TheWaterRises(butWorld));
		this.scenerarioManager.registerSenerario(new TNTRain(butWorld));
	}
}		
