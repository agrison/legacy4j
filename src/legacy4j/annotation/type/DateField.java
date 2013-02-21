package legacy4j.annotation.type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates this field is a Date. 
 * 
 * @author Alexandre Grison <a.grison@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DateField {
	public String value() default "yyyyMMdd";
}
