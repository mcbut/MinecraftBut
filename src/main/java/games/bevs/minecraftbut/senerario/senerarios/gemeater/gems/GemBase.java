package games.bevs.minecraftbut.senerario.senerarios.gemeater.gems;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public abstract class GemBase
{
	private @NonNull Material material;
	private short data = 0;
	private @NonNull int hungerRefill = 1;
	
	public GemBase(@NonNull Material material, @NonNull int hungerRefill) {
		super();
		this.material = material;
		this.data = 0;
		this.hungerRefill = hungerRefill;
	}
	
	public GemBase(@NonNull Material material, short data, @NonNull int hungerRefill) {
		super();
		this.material = material;
		this.data = data;
		this.hungerRefill = hungerRefill;
	}
	
	public abstract void onEat(Player player);


}
