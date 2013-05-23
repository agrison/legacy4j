package me.grison.legacy4j.engine.mappers;

import java.lang.reflect.Field;

/**
 * Interface defining a Field Mapper.
 *
 * @author Alexandre Grison <a.grison@gmail.com>
 */
public interface FieldMapper {
	public int fill(Object inst, Field field, String value);
	public String toString(Object inst, Field field);
}
