package ah.petrolmanagement.constants;

public final class ApiConstants {
	private ApiConstants() {
	}

	// Set max inactive interval in 30 minutes
	public static final int SESSION_INTERVAL = 30;

	public static final int STATUS_CODE_SUCCESS = 0;
	public static final int STATUS_CODE_ERROR = -1;

	public static final String ERR_ITEM_DUPLICATE = "ERR_ITEM_DUPLICATE";
	public static final String ERR_SYSTEM = "ERR_SYSTEM";

	public static final String SELECT = "select";
	public static final String INSERT = "insert";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	public static final String BLANK = "";
	public static final String SLASH = "/";
	public static final int ZERO = 0;
	public static final String[] EMPTY_ARRAY = new String[0];

	public static final String SESSION_USER_LOGGED_IN = "userLoggedIn";
	public static final String SESSION_USERNAME_LOGGED_IN = "usernameLoggedIn";

	public static final String REQUEST_TITLE = "title";

	public static final String VIEW_DAILY = "daily";
	public static final String VIEW_DAILY_SEARCH = "daily/search";
	public static final String VIEW_DAILY_CHART = "daily/chart";
	public static final String VIEW_DAILY_INSERT_PRICE = "daily/insert/price";
	public static final String VIEW_DAILY_INSERT_DAILY = "daily/insert/daily";

	public static final String VIEW_PRODUCT = "product";
	public static final String VIEW_PRODUCT_CATEGORY_SELECT = "product/category/select";
	public static final String VIEW_PRODUCT_CATEGORY_ACTION = "product/category/action";
	public static final String VIEW_PRODUCT_SELECT = "product/select";
	public static final String VIEW_PRODUCT_ACTION = "product/action";

	public static final String PAGE_LOGIN = "login/login";
	public static final String PAGE_403 = "login/403";
	public static final String PAGE_404 = "login/404";
	public static final String PAGE_500 = "login/500";
}