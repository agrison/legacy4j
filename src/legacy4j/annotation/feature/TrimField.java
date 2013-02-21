package legacy4j.annotation.feature;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates this field should be trimed. 
 * 
 * @author Alexandre Grison <a.grison@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TrimField {
	public enum Type { Both, Left, Right }
	public Type value() default Type.Both;
}
