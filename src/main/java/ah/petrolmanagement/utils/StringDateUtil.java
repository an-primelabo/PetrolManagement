package ah.petrolmanagement.utils;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import ah.petrolmanagement.utils.fomatter.FormatUtil;

public final class StringDateUtil {
	private StringDateUtil() {
	}

	public static String getFromDate(final String year, final String month) {
		if (StringUtils.isEmpty(fixYear(year)) || StringUtils.isEmpty(fixMonth(month))) {
			return null;
		}
		return FormatUtil.formatYear(Integer.valueOf(year)) + FormatUtil.formatMonth(Integer.valueOf(month)) + "01";
	}

	public static String getToDate(final String year, final String month) {
		if (StringUtils.isEmpty(fixYear(year)) || StringUtils.isEmpty(fixMonth(month))) {
			return null;
		}
		Date date = FormatUtil.textAsDate(FormatUtil.formatYear(Integer.valueOf(year)) + FormatUtil.formatMonth(Integer.valueOf(month)) + "01");

		if (date == null) {
			return null;
		}
		return FormatUtil.dateAsText(DateUtil.getNextMonth(date));
	}

	public static String getToDate(final String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		Date d = FormatUtil.textAsDate(date);

		if (d == null) {
			return null;
		}
		return FormatUtil.dateAsText(DateUtil.getNextDay(d));
	}

	public static String getYearMonth(final String year, final String month) {
		if (StringUtils.isEmpty(fixYear(year)) || StringUtils.isEmpty(fixMonth(month))) {
			return null;
		}
		return FormatUtil.formatYear(Integer.valueOf(year)) + FormatUtil.formatMonth(Integer.valueOf(month));
	}

	public static String getYearMonthOfPreviousMonth(final String year, final String month) {
		if (StringUtils.isEmpty(fixYear(year)) || StringUtils.isEmpty(fixMonth(month))) {
			return null;
		}
		Date date = FormatUtil.textAsDate(FormatUtil.formatYear(Integer.valueOf(year)) + FormatUtil.formatMonth(Integer.valueOf(month)) + "01");

		if (date == null) {
			return null;
		}
		Date previousMonth = DateUtil.getPreviousMonth(date);
		return FormatUtil.formatYear(DateUtil.getYearPart(previousMonth)) + FormatUtil.formatMonth(DateUtil.getMonthPart(previousMonth));
	}

	public static String getYearMonthOfPreviousYear(final String year, final String month) {
		if (StringUtils.isEmpty(fixYear(year)) || StringUtils.isEmpty(fixMonth(month))) {
			return null;
		}
		Date date = FormatUtil.textAsDate(FormatUtil.formatYear(Integer.valueOf(year)) + FormatUtil.formatMonth(Integer.valueOf(month)) + "01");

		if (date == null) {
			return null;
		}
		Date previousYear = DateUtil.getPreviousYear(date);
		return FormatUtil.formatYear(DateUtil.getYearPart(previousYear)) + FormatUtil.formatMonth(DateUtil.getMonthPart(previousYear));
	}

	private static String fixYear(final String year) {
		if (year == null) {
			return null;
		}
		if (year.matches("[0-9]{4}")) {
			return year;
		}
		return null;
	}

	private static String fixMonth(final String month) {
		if (month == null) {
			return null;
		}
		if (month.matches("[0-9]{1,2}")) {
			return month;
		}
		return null;
	}
}