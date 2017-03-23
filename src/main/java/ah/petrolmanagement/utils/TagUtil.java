package ah.petrolmanagement.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ah.petrolmanagement.constants.ApiConstants;

public final class TagUtil {
	private static final String BR = "<br/>";

	private TagUtil() {
	}

	public static String eraseTag(final String value) {
		if ((value == null) || (value.length() == 0)) {
			return ApiConstants.BLANK;
		}
		Matcher matcher = Pattern.compile("<.+>").matcher(value);
		return matcher.replaceAll(ApiConstants.BLANK);
	}

	public static String multiline(final String value) {
		if ((value == null) || (value.length() == 0)) {
			return ApiConstants.BLANK;
		}
		return HtmlUtil.htmlEscape(value).replaceAll("\r\n", BR).replaceAll("\r", BR).replaceAll("\n", BR);
	}

	public static String autolink(final String value) {
		if ((value == null) || (value.length() == 0)) {
			return ApiConstants.BLANK;
		}
		return value.replaceAll("(http://|https://){1}[\\w\\.\\-/:]+", "<a href=\"$0\">$0</a>");
	}
}