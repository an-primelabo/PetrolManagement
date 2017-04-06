package ah.petrolmanagement.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.request.UserRequestDto;
import ah.petrolmanagement.dto.response.UserResponseDto;
import ah.petrolmanagement.utils.LogUtil;

@Controller
public class UserController extends CommonController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = { ApiConstants.SLASH, UrlConstants.URL_LOGIN }, method = { RequestMethod.GET }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String login(final Model map) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "login");

		if (StringUtils.isBlank(checkUserLoggedIn())) {
			logger.info(UrlConstants.URL_REDIRECT_PRODUCT);
			return UrlConstants.URL_REDIRECT_PRODUCT;
		}

		LogUtil.endMethod(this.getClass().getSimpleName(), "login");
		return ApiConstants.PAGE_LOGIN;
	}

	@RequestMapping(value = { UrlConstants.URL_LOGIN }, method = { RequestMethod.POST }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String login(final UserRequestDto model, final Model map)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "login", model);

		UserResponseDto user = userService.login(model);

		if (user != null && user.getStatus() == ApiConstants.STATUS_CODE_SUCCESS) {
			storeUserLoggedIn(user);

			logger.info(UrlConstants.URL_REDIRECT_PRODUCT, user);
			return UrlConstants.URL_REDIRECT_PRODUCT;
		}

		LogUtil.endMethod(this.getClass().getSimpleName(), "login", user);
		return ApiConstants.PAGE_LOGIN;
	}

	@RequestMapping(value = { UrlConstants.URL_LOGOUT }, method = { RequestMethod.GET }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String logout(final Model map) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "logout");

		leaveUserLoggedIn();

		LogUtil.endMethod(this.getClass().getSimpleName(), "logout");
		return ApiConstants.PAGE_LOGIN;
	}

	@RequestMapping(value = { UrlConstants.URL_403 }, method = {
			RequestMethod.GET, RequestMethod.POST }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String accessDenied() throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "accessDenied");
		LogUtil.endMethod(this.getClass().getSimpleName(), "accessDenied");
		return ApiConstants.PAGE_403;
	}

	@RequestMapping(value = { UrlConstants.URL_404 }, method = {
			RequestMethod.GET, RequestMethod.POST }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String pageNotFound() throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "pageNotFound");
		LogUtil.endMethod(this.getClass().getSimpleName(), "pageNotFound");
		return ApiConstants.PAGE_404;
	}

	@RequestMapping(value = { UrlConstants.URL_500 }, method = {
			RequestMethod.GET, RequestMethod.POST }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String serverError() throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "serverError");
		LogUtil.endMethod(this.getClass().getSimpleName(), "serverError");
		return ApiConstants.PAGE_500;
	}
}