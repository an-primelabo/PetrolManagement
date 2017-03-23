package ah.petrolmanagement.constants;

import ah.petrolmanagement.annotation.ConstantsRegister;

@ConstantsRegister
public final class ApiConstants {
	private ApiConstants() {
	}

	public static final int STATUS_CODE_SUCCESS = 0;
	public static final int STATUS_CODE_ERROR = -1;

	public static final String ERR_ITEM_DUPLICATE = "ERR_ITEM_DUPLICATE";
	public static final String ERR_SYSTEM = "ERR_SYSTEM";

	public static final String BLANK = "";
	public static final int ZERO = 0;
	public static final String[] EMPTY_ARRAY = new String[0];

	public static final String SLASH = "/";

	public static final String VIEW_DAILY = "daily";
	public static final String VIEW_DAILY_INSERT_PRICE = "daily/insert/price";
	public static final String VIEW_DAILY_SEARCH = "daily/search";
}