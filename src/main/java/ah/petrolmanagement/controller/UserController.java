package ah.petrolmanagement.controller;

import java.util.Map;

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

@Controller
public class UserController extends CommonController {
	private String responseJson = null;
	private Map<String, Object> mapJson = null;

	@Autowired
	private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	private AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = { ApiConstants.SLASH, UrlConstants.URL_LOGIN },
					method = { RequestMethod.GET },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String login() throws PetrolException {
		if (isCurrentAuthenticationAnonymous()) {
			return ApiConstants.PAGE_LOGIN;
		}
		return "redirect:" + UrlConstants.URL_DAILY;
	}

	@RequestMapping(value = { UrlConstants.URL_LOGOUT },
					method = { RequestMethod.GET },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String logout(HttpServletRequest request,
						HttpServletResponse response) throws PetrolException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return UrlConstants.URL_LOGIN;
	}

	@RequestMapping(value = { UrlConstants.URL_403 },
					method = { RequestMethod.GET, RequestMethod.POST },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String accessDenied() throws PetrolException {
		return ApiConstants.PAGE_403;
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}
}