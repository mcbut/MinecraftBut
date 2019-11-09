package games.bevs.minecraftbut.commons;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Modified from
 * https://www.spigotmc.org/threads/ichatbasecomponent-get-current-header-and-footer.352879/
 * Note: speeds don't matter to much as this is only used for a few players during a video
 * @author Toldi
 */
public class TabUtils {
	public static void sendTab(Player player, String header, String footer) {
		header = ChatColor.translateAlternateColorCodes('&', header);
		footer = ChatColor.translateAlternateColorCodes('&', footer);

		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

		try {
			Object tabHeader = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", new Class[] { String.class })
					.invoke(null, new Object[] { "{\"text\":\"" + header + "\"}" });
			Object tabFooter = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", new Class[] { String.class })
					.invoke(null, new Object[] { "{\"text\":\"" + footer + "\"}" });
			Constructor<?> titleConstructor = getNMSClass("PacketPlayOutPlayerListHeaderFooter")
					.getConstructor(new Class[0]);
			Object packet = titleConstructor.newInstance(new Object[0]);
			Field aField = null;
			Field bField = null;
			if (version.equals("v1_14_R1") || version.equals("v1_13_R2")) {
				aField = packet.getClass().getDeclaredField("header");
				aField.setAccessible(true);
				aField.set(packet, tabHeader);
				bField = packet.getClass().getDeclaredField("footer");
			} else {
				aField = packet.getClass().getDeclaredField("a");
				aField.setAccessible(true);
				aField.set(packet, tabHeader);
				bField = packet.getClass().getDeclaredField("b");
			}
			bField.setAccessible(true);
			bField.set(packet, tabFooter);
			sendPacket(player, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getNMSPlayer(Player p) throws Exception {
		return p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
	}

	private static Class<?> getNMSClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void sendPacket(Player player, Object packet) {
		try {
			Object handle = getNMSPlayer(player);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") })
					.invoke(playerConnection, new Object[] { packet });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
