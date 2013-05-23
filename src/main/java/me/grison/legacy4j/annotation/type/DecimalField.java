package me.grison.legacy4j.annotation.type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates this field is a Decimal. 
 * 
 * @author Alexandre Grison <a.grison@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalField {
	public int[] value();
	public String separator() default "";
}
