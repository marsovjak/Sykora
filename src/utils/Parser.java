package utils;

/** Util class for parsing strings. */
public class Parser {

	
	/** Tries to parse the string and returns it as an integer or null in case of failed parse. */
	public static Integer tryParse(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
