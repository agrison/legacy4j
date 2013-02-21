package legacy4j.engine.mappers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("serial")
public class EngineMappers {
	static Map<Class<?>, FieldMapper> annotations = new HashMap<Class<?>, FieldMapper>() {{
		put(String.class, new PrimitiveTypesMapper());
		put(int.class, get(String.class));
		put(Integer.class, get(int.class));
		put(Date.class, new DateMapper());
		put(Calendar.class, get(Date.class));
		put(double.class, new DecimalMapper());
		put(Double.class, get(double.class));
		put(float.class, get(double.class));
		put(Float.class, get(double.class));
		put(BigDecimal.class, get(double.class));
	}};

	public static FieldMapper getMapper(Class<?> type) {
		return annotations.get(type);
	}
	
	public static void registerMapper(Class<?> type, FieldMapper filler) {
		annotations.put(type, filler);
	}
}
