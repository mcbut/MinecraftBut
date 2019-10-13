package games.bevs.minecraftbut.senerario.senerarios.gemeater.gems;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class GemBase
{
	private Material material;
	private int hungerRefill = 1;
	
	public abstract void onEat(Player player);
}
