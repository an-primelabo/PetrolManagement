package ah.petrolmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.utils.LogUtil;

@Controller
public class UserController extends CommonController {
	@Autowired
	private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	private AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = { ApiConstants.SLASH, UrlConstants.URL_LOGIN },
					method = { RequestMethod.GET },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String login() throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.PAGE_LOGIN);

		if (isCurrentAuthenticationAnonymous()) {
			return ApiConstants.PAGE_LOGIN;
		}

		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.PAGE_LOGIN);
		return UrlConstants.URL_REDIRECT_DAILY;
	}

	@RequestMapping(value = { UrlConstants.URL_LOGOUT },
					method = { RequestMethod.GET },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String logout(HttpServletRequest request,
						HttpServletResponse response) throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), "logout");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}

		LogUtil.endMethod(this.getClass().getSimpleName(), "logout");
		return UrlConstants.URL_LOGIN;
	}

	@RequestMapping(value = { UrlConstants.URL_403 },
					method = { RequestMethod.GET, RequestMethod.POST },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String accessDenied() throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.PAGE_403);
		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.PAGE_403);
		return ApiConstants.PAGE_403;
	}

	@RequestMapping(value = { UrlConstants.URL_404 },
					method = { RequestMethod.GET, RequestMethod.POST },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String pageNotFound() throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.PAGE_404);
		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.PAGE_404);
		return ApiConstants.PAGE_404;
	}

	@RequestMapping(value = { UrlConstants.URL_500 },
					method = { RequestMethod.GET, RequestMethod.POST },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String serverError() throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.PAGE_500);
		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.PAGE_500);
		return ApiConstants.PAGE_500;
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}
}