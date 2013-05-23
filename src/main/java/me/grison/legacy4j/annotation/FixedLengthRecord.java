package me.grison.legacy4j.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import me.grison.legacy4j.annotation.filter.ConditionType;
import me.grison.legacy4j.annotation.filter.RecordCondition;

/**
 * Indicates that a POJO is a fixed length record.
 * 
 * @author Alexandre Grison <a.grison@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FixedLengthRecord {
	public String value() default "";
	public int ignoreFirst() default 0;
	public int ignoreLast() default 0;
	public boolean ignoreEmpty() default false;
	public String ignoreMatching() default "";
	public RecordCondition condition() default RecordCondition.None;
	public ConditionType conditionType() default ConditionType.Include;
}
