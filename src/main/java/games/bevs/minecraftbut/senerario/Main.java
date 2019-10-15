package games.bevs.minecraftbut.senerario;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//
//import games.bevs.minecraftbut.senerario.options.Optional;
//import games.bevs.minecraftbut.senerario.senerarios.DummySenerario;
//
//public class Main {
//
//	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException 
//	{
//		DummySenerario dummySenerario = new DummySenerario();
//		try {
//			command(dummySenerario, "randomValue", "10");
//			command(dummySenerario, "textSent", "Hey guys");
//		} catch (NoSuchFieldException | SecurityException e) {
//			e.printStackTrace();
//		}
//		
//		
//		//Set valie
//	}
//	
//	/**
//	 * Not the best method of doing this but it's good enough for this use case
//	 * @param senerario
//	 * @param variableName
//	 * @param value
//	 * @throws IllegalArgumentException
//	 * @throws IllegalAccessException
//	 * @throws NoSuchFieldException
//	 * @throws SecurityException
//	 * @return successful
//	 */
//	public static boolean command(Senerario senerario, String variableName, String value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
//	{
//		Field field = senerario.getClass().getDeclaredField(variableName);
//		Optional optional = field.getAnnotation(Optional.class);
//		if(optional == null)
//			return false;
//		
//		field.setAccessible(true);
//		
//		if(field.getType() == String.class)
//		{
//			field.set(senerario, value);
//			return true;
//		}
//		else if(field.getType() == int.class)
//		{
//			int valueAsInt = Integer.parseInt(value);
//			field.set(senerario, valueAsInt);
//			return true;
//		}
//		else if(field.getType() == boolean.class)
//		{
//			boolean valueAsBoolean = Boolean.parseBoolean(value.toLowerCase());
//			field.set(senerario, valueAsBoolean);
//			return true;
//		}
//		
//		return false;
//	}
//}
