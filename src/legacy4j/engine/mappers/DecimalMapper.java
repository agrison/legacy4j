package legacy4j.engine.mappers;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import legacy4j.annotation.type.DecimalField;

public class DecimalMapper implements FieldMapper {
	private boolean hasSeparator(DecimalField field) {
		return field == null ? false : !"".equals(field.separator());
	}
	
	private int fieldSize(DecimalField field) {
		if (field != null) {
			int[] pos = field.value();
			return (hasSeparator(field) ? 1 : 0) + pos[0] + pos[1];
		} else 
			return 0;
	}
	
	@Override
	public int fill(Object inst, Field field, String value) {
		try {
			DecimalField def = field.getAnnotation(DecimalField.class);
			if (def != null) {
				Object decimalObject = null;
				int[] pos = def.value();
				boolean hasSeparator = hasSeparator(def);
				int size = fieldSize(def);
				
				String numVal = value.substring(0, size);
				
				String intVal = numVal.substring(0, pos[0]);
				String decimalVal = numVal.substring(pos[0] + (hasSeparator ? 1 : 0));
				String num = intVal + "." + decimalVal;
				
				if (field.getType() == BigDecimal.class) {
					decimalObject = new BigDecimal(num);
				} else if (field.getType() == double.class || field.getType() == Double.class) {
					decimalObject = new Double(num).doubleValue();
				} else if (field.getType() == float.class || field.getType() == Float.class) {
					decimalObject = new Float(num).floatValue();
				}
				field.set(inst, decimalObject);
				return (hasSeparator ? 1 : 0) + pos[0] + pos[1];
			}
			return 0;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString(Object inst, Field field) {
		try {
			DecimalField def = field.getAnnotation(DecimalField.class);
			if (def != null) {
				String repr = "";
				if (field.getType() == BigDecimal.class) {
					repr = ((BigDecimal)field.get(inst)).toPlainString();
				} else if (field.getType() == double.class || field.getType() == Double.class) {
					repr = String.valueOf((Double)field.get(inst));
				} else if (field.getType() == float.class || field.getType() == Float.class) {
					repr = String.valueOf((Float)field.get(inst));
				}
				repr = repr.replace(".", def.separator());
				
				return String.format("%" + fieldSize(def) + "s", repr).replaceAll("[ ]", "0");
			}
			return "";
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
