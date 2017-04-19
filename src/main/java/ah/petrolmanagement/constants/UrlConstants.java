package ah.petrolmanagement.constants;

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

	public static final String REQUEST_HEADER_ACCEPT = "Accept=text/plain";
	public static final String REQUEST_HEADER_ACCEPT_JSON = "Accept=application/json";

	public static final String URL_DAILY = "/daily";
	public static final String URL_DAILY_INDEX = "/daily/index";
	public static final String URL_DAILY_SELECT = "/daily/select";
	public static final String URL_DAILY_ACTION = "/daily/action";

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
	public static final String URL_REDIRECT_PRODUCT = "redirect:" + URL_PRODUCT;
	public static final String URL_REDIRECT_LOGIN = "redirect:" + URL_LOGIN;
	public static final String URL_REDIRECT_403 = "redirect:" + URL_403;
}