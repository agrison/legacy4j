package me.grison.legacy4j.mapper;

import me.grison.legacy4j.annotation.FixedLengthField;
import me.grison.legacy4j.engine.mappers.FieldMapper;
import me.grison.legacy4j.model.Person;

import java.lang.reflect.Field;

public class PersonMapper implements FieldMapper {
	@Override
	public int fill(Object inst, Field field, String value) {
		try {
			FixedLengthField flf = field.getAnnotation(FixedLengthField.class);
			if (flf == null)
				return 0;
			
			if (field.getType() == Person.class) {
				Person p = new Person();
				p.firstName = value.substring(0, 10).trim();
				p.surName = value.substring(10).trim();
				field.set(inst, p);
			}
			
			return flf.value();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString(Object inst, Field field) {
		try {
			Person p = (Person)field.get(inst);
			return String.format("%-10s%-10s", p.firstName, p.surName);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
