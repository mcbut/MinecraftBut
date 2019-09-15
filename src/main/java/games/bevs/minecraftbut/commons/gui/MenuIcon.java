package games.bevs.minecraftbut.commons.gui;

import org.bukkit.entity.Player;

import games.bevs.minecraftbut.commons.utils.Callback;
import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuIcon 
{
	private ItemStackBuilder icon;
	private int slot;
	private Callback<Player> runnable;
}
