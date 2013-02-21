package legacy4j.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import legacy4j.annotation.filter.ConditionType;
import legacy4j.annotation.filter.RecordCondition;

/**
 * Indicates the length of a field. 
 * This annotatio is checked only when the field's class 
 * have a @FixedLengthRecord annotation.
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
