package legacy4j.engine.mappers;

import java.lang.reflect.Field;

public interface FieldMapper {
	public int fill(Object inst, Field field, String value);
	public String toString(Object inst, Field field);
}
