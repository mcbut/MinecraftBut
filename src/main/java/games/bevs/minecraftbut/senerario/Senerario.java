package games.bevs.minecraftbut.senerario;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import games.bevs.minecraftbut.MinecraftButPlugin;
import games.bevs.minecraftbut.commons.Console;
import games.bevs.minecraftbut.commons.utils.CC;
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
		if(this.enabled) return;
		
		Console.log("'" + this.getName() + "' has been Enabled");
		Bukkit.getPluginManager().registerEvents(this, MinecraftButPlugin.getPlugin());
		
		this.enabled = true;
		this.onStart();
	}
	
	public void finish()
	{
		if(!this.enabled) return;
		
		Console.log("'" + this.getName() + "' has been Disabled");
		this.onFinish();
		
		this.enabled = false;
		
		this.runnableIds.forEach(id -> Bukkit.getScheduler().cancelTask(id));
		this.runnableIds.clear();
		
		HandlerList.unregisterAll(this);
	}
	
	public String getId()
	{
		return this.getClass().getSimpleName();
	}
	
	/**
	 * /MinecraftBut Senerario [senerario] [Option] [Args] 
	 * /MinecraftBut Senerario [senerario] help
	 * @param sender
	 * @param rawArgs
	 * @return
	 */
	public boolean handleCommand(CommandSender sender, String[] rawArgs)
	{
		if(!(sender instanceof Player))
		{
			sender.sendMessage(CC.red + "Sorry, you must be a player to do this command");
			return false;
		}
		
		Player player = (Player) sender;
		
		if(rawArgs.length <= 2 || rawArgs[2].equalsIgnoreCase("help"))
		{
			this.onHelp(player);
			return true;
		}
		
		String option = rawArgs[2];

		List<String> argsList = new ArrayList<>();
		for(int i = 3; i < rawArgs.length; i++)
		{
			argsList.add(rawArgs[i]);
		}
		
		this.onCommand(player, option, argsList.stream().toArray(String[]::new));
		return true;
	}
	
	protected void onStart()
	{
		
	}
	
	protected void onFinish()
	{
		
	}
	
	/**
	 * A command handler for the senerario
	 * @param player
	 * @param option, Option of the gamemodes aka the 3rd arg
	 * @param args
	 */
	protected void onCommand(Player player, String option, String[] args)
	{
		if(option.equalsIgnoreCase("status"))
		{
			String state = args[0];
			if(state.equalsIgnoreCase("true") 
				|| state.equalsIgnoreCase("enable") 
				|| state.equalsIgnoreCase("on"))
			{
				this.start();
			}
			else 
			{
				this.finish();
			}
			return;
		}
		
		
	}
	
	protected String withBaseCommand(String option, String args)
	{
		return CC.red + "/MinecraftBut Senerario " + this.getId() + " "+ option + " " + args;
	}
	
	protected void onHelp(Player player)
	{
		player.sendMessage(withBaseCommand("status", "<Enable|Disable>"));
	}
}
