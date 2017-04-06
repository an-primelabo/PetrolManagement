package ah.petrolmanagement.utils;

import java.math.BigDecimal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ah.petrolmanagement.constants.ApiConstants;

public class Utils {
	public static Float roundQuantity(Float number) {
		BigDecimal bd = new BigDecimal(Float.toString(number));
		bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	public static String hashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	public static boolean checkPassword(String password, String hashedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password, hashedPassword);
	}

	public static String getString(Object object) {
		if (object != null) {
			return object.toString();
		}
		return ApiConstants.BLANK;
	}

	public static Integer getInteger(Object object) {
		if (object != null) {
			try {
				return Integer.parseInt(object.toString());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return ApiConstants.ZERO;
			}
		}
		return ApiConstants.ZERO;
	}

	public static Float getFloat(Object object) {
		if (object != null) {
			try {
				return Float.parseFloat(object.toString());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return (float) ApiConstants.ZERO;
			}
		}
		return (float) ApiConstants.ZERO;
	}
}