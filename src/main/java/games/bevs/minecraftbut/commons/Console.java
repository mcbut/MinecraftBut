package games.bevs.minecraftbut.commons;

import org.bukkit.Bukkit;

import games.bevs.minecraftbut.commons.utils.CC;

public class Console {

	public static void log(String message)
	{
		Bukkit.broadcastMessage(CC.iGray + "[MinecraftBut]" + message);
	}
}
