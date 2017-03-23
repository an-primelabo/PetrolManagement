package ah.petrolmanagement.utils;

import java.math.BigDecimal;

public class Utils {
	public static float roundQuantity(float number) {
		BigDecimal bd = new BigDecimal(Float.toString(number));
		bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}