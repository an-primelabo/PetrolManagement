package ah.petrolmanagement.logic;

import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.SpringSecurityMessageSource;

import ah.petrolmanagement.exception.PetrolException;

public class CommonLogic implements MessageSourceAware {
	@Autowired(required = false)
	private HttpServletRequest request;

	@Autowired(required = false)
	private HttpServletResponse response;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Override
	public void setMessageSource(final MessageSource messageSource) {
		messages = new MessageSourceAccessor(messageSource);
	}

	protected String getMessage(final String code, final Locale locale, final String... params) {
		String message = this.messages.getMessage(code, locale);
		MessageFormat format = new MessageFormat(message);
		return format.format(params);
	}

	protected PetrolException getException(final String code, final Locale locale, final String... params) {
		return getException(code, locale, null, params);
	}

	protected PetrolException getException(final String code, final Locale locale, final HttpStatus status, final String... params) {
		PetrolException e = new PetrolException(code, this.getMessage(code, locale, params));

		if (status != null) {
			e.setHttpStatus(status);
		} else {
			e.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return e;
	}

	public String getResultMessage(final String code, final String... args) throws PetrolException {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		Locale locale = Locale.ENGLISH;
		String message = messages.getMessage(code, locale);

		if (StringUtils.isBlank(message)) {
			return null;
		}
		return String.format(message, args);
	}
}