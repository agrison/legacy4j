package me.grison.legacy4j.engine.mappers;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.grison.legacy4j.annotation.type.DateField;

/**
 * Date Mapper for field having type Date or Calendar.
 *
 * @author Alexandre Grison <a.grison@gmail.com>
 */
public class DateMapper implements FieldMapper {
	@Override
	public int fill(Object inst, Field field, String value) {
		try {
			DateField daf = field.getAnnotation(DateField.class);
			if (daf != null) {
				String dateValue = value.substring(0, daf.value().length());
				Date date = new SimpleDateFormat(daf.value()).parse(dateValue);
				Object dateObject = date;
				if (field.getType() == Calendar.class) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					dateObject = cal;
				}
				field.set(inst, dateObject);
				return daf.value().length();
			}
			return 0;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString(Object inst, Field field) {
		try {
			DateField daf = field.getAnnotation(DateField.class);
			if (daf != null) {
				Date date = null;
				if (field.getType() == Calendar.class) {
					Calendar cal = (Calendar)field.get(inst);
					date = cal.getTime();
				} else if (field.getType() == Date.class) {
					date = (Date)field.get(inst);
				}
				return new SimpleDateFormat(daf.value()).format(date);
			} else {
				return "";
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
