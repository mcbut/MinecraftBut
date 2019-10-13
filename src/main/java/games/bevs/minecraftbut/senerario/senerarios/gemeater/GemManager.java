package games.bevs.minecraftbut.senerario.senerarios.gemeater;

import java.util.HashMap;

import org.bukkit.Material;

import games.bevs.minecraftbut.senerario.senerarios.gemeater.gems.GemBase;

public class GemManager 
{
	private HashMap<Material, GemBase> gems = new HashMap<>();
	
	public GemManager()
	{
		
	}
	
	public void registerGem(GemBase gem)
	{
		this.gems.put(gem.getMaterial(), gem);
	}
	
	public GemBase getGem(Material mat)
	{
		return this.gems.get(mat);
	}
}
