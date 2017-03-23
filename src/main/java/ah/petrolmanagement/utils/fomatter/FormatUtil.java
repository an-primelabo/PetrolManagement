package ah.petrolmanagement.utils.fomatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public final class FormatUtil {
	private FormatUtil() {
	}

	private static final String DATE_DELIMITER = "/";

	private static final String TIME_DELIMITER = ":";

	private static final String MONTH190001 = "190001";

	private static final String DATE19000101 = "19000101";

	private static final String DATE19000101000000 = "19000101000000";

	private static final String TEXTDATE_FORMAT = "yyyyMMdd";

	private static final String TEXTDATETIME_FORMAT = "yyyyMMddHHmmss";

	private static final String TEXTYEARMONTH_FORMAT = "yyyyMM";

	private static final String DATE_FORMAT = "yyyy/MM/dd";

	private static final String TIME_FORMAT = "HH:mm:ss";

	private static final String TIME_HHMM_FORMAT = "HH:mm";

	private static final String DATE_MINUTE_FORMAT = "yyyy/MM/dd HH:mm";

	private static final String MONTH_DAY_FORMAT = "M/d";

	private static final String MONTH_TIME_FORMAT = "MM/dd HH:mm";

	private static final String MONTH_WEEK_FORMAT = "M/d(E)";

	private static final String DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss.SSSSSS";

	public static String formatYear(final int year) {
		return String.format("%04d", year);
	}

	public static String formatMonth(final int month) {
		return String.format("%02d", month);
	}

	public static String formatMinutes(final int minutes) {
		return String.format("%02d", minutes);
	}

	public static String printDate(final Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);
	}

	public static String printTime(final Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		return sdf.format(date);
	}

	public static String printTimeHHmm(final Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_HHMM_FORMAT);
		return sdf.format(date);
	}

	public static String printDateMinute(final Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_MINUTE_FORMAT);
		return sdf.format(date);
	}

	public static String printMonthDay(final Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(MONTH_DAY_FORMAT);
		return sdf.format(date);
	}

	public static String printMonthTime(final Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(MONTH_TIME_FORMAT);
		return sdf.format(date);
	}

	public static String printMonthWeek(final Date date, final Locale locale) {
		SimpleDateFormat sdf = new SimpleDateFormat(MONTH_WEEK_FORMAT, locale);
		return sdf.format(date);
	}

	public static String printTextDateTime(final String object) {
		if (object == null) {
			return object;
		}
		if (object.length() == 8) {
			if (object.equals(DATE19000101)) {
				return null;
			}
			StringBuilder sb = new StringBuilder(10);
			sb.append(object.substring(0, 4));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(4, 6));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(6, 8));
			return sb.toString();
		} else if (object.length() == 14) {
			if (object.equals(DATE19000101000000)) {
				return null;
			}
			StringBuilder sb = new StringBuilder(19);
			sb.append(object.substring(0, 4));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(4, 6));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(6, 8));
			sb.append(" ");
			sb.append(object.substring(8, 10));
			sb.append(TIME_DELIMITER);
			sb.append(object.substring(10, 12));
			sb.append(TIME_DELIMITER);
			sb.append(object.substring(12, 14));
			return sb.toString();
		} else if (object.length() == 6) {
			if (object.equals(MONTH190001)) {
				return null;
			}
			StringBuilder sb = new StringBuilder(8);
			sb.append(object.substring(0, 2));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(2, 4));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(4, 6));
			return sb.toString();
		}
		return object;
	}

	public static String printTextDate(final String object) {
		if (object == null) {
			return object;
		}
		if (object.length() == 8) {
			if (object.equals(DATE19000101)) {
				return null;
			}
			StringBuilder sb = new StringBuilder(10);
			sb.append(object.substring(0, 4));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(4, 6));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(6, 8));
			return sb.toString();
		} else if (object.length() == 14) {
			if (object.equals(DATE19000101000000)) {
				return null;
			}
			StringBuilder sb = new StringBuilder(10);
			sb.append(object.substring(0, 4));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(4, 6));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(6, 8));
			return sb.toString();
		}
		return object;
	}

	public static String printTextTime(final String object) {
		if (object == null) {
			return object;
		}
		if (object.length() == 6) {
			StringBuilder sb = new StringBuilder(8);
			sb.append(object.substring(0, 2));
			sb.append(TIME_DELIMITER);
			sb.append(object.substring(2, 4));
			sb.append(TIME_DELIMITER);
			sb.append(object.substring(4, 6));
			return sb.toString();
		} else if (object.length() == 14) {
			if (object.equals(DATE19000101000000)) {
				return null;
			}
			StringBuilder sb = new StringBuilder(8);
			sb.append(object.substring(8, 10));
			sb.append(TIME_DELIMITER);
			sb.append(object.substring(10, 12));
			sb.append(TIME_DELIMITER);
			sb.append(object.substring(12, 14));
			return sb.toString();
		}
		return object;
	}

	public static String printTextYearMonth(final String object) {
		if (object == null) {
			return object;
		}
		if (object.equals(MONTH190001) || object.equals(DATE19000101)) {
			return null;
		}
		if ((object.length() == 6) || (object.length() == 8)) {
			StringBuilder sb = new StringBuilder(7);
			sb.append(object.substring(0, 4));
			sb.append(DATE_DELIMITER);
			sb.append(object.substring(4, 6));
			return sb.toString();
		}
		return object;
	}

	public static String parseTextDateTime(final String text) {
		if (text == null) {
			return text;
		}
		StringBuilder sb = new StringBuilder(text.length());

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if ((c >= '0') && (c <= '9')) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String printTextTimeHHmm(final String object) {
		if (object == null) {
			return object;
		}
		if (object.length() == 6) {
			StringBuilder sb = new StringBuilder(5);
			sb.append(object.substring(0, 2));
			sb.append(TIME_DELIMITER);
			sb.append(object.substring(2, 4));
			return sb.toString();
		}
		return null;
	}

	public static String printIPAddress(final String object) {
		if ((object == null) || (object.length() == 0)) {
			return object;
		}
		StringBuilder sb = new StringBuilder(object.length());

		for (String octet : StringUtils.split(object, ".")) {
			int index = 0;

			while (index < octet.length() - 1) {
				if (octet.charAt(index) != '0') {
					break;
				}
				index++;
			}
			sb.append(octet.substring(index));
			sb.append('.');
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	public static String parseIPAddress(final String text) {
		if ((text == null) || (text.length() == 0)) {
			return text;
		}
		StringBuilder sb = new StringBuilder(text.length());

		for (String octet : StringUtils.split(text, ".")) {
			for (int i = 0; i < 3 - octet.length(); i++) {
				sb.append('0');
			}
			sb.append(octet);
			sb.append('.');
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	public static String formatIPAddress(final String ip1, final String ip2, final String ip3, final String ip4) {
		return String.format("%03d.%03d.%03d.%03d", Integer.parseInt(ip1),
				Integer.parseInt(ip2), Integer.parseInt(ip3), Integer.parseInt(ip4));
	}

	public static boolean isValidIPAddress(final String text) {
		if (text == null) {
			return false;
		}
		String[] octet = StringUtils.split(text, ".");

		if (octet.length != 4) {
			return false;
		}
		String ip = FormatUtil.formatIPAddress(octet[0], octet[1], octet[2], octet[3]);
		return !("000.000.000.000".equals(ip) || "255.255.255.255".equals(ip));
	}

	public static String dateAsText(final Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TEXTDATE_FORMAT);
		return sdf.format(date);
	}

	public static String dateTimeAsText(final Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TEXTDATETIME_FORMAT);
		return sdf.format(date);
	}

	public static String dateYearMonthAsText(final Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TEXTYEARMONTH_FORMAT);
		return sdf.format(date);
	}

	public static Date textAsDate(final String date) {
		SimpleDateFormat sdf;

		if (date.length() == 8) {
			sdf = new SimpleDateFormat(TEXTDATE_FORMAT);
		} else if (date.length() == 10) {
			sdf = new SimpleDateFormat(DATE_FORMAT);
		} else {
			sdf = new SimpleDateFormat(TEXTDATETIME_FORMAT);
		}
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date textAsDate2(final String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);

		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String padLeft(final String text, final int length, final String character) {
		if (StringUtils.isEmpty(text)) {
			return text;
		}
		return StringUtils.repeat(character, length - text.length()) + text;
	}

	public static String padRight(final String text, final int length, final String character) {
		if (StringUtils.isEmpty(text)) {
			return text;
		}
		return text + StringUtils.repeat(character, length - text.length());
	}
}