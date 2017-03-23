package ah.petrolmanagement.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
	private static final long ONE_HOUR_MILLISECONDS = 3600000L;
	private static final long ONE_DAY_MILLISECONDS = 86400000L;

	private DateUtil() {
	}

	public static int getYearPart(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	public static int getMonthPart(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	public static int getDayOfWeek(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static Date getDate(final int year, final int month, final int date) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(year, month - 1, date);
		return c.getTime();
	}

	public static Date getTime(final Date date, final int hour, final int minute, final int second, final int millisecond) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		c.set(Calendar.MILLISECOND, millisecond);
		return c.getTime();
	}

	public static Date getNow() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	public static Date getToday() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getYesterday() {
		return addDays(getToday(), -1);
	}

	public static Date getTwoDaysAgo() {
		return addDays(getToday(), -2);
	}

	public static Date getPreviousDay(final Date date) {
		return addDays(date, -1);
	}

	public static Date getNextDay(final Date date) {
		return addDays(date, 1);
	}

	public static Date getLastWeek() {
		return addDays(getToday(), -6);
	}

	public static Date getLastMonth() {
		return addMonths(getToday(), -1);
	}

	public static Date getPreviousMonth(final Date date) {
		return addMonths(date, -1);
	}

	public static Date getNextMonth(final Date date) {
		return addMonths(date, 1);
	}

	public static Date getPreviousYear(final Date date) {
		return addYears(date, -1);
	}

	public static Date addDays(final Date date, final int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, amount);
		return c.getTime();
	}

	public static Date addMonths(final Date date, final int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, amount);
		return c.getTime();
	}

	public static Date addYears(final Date date, final int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, amount);
		return c.getTime();
	}

	public static Date addMinutes(final Date date, final int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, amount);
		return c.getTime();
	}

	public static boolean isWithinHours(final Date date1, final Date date2, final int hour) {
		return Math.abs(date1.getTime() - date2.getTime()) <= ONE_HOUR_MILLISECONDS * hour;
	}

	public static boolean isWithinDays(final Date date1, final Date date2, final int days) {
		return Math.abs(date1.getTime() - date2.getTime()) <= days * ONE_DAY_MILLISECONDS;
	}

	public static long differenceDays(final Date date1, final Date date2) {
		return Math.abs(date1.getTime() - date2.getTime()) / ONE_DAY_MILLISECONDS;
	}

	public static Date getLastDayOfPreviousYear(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	public static Date getLastDayOfPreviousMonth(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	public static Date getLastDayOfPreviousYearMonth(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.YEAR, -1);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	public static Date getFirstDayOfYear(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		return c.getTime();
	}
}