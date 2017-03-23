package ah.petrolmanagement.utils;

import ah.petrolmanagement.constants.ApiConstants;

public class URLUtil {
	public static String removeFirstSeparator(String url) {
		if (url.indexOf(ApiConstants.SLASH) == 0) {
			return url.substring(1, url.length());
		} else {
			return url;
		}
	}
}