package games.bevs.bees.types;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreature;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import games.bevs.bees.utils.SkullCreator;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.EntityCreature;
import net.minecraft.server.v1_8_R3.NavigationAbstract;

public class Bee {
	private static final String BEE_SKIN = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ3MzIyZjgzMWUzYzE2OGNmYmQzZTI4ZmU5MjUxNDRiMjYxZTc5ZWIzOWM3NzEzNDlmYWM1NWE4MTI2NDczIn19fQ==";

	private ArmorStand armorStand;
	private Chicken chicken;

	private @Getter @Setter Player player;
	private @Getter @Setter Location targetLocation;

	private double heightOffsetIn = 0.0;
	private double heightOffset = 0.0;
	private int tick = 0;

	public Bee() {

	}

	private ItemStack getBeeAsSkull() {
		ItemStack beeSkull = SkullCreator.itemFromBase64(BEE_SKIN);

		return beeSkull;
	}

	public void spawn(Location location) {
		this.armorStand = location.getWorld().spawn(location, ArmorStand.class);
		this.armorStand.setVisible(false);
		this.armorStand.setGravity(false);
		this.armorStand.setBasePlate(false);
		this.armorStand.setCanPickupItems(false);

		this.armorStand.setHelmet(this.getBeeAsSkull());
		this.armorStand.setSmall(true);

		this.armorStand.setCustomName(ChatColor.YELLOW + "Bee");
		this.armorStand.setCustomNameVisible(true);

		this.chicken = location.getWorld().spawn(location, Chicken.class);
//		this.chicken.setPassenger(this.armorStand);
		this.chicken.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20000000, 1));
	}

	public Location getLocation() {
		return this.armorStand.getLocation();
	}

	public void remove() {
		this.armorStand.remove();
		this.chicken.remove();
	}

	public boolean move() {
		if (this.targetLocation == null)
			return false;

		// A*
		Location goalState = this.targetLocation;
		Location initState = this.getLocation();

		int xDiff = Math.abs(initState.getBlockX() - goalState.getBlockX());
		int yDiff = Math.abs(initState.getBlockY() - goalState.getBlockY());
		int zDiff = Math.abs(initState.getBlockZ() - goalState.getBlockZ());

		if (xDiff + yDiff + zDiff <= 4)
			return true;
		
		EntityCreature ec = ((CraftCreature) this.chicken).getHandle();
		NavigationAbstract nav = ec.getNavigation();

		int xIndex = -1;
		int zIndex = -1;
		Block targetBlock = goalState.getBlock().getRelative(xIndex, -1, zIndex);
		while (targetBlock.isEmpty() || targetBlock.isLiquid()) {

			if (xIndex < 2) {
				xIndex++;
			} else if (zIndex < 2) {

				xIndex = -1;
				zIndex++;
			} else {
				return true;
			}

			targetBlock = goalState.getBlock().getRelative(xIndex, -1, zIndex);
		}

		nav.a(targetBlock.getX(), (targetBlock.getY() + 1), targetBlock.getZ(), 1.3f);
		return false;
	}

	public void update() {

		this.setTargetLocation(player.getLocation());
		boolean idle = move();

		// avoid blocks
		this.armorStand.teleport(this.chicken.getLocation().add(0, heightOffset, 0));
		if (tick % 2 == 0 || idle) {
			heightOffsetIn += 0.05;
			heightOffset = 0.3 * Math.sin((heightOffsetIn / 1.0) * Math.PI * 2);
		}
		this.tick++;
	}
}
