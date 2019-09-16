package games.bevs.minecraftbut.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import games.bevs.minecraftbut.MinecraftButPlugin;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.gui.SenerarioMenu;

public class MinecraftButCommand extends Command
{

	private SenerarioMenu senerarioMenu = new SenerarioMenu(CC.b + "Senerario");
	
	public MinecraftButCommand() {
		super("MinecraftBut", 
			"Pops up with the gui to edit the set up",
			"/MinecraftBut", Arrays.asList("mb"));
		
		Bukkit.getPluginManager().registerEvents(senerarioMenu, MinecraftButPlugin.getPlugin());
	}

	@Override
	public boolean execute(CommandSender sender, String cmd, String[] args)
	{
		
		if(sender instanceof Player)
		{
			senerarioMenu.open((Player) sender);
			return true;
		}
		
		return false;
	}

}
