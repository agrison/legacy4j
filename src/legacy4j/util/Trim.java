package legacy4j.util;

import legacy4j.annotation.feature.TrimField;

public class Trim {
	public static String trim(TrimField.Type type, String text) {
		if (type == TrimField.Type.Both)
			return text.trim();
		else
			return type == TrimField.Type.Left ? ltrim(text) : rtrim(text);
	}
	
	public static String ltrim(String text) {
		int pos = 0;
		while (pos < text.length() && Character.isWhitespace(text.charAt(pos))) 
			++pos;
		return text.substring(pos);
	}
	
	public static String rtrim(String text) {
		int pos = text.length() - 1;
		while (pos > 0 && Character.isWhitespace(text.charAt(pos)))
			--pos;
		return text.substring(0, pos + 1);
	}
}
