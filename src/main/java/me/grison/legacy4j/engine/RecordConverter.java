package me.grison.legacy4j.engine;

import java.lang.reflect.Field;

import me.grison.legacy4j.engine.anno.AnnotationChecker;
import me.grison.legacy4j.engine.mappers.EngineMappers;

/**
 * Record conversion utilities.
 *
 * @author Alexandre Grison <a.grison@gmail.com>
 */
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
		} catch (Throwable e) {
            throw new RuntimeException(e);
		}
		return inst;
	}
	
	public static<T> String toString(T inst) {
		StringBuilder builder = new StringBuilder();
		try {
			for (Field f: inst.getClass().getDeclaredFields()) {
				if (!AnnotationChecker.isLegacy4jAnnotated(f))
					continue;
				builder.append(EngineMappers.getMapper(f.getType()).toString(inst, f));
			}
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
		return builder.toString();
	}
}
