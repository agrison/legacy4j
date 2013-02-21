package legacy4j.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import legacy4j.annotation.FixedLengthField;
import legacy4j.annotation.FixedLengthRecord;
import legacy4j.annotation.feature.QuoteField;
import legacy4j.annotation.feature.TrimField;
import legacy4j.annotation.feature.QuoteField.Type;
import legacy4j.annotation.type.CustomField;
import legacy4j.annotation.type.DateField;
import legacy4j.annotation.type.DecimalField;

@FixedLengthRecord(ignoreMatching="^#.*|^-.*|^\\s+Page.*$", ignoreEmpty=true)
public class Item {
	@FixedLengthField(4)
	public int id;
	@FixedLengthField(10)
	@TrimField
	@QuoteField(Type.Braces)
	public String name;
	@DecimalField({8, 2}) // size = 8 + 2 = 10
	public BigDecimal num1;
	@DecimalField(value={7, 4}, separator=",") // size = 7 + 4 + 1 = 12
	public Double num2;
	@DateField("yyyyMMdd") // size = "yyyyMMdd".length() = 8
	public Calendar cal;
	@FixedLengthField(22)
	@CustomField(PersonMapper.class)
	public Person person;
	public String toString() {
		return String.format("Item [id=%s, name='%s', num1=%s, num2=%s, cal=%s, person=%s]", 
				id, name, num1, num2, new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()), person
		);
	}
	public String format() {
		return "";
	}
}