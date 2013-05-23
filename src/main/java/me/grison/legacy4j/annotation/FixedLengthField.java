package me.grison.legacy4j.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates the length of a field. 
 * This annotation is checked only when the field's class
 * have a @FixedLengthRecord annotation.
 * 
 * @author Alexandre Grison <a.grison@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FixedLengthField {
	public int value();
}
