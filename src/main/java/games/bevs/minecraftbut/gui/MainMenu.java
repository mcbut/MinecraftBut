package games.bevs.minecraftbut.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import games.bevs.minecraftbut.commons.gui.Menu;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.commons.utils.ItemStackBuilder;

public class MainMenu extends Menu
{
	private ArcadeMenu arcadeMenu;
	private ClassicMenu classicMenu;

	public MainMenu() 
	{
		super(CC.bWhite + "Main Menu", 9 * 6);
		
		this.arcadeMenu = new ArcadeMenu();
		this.classicMenu = new ClassicMenu();
		
		this.setIcon(0, 
				 new ItemStackBuilder(Material.CARROT_STICK)
				 	 	.displayName(CC.bold + "Arcade")
				 	 	.lore(CC.lPurple + "The senerarios will change",
				 	 		  CC.lPurple + "every set period"),
				 (player) -> {
					 openArcade(player);
				 });
		
		this.setIcon(1, 
				 new ItemStackBuilder(Material.GRASS)
				 	 	.displayName(CC.bold + "Classic")
				 	 	.lore(CC.lPurple + "Select what senerarios",
				 	 		  CC.lPurple + "you want to have active"),
				 (player) -> {
					 openClassic(player);
				 });
	}
	
	
	private void openArcade(Player player)
	{
		this.arcadeMenu.open(player);
	}
	
	
	private void openClassic(Player player)
	{
		this.classicMenu.open(player);
	}
}
