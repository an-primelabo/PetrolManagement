package ah.petrolmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.response.CommonResponseDto;
import ah.petrolmanagement.dto.response.UserResponseDto;
import ah.petrolmanagement.enums.Roles;
import ah.petrolmanagement.service.ICategoryService;
import ah.petrolmanagement.service.IDailyMeterService;
import ah.petrolmanagement.service.IProductPriceService;
import ah.petrolmanagement.service.IProductService;
import ah.petrolmanagement.service.IRoleService;
import ah.petrolmanagement.service.ITankService;
import ah.petrolmanagement.service.IUserService;

public class CommonController {
	public static final String MODEL = "model";

	@Autowired(required = false)
	private HttpServletRequest request;

	@Autowired(required = false)
	private HttpServletResponse response;

	@Autowired
	protected ICategoryService categoryService;

	@Autowired
	protected IDailyMeterService dailyService;

	@Autowired
	protected IProductPriceService priceService;

	@Autowired
	protected IProductService productService;

	@Autowired
	protected IRoleService roleService;

	@Autowired
	protected ITankService tankService;

	@Autowired
	protected IUserService userService;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CommonResponseDto handleException(
			final RuntimeException e, final HttpServletRequest request,
			final HttpServletResponse response) {
		logger.error(ApiConstants.BLANK, e);

		return this.handleException(e, request, response);
	}

	protected void storeUserLoggedIn(UserResponseDto user) {
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(ApiConstants.SESSION_INTERVAL * 60);
		session.setAttribute(ApiConstants.SESSION_USER_LOGGED_IN, user);
		session.setAttribute(ApiConstants.SESSION_USERNAME_LOGGED_IN,
				user.getUsername());
	}

	protected void leaveUserLoggedIn() {
		request.getSession().removeAttribute(
				ApiConstants.SESSION_USER_LOGGED_IN);
		request.getSession().removeAttribute(
				ApiConstants.SESSION_USERNAME_LOGGED_IN);
	}

	protected UserResponseDto getUserLoggedIn() {
		return (UserResponseDto) request.getSession().getAttribute(
				ApiConstants.SESSION_USER_LOGGED_IN);
	}

	protected String getUsernameLoggedIn() {
		return getUserLoggedIn().getUsername();
	}

	protected String isAdmin() {
		String checkUser = checkUserLoggedIn();

		if (StringUtils.isNotBlank(checkUser)) {
			return checkUser;
		}
		if (!StringUtils.equals(getUserLoggedIn().getRole(),
				Roles.ADMIN.getRole())) {
			return UrlConstants.URL_REDIRECT_403;
		}
		return ApiConstants.BLANK;
	}

	protected String checkUserLoggedIn() {
		if (getUserLoggedIn() == null) {
			return UrlConstants.URL_REDIRECT_LOGIN;
		}
		return ApiConstants.BLANK;
	}
}