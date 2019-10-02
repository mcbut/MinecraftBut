package games.bevs.minecraftbut.commands;

import java.util.Arrays;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import games.bevs.minecraftbut.MinecraftButPlugin;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.gui.SenerarioMenu;
import games.bevs.minecraftbut.senerario.ScenerarioManager;
import games.bevs.minecraftbut.senerario.Senerario;

public class MinecraftButCommand extends Command
{

	private SenerarioMenu senerarioMenu = new SenerarioMenu(CC.b + "Senerario");
	
	private ScenerarioManager scenerarioManager;
	
	public MinecraftButCommand(ScenerarioManager scenerarioManager) {
		super("MinecraftBut", 
			"Pops up with the gui to edit the set up",
			"/MinecraftBut", Arrays.asList("mb"));
		
		this.scenerarioManager = scenerarioManager;
		
		Bukkit.getPluginManager().registerEvents(senerarioMenu, MinecraftButPlugin.getPlugin());
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

	@Override
	public boolean execute(CommandSender sender, String cmd, String[] args)
	{
		
		if(args.length >= 2)
		{
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

}
