package me.grison.legacy4j.annotation.feature;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates this field should be quoted. 
 * 
 * @author Alexandre Grison <a.grison@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface QuoteField {
	public enum Type { 
		Brackets("[", "]"), 
		Parenthesis("(", ")"), 
		Braces("{", "}"), 
		Quote("\"", "\""), 
		SimpleQuote("'", "'");
		
		public String before, after;
		Type(String before, String after) { this.before = before; this.after = after; }
	}
	public Type value() default Type.Quote;
}
