package games.bevs.minecraftbut.senerario;

import java.util.HashMap;

import lombok.Getter;

public class ScenerarioManager 
{
	@Getter
	private HashMap<String, Senerario> senerarios; 
	
	public ScenerarioManager()
	{
		this.senerarios = new HashMap<>();
	}
	
	public void registerSenerario(Senerario senerario)
	{
		this.senerarios.put(senerario.getId().toLowerCase(), senerario);
	}
	
	public Senerario getSenerario(String name)
	{
		return this.getSenerarios().get(name.toLowerCase());
	}
}
