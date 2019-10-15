package games.bevs.minecraftbut.senerario;

import java.lang.reflect.Field;
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
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
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
		
		//handle auto optionals
		if(args.length > 0)
		{
			String fieldName = option;
			String value = args[0];
			if(args.length > 1)
			{
				for(int i = 1; i < args.length; i++)
				{
					value += " " + args[i];
				}
			}
			
			boolean success = false;
			
			Optional optional = this.getOptional(this, fieldName);
			if(optional == null)
			{
				player.sendMessage(CC.red + "Could not find '" + fieldName + "'!" );
				this.onHelp(player);
				return;
			}
			
			try {
				if(editOptionalField(this, fieldName, value))
					success = true;
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
			
			if(success)
			{
				String message = CC.red + "Failed really bad, try hit up Sprock on Discord @ Heath#5377";
				if(optional != null)
				{
					message = optional.successMessage();
					if(message.length() < 3)
						message = Optional.SUCCESS_MESSAGE;
					
					message = message.replaceAll("%name%", fieldName);
					message = message.replaceAll("%value%", "'" + value + "'");
				}
				player.sendMessage(message);
			}
			else
			{
				player.sendMessage(CC.red + "Something went wrong, incorrect type? int? true|false?");
			}
		}
		else
		{
			this.onHelp(player);
		}
	}
	
	protected String withBaseCommand(String option, String args)
	{
		return CC.red + "/MinecraftBut Senerario " + this.getId() + " "+ option + " " + args;
	}
	
	protected String withBaseCommand(String option, Class<?> clazz)
	{
		String args = "";
		if(clazz == String.class)
			args = "<Message>";
		else if(clazz == boolean.class)
			args = "<true|false>";
		else if(clazz == int.class)
			args = "<Number (1, 2, 3...)>";
		return CC.red + "/MinecraftBut Senerario " + this.getId() + " "+ option + " " + args;
	}
	
	public void help(CommandSender sender)
	{
		this.onHelp(sender);
	}
	
	protected void onHelp(CommandSender sender)
	{
		sender.sendMessage(withBaseCommand("status", "<Enable|Disable>"));
		for(Field optionField : this.getOptionalFields())
			sender.sendMessage(withBaseCommand(optionField.getName(), optionField.getType()));
	}
	
	public List<Field> getOptionalFields()
	{
		List<Field> fields = new ArrayList<>();
		for(Field field : this.getClass().getDeclaredFields())
		{
			Optional optional = field.getAnnotation(Optional.class);
			if(optional == null) continue;
			fields.add(field);
		}
		return fields;
	}
	
	/**
	 * Not the best method of doing this but it's good enough for this use case
	 * @param senerario
	 * @param variableName
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @return successful
	 */
	public boolean editOptionalField(Senerario senerario, String variableName, String value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		Field field = senerario.getClass().getDeclaredField(variableName);
		Optional optional = field.getAnnotation(Optional.class);
		if(optional == null)
			return false;
		
		field.setAccessible(true);
		
		if(field.getType() == String.class)
		{
			field.set(senerario, value);
			return true;
		}
		else if(field.getType() == int.class)
		{
			int valueAsInt = Integer.parseInt(value);
			field.set(senerario, valueAsInt);
			return true;
		}
		else if(field.getType() == boolean.class)
		{
			boolean valueAsBoolean = Boolean.parseBoolean(value.toLowerCase());
			field.set(senerario, valueAsBoolean);
			return true;
		}
		
		return false;
	}
	
	public Optional getOptional(Senerario senerario, String fieldName)
	{
		Field field;
		Optional optional = null;
		try {
			field = senerario.getClass().getDeclaredField(fieldName);
			optional = field.getAnnotation(Optional.class);
		} catch (NoSuchFieldException | SecurityException e) {
//			e.printStackTrace();//ssssshhh i know baby
		}
		return optional;
	}
}
