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
	public static final String URL_DAILY_CHART = "/daily/chart";
	public static final String URL_DAILY_INSERT_PRICE = "/daily/insert/price";
	public static final String URL_DAILY_INSERT_DAILY = "/daily/insert/daily";

	public static final String URL_PRODUCT = "/product";
	public static final String URL_PRODUCT_INDEX = "/product/index";
	public static final String URL_PRODUCT_CATEGORY_SELECT = "/product/category/select";
	public static final String URL_PRODUCT_CATEGORY_ACTION = "/product/category/action";
	public static final String URL_PRODUCT_SELECT = "/product/select";
	public static final String URL_PRODUCT_ACTION = "/product/action";

	public static final String URL_LOGIN = "/login";
	public static final String URL_LOGOUT = "/logout";
	public static final String URL_403 = "/403";
	public static final String URL_404 = "/404";
	public static final String URL_500 = "/500";

	public static final String URL_REDIRECT_DAILY = "redirect:" + URL_DAILY;

	public static final String URL_API_CATEGORY_SELECT = "category/select/json";
	public static final String URL_API_CATEGORY_INSERT = "category/save/json";
	public static final String URL_API_CATEGORY_UPDATE = "category/update/json";
	public static final String URL_API_CATEGORY_DELETE = "category/delete/json";

	public static final String URL_API_DAILY_SELECT = "daily/select/json";
	public static final String URL_API_DAILY_INSERT = "daily/save/json";
	public static final String URL_API_DAILY_UPDATE = "daily/update/json";
	public static final String URL_API_DAILY_DELETE = "daily/delete/json";

	public static final String URL_API_PRODUCT_SELECT = "product/select/json";
	public static final String URL_API_PRODUCT_INSERT = "product/save/json";
	public static final String URL_API_PRODUCT_UPDATE = "product/update/json";
	public static final String URL_API_PRODUCT_DELETE = "product/delete/json";

	public static final String URL_API_PRICE_SELECT = "price/select/json";
	public static final String URL_API_PRICE_SELECT_PRICE = "price/select/price/json";
	public static final String URL_API_PRICE_SELECT_OLD_PRICE = "price/select/old/price/json";
	public static final String URL_API_PRICE_SELECT_NEW_PRICE = "price/select/new/price/json";
	public static final String URL_API_PRICE_INSERT = "price/save/json";
	public static final String URL_API_PRICE_UPDATE = "price/update/json";
	public static final String URL_API_PRICE_DELETE = "price/delete/json";

	public static final String URL_API_ROLE_SELECT = "role/select/json";

	public static final String URL_API_TANK_SELECT = "tank/select/json";
	public static final String URL_API_TANK_INSERT = "tank/save/json";
	public static final String URL_API_TANK_UPDATE = "tank/update/json";
	public static final String URL_API_TANK_DELETE = "tank/delete/json";

	public static final String URL_API_USER_SELECT = "user/select/json";
	public static final String URL_API_USER_INSERT = "user/save/json";
	public static final String URL_API_USER_UPDATE = "user/update/json";
	public static final String URL_API_USER_DELETE = "user/delete/json";
}