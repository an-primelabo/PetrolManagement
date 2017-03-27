package ah.petrolmanagement.constants;

import ah.petrolmanagement.annotation.ConstantsRegister;

@ConstantsRegister
public final class UrlConstants {
	private UrlConstants() {
	}

	public static final String SIGN_AMP = "&";
	public static final String SIGN_QST = "?";
	public static final String SIGN_EQL = "=";
	public static final String BASE_CHARSET = "UTF-8";
	public static final String SEPARATOR_COMMA = ",";
	public static final String REQUEST_HEADER_COOKIE = "Cookie";
	public static final String REQUEST_COOKIE_JSESSIONID = "JSESSIONID";
	public static final String CONTENT_TYPE_KEY = "Content-Type";
	public static final String ACCEPT_LANGUAGE_KEY = "Accept-Language";
	public static final String HEADER_CONTENT_TYPE_VALUE_JSON = "application/json; charset=UTF-8";
	public static final int CONNECTION_TIMEOUT = 200;
	public static final int SOCKET_TIMEOUT = 20 * 1000;

	public static final String REQUEST_HEADER_ACCEPT = "Accept=text/plain";
	public static final String REQUEST_HEADER_ACCEPT_JSON = "Accept=application/json";

	public static final String URL_DAILY = "/daily";
	public static final String URL_DAILY_INDEX = "/daily/index";
	public static final String URL_DAILY_SEARCH = "/daily/search";
	public static final String URL_DAILY_INSERT_PRICE = "/daily/insert/price";
	public static final String URL_DAILY_INSERT_DAILY = "/daily/insert/daily";

	public static final String URL_CATEGORY_SELECT = "category/select/json";
	public static final String URL_CATEGORY_INSERT = "category/save/json";
	public static final String URL_CATEGORY_UPDATE = "category/update/json";
	public static final String URL_CATEGORY_DELETE = "category/delete/json";

	public static final String URL_DAILY_SELECT = "daily/select/json";
	public static final String URL_DAILY_INSERT = "daily/save/json";
	public static final String URL_DAILY_UPDATE = "daily/update/json";
	public static final String URL_DAILY_DELETE = "daily/delete/json";

	public static final String URL_PRICE_SELECT = "price/select/json";
	public static final String URL_PRICE_SELECT_PRICE = "price/select/price/json";
	public static final String URL_PRICE_SELECT_OLD_PRICE = "price/select/old/price/json";
	public static final String URL_PRICE_SELECT_NEW_PRICE = "price/select/new/price/json";
	public static final String URL_PRICE_INSERT = "price/save/json";
	public static final String URL_PRICE_UPDATE = "price/update/json";
	public static final String URL_PRICE_DELETE = "price/delete/json";

	public static final String URL_PRODUCT_SELECT = "product/select/json";
	public static final String URL_PRODUCT_INSERT = "product/save/json";
	public static final String URL_PRODUCT_UPDATE = "product/update/json";
	public static final String URL_PRODUCT_DELETE = "product/delete/json";

	public static final String URL_SHIFT_SELECT = "shift/select/json";
	public static final String URL_SHIFT_INSERT = "shift/save/json";
	public static final String URL_SHIFT_UPDATE = "shift/update/json";
	public static final String URL_SHIFT_DELETE = "shift/delete/json";

	public static final String URL_TANK_SELECT = "tank/select/json";
	public static final String URL_TANK_INSERT = "tank/save/json";
	public static final String URL_TANK_UPDATE = "tank/update/json";
	public static final String URL_TANK_DELETE = "tank/delete/json";
}