package me.grison.legacy4j.engine.mappers;

import java.lang.reflect.Field;

import me.grison.legacy4j.annotation.FixedLengthField;
import me.grison.legacy4j.annotation.feature.QuoteField;
import me.grison.legacy4j.annotation.feature.TrimField;
import me.grison.legacy4j.util.Trim;

/**
 * Standard mapper for primitive types.
 *
 * @author Alexandre Grison <a.grison@gmail.com>
 */
public class PrimitiveTypesMapper implements FieldMapper {
	@Override
	public int fill(Object inst, Field field, String value) {
		try {
			FixedLengthField flf = field.getAnnotation(FixedLengthField.class);
			if (flf == null)
				return 0;
			String fieldValue = value.substring(0, flf.value());
			// for String just set the value (later we'll check for Trim and align modifiers)
			if (field.getType() == String.class) {
				// should the String be trimed ?
				TrimField tf = field.getAnnotation(TrimField.class);
				String newValue = tf == null ? fieldValue : Trim.trim(tf.value(), fieldValue);
				
				// should the String be quoted ?
				QuoteField qf = field.getAnnotation(QuoteField.class);
				newValue = qf == null ? newValue : 
					String.format("%s%s%s", qf.value().before, newValue, qf.value().after);
				field.set(inst, newValue);
			} else if (field.getType() == int.class || field.getType() == Integer.class) {
				field.set(inst, Integer.valueOf(fieldValue).intValue());
			}
			return flf.value();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString(Object inst, Field field) {
		try {
			FixedLengthField flf = field.getAnnotation(FixedLengthField.class);
			if (field.getType() == String.class) {
				String repr = (String)field.get(inst);
				// unquote
				QuoteField qf = field.getAnnotation(QuoteField.class);
				repr = qf == null ? repr : repr.replaceAll("[" + qf.value().before + qf.value().after + "]", "");
				// add blank if necessary
				repr = String.format("%-" + flf.value() + "s", repr);
				return repr;
			} else if (field.getType() == int.class || field.getType() == Integer.class) {
				return String.format("%0" + flf.value() + "d", (Integer)field.get(inst));
			} else
				return (String)field.get(inst);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
