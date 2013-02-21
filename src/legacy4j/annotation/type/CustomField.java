package legacy4j.annotation.type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates this field is a Custom type. 
 * 
 * @author Alexandre Grison <a.grison@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomField {
	public Class<?> value();
}
