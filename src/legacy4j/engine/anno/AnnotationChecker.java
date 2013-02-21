package legacy4j.engine.anno;

import java.lang.reflect.Field;
import java.text.ParseException;

import legacy4j.annotation.FixedLengthField;
import legacy4j.engine.mappers.EngineMappers;

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
		//FixedLengthField flf = field.getAnnotation(FixedLengthField.class);
		//if (flf == null) return 0;
		
		// apply the strategy
		//String value = line.substring(0, flf.value());
		return EngineMappers.getMapper(field.getType()).fill(record, field, line);
	}
	
	public static boolean isLegacy4jAnnotated(Field f) {
		return true;
	}
}
