package legacy4j.engine.mappers;

import java.lang.reflect.Field;

import legacy4j.annotation.FixedLengthField;
import legacy4j.annotation.feature.QuoteField;
import legacy4j.annotation.feature.TrimField;
import legacy4j.util.Trim;

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
				// should the String be unquoted ?
				QuoteField qf = field.getAnnotation(QuoteField.class);
				String newValue = qf == null ? fieldValue : fieldValue.substring(qf.value().before.length(), fieldValue.length() - qf.value().after.length());

                // should the String be trimed ?
                TrimField tf = field.getAnnotation(TrimField.class);
                newValue = tf == null ? newValue : Trim.trim(tf.value(), newValue);

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
                QuoteField qf = field.getAnnotation(QuoteField.class);
                int quoteSize = qf == null ? 0 : qf.value().before.length() + qf.value().after.length();
                // add blank if necessary
                repr = String.format("%-" + (flf.value() - quoteSize) + "s", repr);
				// unquote
				repr = qf == null ? repr : String.format("%s%s%s", qf.value().before, repr , qf.value().after);//repr.replaceAll("\\Q" + qf.value().before + qf.value().after + "\\E", "");
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
