package games.bevs.minecraftbut.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import com.google.common.collect.ImmutableList;

import games.bevs.minecraftbut.MinecraftButPlugin;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.gui.SenerarioMenu;
import games.bevs.minecraftbut.senerario.ScenerarioManager;
import games.bevs.minecraftbut.senerario.Senerario;

public class MinecraftButCommand extends Command
{
	public static final String ROOT_PERMISSION = "minecraftbut.command.minecraftbut";
	
	private static List<String> SENERARIOS = null;
	
	private SenerarioMenu senerarioMenu = new SenerarioMenu(CC.b + "Senerario");
	
	private ScenerarioManager scenerarioManager;
	
	public MinecraftButCommand(ScenerarioManager scenerarioManager) {
		super("MinecraftBut", 
			"Pops up with the gui to edit the set up",
			"/MinecraftBut", Arrays.asList("mb"));
		
		this.scenerarioManager = scenerarioManager;
		
		Bukkit.getPluginManager().registerEvents(senerarioMenu, MinecraftButPlugin.getPlugin());
		
		ArrayList<String> scenerariosOut = new ArrayList<>();
		
		
		Iterator<Senerario> senerarios = MinecraftButPlugin.getPlugin().getScenerarioManager().getSenerarios().values().iterator();
		while(senerarios.hasNext())
			scenerariosOut.add(senerarios.next().getId());
		
		SENERARIOS = scenerariosOut;
	}
	
	private void sendSenerarioList(CommandSender sender)
	{
		sender.sendMessage(CC.aqua + "LIST OF SENERARIOS");
		
		Iterator<Senerario> senerarios = MinecraftButPlugin.getPlugin().getScenerarioManager().getSenerarios().values().iterator();
		while(senerarios.hasNext())
		{
			Senerario senerario = senerarios.next();
			sender.sendMessage(CC.aqua + senerario.getName() + " [ Id: " + senerario.getId() + " ]");
		}
	}
	
	public void listCredits(CommandSender sender)
	{
		sender.sendMessage(CC.green + "Sprock : https://www.youtube.com/channel/UCQlwZZLg3YZb5RYboR94jPQ");
		sender.sendMessage(CC.green + "GeorgeNotFound : https://www.youtube.com/user/GeorgeeeHDPlays");
		sender.sendMessage(CC.green + "BadBoyHalo : https://www.youtube.com/user/thesaintsofgames");
		sender.sendMessage(CC.green + "Dream : https://www.youtube.com/user/DreamTraps");
		sender.sendMessage(CC.green + "Skeppy : https://www.youtube.com/channel/UCzMjRlKVO9XIqH_crIFpi6w");
		sender.sendMessage(CC.green + "WilburSoot : https://www.youtube.com/channel/UC1n_PfsVqxllCcnMPlxBIjw");
		sender.sendMessage(CC.green + "CallMeCarson : https://www.youtube.com/user/TheBlueCrewPros");
		
	}

	@Override
	public boolean execute(CommandSender sender, String cmd, String[] args)
	{
		if(!sender.hasPermission(ROOT_PERMISSION))
		{
			sender.sendMessage(CC.red + "You do not have the permission " + ROOT_PERMISSION);
			return false;
		}
		
		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l"))
			{
				sendSenerarioList(sender);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("credit") || args[0].equalsIgnoreCase("credits") || args[0].equalsIgnoreCase("c"))
			{
				this.listCredits(sender);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("helpall") || args[0].equalsIgnoreCase("ha"))
			{
				Iterator<Senerario> senerarios = MinecraftButPlugin.getPlugin().getScenerarioManager().getSenerarios().values().iterator();
				while(senerarios.hasNext())
				{
					Senerario senerario = senerarios.next();
					senerario.help(sender);
				}
				return true;
			}
			
			if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h"))
			{
				sender.sendMessage(CC.aqua + "/MinecraftBut list - Will list all Senerario");
				sender.sendMessage(CC.aqua + "/MinecraftBut credit - List creators of each idea");
				sender.sendMessage(CC.aqua + "/MinecraftBut help - Helps with top level commands");
				sender.sendMessage(CC.aqua + "/MinecraftBut helpall - Will tell you all Senerario configs");
				sender.sendMessage(CC.aqua + "/MinecraftBut Senerario <SenerarioId> - Allows you to config a Senerario");
				sender.sendMessage(CC.aqua + "/MinecraftBut World <WorldName> - Set the world that is currently effect");
				sender.sendMessage(CC.aqua + "You can use 'Mb' as shorthand for 'MinecraftBut'");
				sender.sendMessage(CC.aqua + "You can use 's' as shorthand for 'Senerario'");
				return true;
			}
		}
		if(args.length >= 2)
		{
			if(args[0].equalsIgnoreCase("changeworld") || args[0].equalsIgnoreCase("cw"))
			{
				String worldName = args[1];
				World world = Bukkit.getWorld(worldName);
				if(world == null)
				{
					Bukkit.getWorlds().forEach( optionWorld -> sender.sendMessage("World Option: " + optionWorld.getName()));
					sender.sendMessage(CC.red + "This is not a world!");
					return false;
				}
				
				MinecraftButPlugin.getPlugin().getButWorld().setWorld(world);
				sender.sendMessage(CC.green + "MinecraftBut ] World set to " + world.getName());
			}
			
			if(args[0].equalsIgnoreCase("Senerario") || args[0].equalsIgnoreCase("s"))
			{
				String senerarioId = args[1];
				Senerario senerario = this.scenerarioManager.getSenerario(senerarioId);
				if(senerario == null)
				{
					this.sendSenerarioList(sender);
					sender.sendMessage(CC.red + "senerario not found!");
					return false;
				}
				
				return senerario.handleCommand(sender, args);
			}
		} else {
			if(sender instanceof Player)
			{
				senerarioMenu.open((Player) sender);
				return true;
			}
		}
		
		return false;
	}
	
	//Command will only be ran once in a while so speed doesn't matter
	@Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		if (args.length == 2) {
			return StringUtil.copyPartialMatches(args[1], SENERARIOS, new ArrayList<String>(SENERARIOS.size()));
		}
		
		if (args.length == 3) {
			//Get scene 
			String scene = args[1];
			Senerario senerario = this.scenerarioManager.getSenerario(scene);
			if(senerario != null) 
			{
				List<String> options = senerario.getOptionalFields().stream().map(field -> field.getName()).collect(Collectors.toList());
				return StringUtil.copyPartialMatches(args[2], options, new ArrayList<String>(options.size()));
			}
		}
		return ImmutableList.of();
    }

}
