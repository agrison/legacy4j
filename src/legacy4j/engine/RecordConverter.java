package legacy4j.engine;

import java.lang.reflect.Field;
import java.text.ParseException;

import legacy4j.engine.anno.AnnotationChecker;
import legacy4j.engine.mappers.EngineMappers;

public class RecordConverter<T> {
	public static<T> T fromString(Class<T> clazz, String line) {
		T inst = null;
		try {
			inst = clazz.newInstance();
			int pos = 0;
			for (Field f: clazz.getDeclaredFields()) {
				String restOfLine = line.substring(pos);
				pos += AnnotationChecker.inspect(inst, f).applyModifiers(restOfLine);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return inst;
	}
	
	public static<T> String toString(T inst) {
		StringBuilder builder = new StringBuilder();
		try {
			for (Field f: inst.getClass().getDeclaredFields()) {
				if (!AnnotationChecker.isLegacy4jAnnotated(f))
					continue;
				//System.out.println("// Reading " + f);
				builder.append(EngineMappers.getMapper(f.getType()).toString(inst, f));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}
