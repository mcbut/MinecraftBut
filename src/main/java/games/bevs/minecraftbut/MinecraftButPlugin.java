package games.bevs.minecraftbut;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.minecraftbut.commands.MinecraftButCommand;
import games.bevs.minecraftbut.senerario.ScenerarioManager;
import games.bevs.minecraftbut.senerario.senerarios.BedRockTrail;
import games.bevs.minecraftbut.senerario.senerarios.EnderDragonAppears;
import games.bevs.minecraftbut.senerario.senerarios.NoJump;
import games.bevs.minecraftbut.senerario.senerarios.OneHit;
import games.bevs.minecraftbut.senerario.senerarios.RainingBlocks;
import games.bevs.minecraftbut.senerario.senerarios.TNTOnSelf;
import games.bevs.minecraftbut.senerario.senerarios.TNTRain;
import games.bevs.minecraftbut.senerario.senerarios.TheLavaRises;
import games.bevs.minecraftbut.senerario.senerarios.TheWaterRises;
import games.bevs.minecraftbut.senerario.senerarios.gemeater.GemEater;
import games.bevs.minecraftbut.world.ButWorld;
import lombok.Getter;

@Getter
public class MinecraftButPlugin extends JavaPlugin
{
	private ButWorld butWorld;
	private ScenerarioManager scenerarioManager;
	
	@Override
	public void onEnable()
	{
		this.butWorld = new ButWorld(Bukkit.getWorlds().get(0));
		
		this.scenerarioManager = new ScenerarioManager();
		
		this.populateScenerarios(this.butWorld);
		
		this.registerCommands();
	}
	
	@Override
	public void onDisable()
	{
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
		this.scenerarioManager.registerSenerario(new BedRockTrail(butWorld));
		this.scenerarioManager.registerSenerario(new TNTOnSelf(butWorld));
		this.scenerarioManager.registerSenerario(new GemEater(butWorld));
	}
	
	private CommandMap getCommandMap()
	{
		try{
		    Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
		    commandMapField.setAccessible(true);
		    return (CommandMap) commandMapField.get(Bukkit.getServer());
		}
		catch(Exception exception){
		    exception.printStackTrace();
		}
		 
		return null;
	}
	
	private void registerCommands()
	{
		CommandMap commandMap = this.getCommandMap();
		if(commandMap == null)
		{
			for(int i = 0; i < 20; i++)
				Bukkit.broadcastMessage("");
			
			for(int i = 0; i < 20; i++)
			{
				Bukkit.broadcastMessage(" FUCK, I can't get access to the commandMap");
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage("");
			}
			return;
		}
		 
		MinecraftButCommand minecraftButCMD = new MinecraftButCommand(this.scenerarioManager);
		commandMap.register(minecraftButCMD.getName(), minecraftButCMD);
	}
}		
