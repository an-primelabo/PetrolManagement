package ah.petrolmanagement.utils;

import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

import ah.petrolmanagement.constants.ApiConstants;

public final class StringUtil {
	private StringUtil() {
	}

	private static final long CONVERT = 1024;

	public static String getString(final Integer intValue, final String format) {
		DecimalFormat decimalFormat = new DecimalFormat(format);
		return decimalFormat.format(intValue);
	}

	public static boolean checkFormat(final String str, final String format) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		return str.matches(format);
	}

	public static String formatTimeZoneToString(final String str) {
		String result = ApiConstants.BLANK;

		if (StringUtils.isEmpty(str)) {
			return ApiConstants.BLANK;
		}
		if (str.length() < 2) {
			result += "0" + str;
		} else {
			result += str;
		}
		return result;
	}

	public static String formatBytes(final long bytes) {
		String result = ApiConstants.BLANK;
		DecimalFormat df01 = new DecimalFormat("#.#");
		DecimalFormat df02 = new DecimalFormat("#.##");

		if (bytes <= CONVERT) {
			return String.valueOf(bytes) + " B";
		} else if (bytes < (CONVERT * CONVERT)) {
			double val = Double.parseDouble(String.valueOf(bytes));
			val = val / CONVERT;
			return String.valueOf(df01.format(val)) + " KB";
		} else if (bytes >= (CONVERT * CONVERT)) {
			double val = Double.parseDouble(String.valueOf(bytes));
			val = val / (CONVERT * CONVERT);
			return String.valueOf(df02.format(val)) + " MB";
		}
		return result;
	}
}