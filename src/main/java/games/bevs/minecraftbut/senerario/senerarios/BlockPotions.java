package games.bevs.minecraftbut.senerario.senerarios;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import games.bevs.minecraftbut.commons.XMaterial;
import games.bevs.minecraftbut.commons.utils.MathUtils;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;

public class BlockPotions extends Senerario
{
	private static final PotionEffectType[] EFFECTS = new PotionEffectType[] { PotionEffectType.BLINDNESS, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.HEALTH_BOOST, PotionEffectType.JUMP, PotionEffectType.SPEED, PotionEffectType.HUNGER, PotionEffectType.HARM, PotionEffectType.CONFUSION, PotionEffectType.INVISIBILITY, PotionEffectType.POISON, PotionEffectType.SATURATION, PotionEffectType.SLOW, PotionEffectType.SLOW_DIGGING, PotionEffectType.WITHER, PotionEffectType.WEAKNESS};
	@Optional
	private int maxPotionLevel = 5;
	@Optional
	private int minPotionLevel = 0;
	
	@Optional
	private int maxPotionSeconds = 30;
	@Optional
	private int minPotionSeconds = 10;
	
	public BlockPotions(ButWorld butWorld) 
	{
		super("Block Potion", butWorld, XMaterial.POTION.parseMaterial(), new String[] {"Blocks with potion effects"}, "BajanCandian");
	}	
	
	@Override
	public void onStart()
	{
	
	}
	
	@Override
	public void onFinish()
	{
		
	}
	
	/**
	 * Apply a random potion to the player using the options defend above
	 * @param player
	 */
	public void applyPotion(Player player)
	{
		//random effect
		PotionEffectType effectType = EFFECTS[MathUtils.getRandom().nextInt(EFFECTS.length)];
		int secondsDuration = MathUtils.getRandom().nextInt(this.maxPotionSeconds - this.minPotionSeconds) + this.minPotionSeconds;
		int potionLevel = MathUtils.getRandom().nextInt(this.maxPotionLevel - this.minPotionLevel) + this.minPotionLevel;
		player.addPotionEffect(new PotionEffect(effectType, 20 * secondsDuration, potionLevel, true, false));
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e)
	{
		Player player = e.getPlayer();
		if(player.getGameMode() == GameMode.CREATIVE) return;
		this.applyPotion(player);
	}
}
