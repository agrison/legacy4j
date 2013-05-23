package me.grison.legacy4j.engine.anno;

import java.lang.reflect.Field;
import java.text.ParseException;

import me.grison.legacy4j.engine.mappers.EngineMappers;

/**
 * Check for legacy4j annotations on java models.
 *
 * @author Alexandre Grison <a.grison@gmail.com>
 */
public class AnnotationChecker {
	private Object record;
	private Field field;
	
	public static AnnotationChecker inspect(Object record, Field field) {
		AnnotationChecker ac = new AnnotationChecker();
		ac.record = record;
		ac.field = field;
		return ac;
	}
	
	public int applyModifiers(String line) 
	throws IllegalArgumentException, IllegalAccessException, ParseException {
		// first get the FixedLengthField annotation
		if (!AnnotationChecker.isLegacy4jAnnotated(field))
			return 0;
		
		// apply the strategy
		return EngineMappers.getMapper(field.getType()).fill(record, field, line);
	}
	
	public static boolean isLegacy4jAnnotated(Field f) {
		return true;
	}
}
