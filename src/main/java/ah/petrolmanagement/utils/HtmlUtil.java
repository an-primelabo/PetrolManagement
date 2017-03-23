package ah.petrolmanagement.utils;

import java.util.Arrays;

import ah.petrolmanagement.constants.ApiConstants;

public final class HtmlUtil {
	private static final int HIGHEST_SPECIAL = '>';
	private static char[][] SPECIAL_CHARACTERS = new char[HIGHEST_SPECIAL + 1][];

	static {
		SPECIAL_CHARACTERS['&'] = "&amp;".toCharArray();
		SPECIAL_CHARACTERS['<'] = "&lt;".toCharArray();
		SPECIAL_CHARACTERS['>'] = "&gt;".toCharArray();
		SPECIAL_CHARACTERS['"'] = "&#034;".toCharArray();
		SPECIAL_CHARACTERS['\''] = "&#039;".toCharArray();
	}

	private HtmlUtil() {
	}

	public static String htmlEscape(final Object input) {
		String str = ApiConstants.BLANK;

		if (input == null) {
			return str;
		}
		if (input.getClass().isArray()) {
			Class<?> clazz = input.getClass().getComponentType();

			if (clazz == String.class) {
				str = Arrays.toString((Object[]) input);
			} else if (clazz == boolean.class) {
				str = Arrays.toString((boolean[]) input);
			} else if (clazz == int.class) {
				str = Arrays.toString((int[]) input);
			} else if (clazz == long.class) {
				str = Arrays.toString((long[]) input);
			} else if (clazz == byte.class) {
				str = Arrays.toString((byte[]) input);
			} else if (clazz == short.class) {
				str = Arrays.toString((short[]) input);
			} else if (clazz == float.class) {
				str = Arrays.toString((float[]) input);
			} else if (clazz == double.class) {
				str = Arrays.toString((double[]) input);
			} else if (clazz == char.class) {
				str = Arrays.toString((char[]) input);
			} else {
				str = Arrays.toString((Object[]) input);
			}
		} else {
			str = input.toString();
		}
		return htmlEscape(str);
	}

	public static String htmlEscape(final String buffer) {
		if ((buffer == null) || (buffer.length() == 0)) {
			return buffer;
		}
		int start = 0;
		int length = buffer.length();
		char[] arrayBuffer = buffer.toCharArray();
		StringBuilder escapedBuffer = null;

		for (int i = 0; i < length; i++) {
			char c = arrayBuffer[i];

			if (c <= HIGHEST_SPECIAL) {
				char[] escaped = SPECIAL_CHARACTERS[c];

				if (escaped != null) {
					if (start == 0) {
						escapedBuffer = new StringBuilder(length + 5);
					}
					if (start < i) {
						escapedBuffer.append(arrayBuffer, start, i - start);
					}
					start = i + 1;
					escapedBuffer.append(escaped);
				}
			}
		}
		if (start == 0) {
			return buffer;
		}
		if (start < length) {
			escapedBuffer.append(arrayBuffer, start, length - start);
		}
		return escapedBuffer.toString();
	}
}