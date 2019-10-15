package games.bevs.minecraftbut.commons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;

public class FallingBlockUtils {

	private static Class<? extends FallingBlock> fallingclazz;
	private static Method method;

	static 
	{
		try {
			fallingclazz = FallingBlock.class;
			method = fallingclazz.getDeclaredMethod("getBlockData");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	//if(fallingBlock.getBlockData().getMaterial() == XMaterial.ANVIL.parseMaterial()) 1.14
	//if(fallingBlock.getBlockId() == 145) 1.8
	// will need optimizing
	public static boolean fallingBlockMatch(FallingBlock fallingBlock, Material material) {
//		try {
//			Class<? extends FallingBlock> fallingclazz = fallingBlock.getClass();
//			Method method = fallingclazz.getDeclaredMethod("getBlockData");
//			Object blockData = method.invoke(fallingBlock);
//			Class<? extends Object> blockDataClazz = blockData.getClass();
//			Method method2 = blockDataClazz.getDeclaredMethod("getMaterial");
//			Material fallingMaterial = (Material) method2.invoke(blockData);
//			return fallingMaterial == material;
//		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
//			e.printStackTrace();
			return fallingBlock.getBlockId() == material.getId();
//		}
	}
}
