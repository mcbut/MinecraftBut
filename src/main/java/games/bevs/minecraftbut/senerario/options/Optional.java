package games.bevs.minecraftbut.senerario.options;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import games.bevs.minecraftbut.commons.utils.CC;

/**
 * Used so that we can configure variables with commands
 */
@Target(value={ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Optional
{
	public static final String SUCCESS_MESSAGE = CC.green + "Updated %name% to %value%";
	/**
	 * if the option requires reloading the scenerario
	 * such as schudalers.
	 * @return requires reload
	 */
	public boolean reload() default false;
	/**
	 * %Name% is the name of the variable
	 * %value% is the new value of the variable
	 * @return message sent to player when command works
	 */
	public String successMessage() default "";
	
	public String description() default "Unknown";
}
