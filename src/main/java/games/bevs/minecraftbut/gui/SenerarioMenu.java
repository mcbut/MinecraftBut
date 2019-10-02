package games.bevs.minecraftbut.gui;

import java.util.Iterator;

import org.bukkit.Bukkit;

import games.bevs.minecraftbut.MinecraftButPlugin;
import games.bevs.minecraftbut.commons.gui.Menu;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;
import games.bevs.minecraftbut.senerario.Senerario;

public class SenerarioMenu extends Menu
{
	

	public SenerarioMenu(String title) 
	{
		super(title, 9 * 6);
		
		//draw all senerarios
		
		this.drawSenerarios();
	}

	
	
	protected void drawSenerarios()
	{
		Iterator<Senerario> senerarios = MinecraftButPlugin.getPlugin().getScenerarioManager().getSenerarios().values().iterator();
		int slot = 0;
		while(senerarios.hasNext())
		{
			Senerario senerario = senerarios.next();
			
			this.drawSenerario(slot, senerario);
			
			slot++;
		}
	}
	
	protected void drawSenerario(int slot, Senerario senerario)
	{
		this.setIcon(slot,
					 new ItemStackBuilder(senerario.getIcon())
					 	 .displayName(CC.b + (senerario.isEnabled() ? CC.green : CC.red) + senerario.getName())
					 	 .lore(senerario.getDescription())
					 	 .addLore(CC.gray + "Id: " + senerario.getId()),
					 (player) -> {
						 if(senerario.isEnabled())
						 {
							 senerario.finish();
							 this.drawSenerario(slot, senerario);
						 }
						 else
						 {
							 senerario.start();
							 this.drawSenerario(slot, senerario);
						 }
						 
					 });
	}
}
