package ah.petrolmanagement.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.dto.response.CommonResponseDto;
import ah.petrolmanagement.exception.PetrolException;

public class CommonController implements MessageSourceAware {
	public static final String MODEL = "model";

	@Autowired(required = false)
	private HttpServletRequest request;

	@Autowired(required = false)
	private HttpServletResponse response;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected static MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Override
	public final void setMessageSource(final MessageSource messageSource) {
		messages = new MessageSourceAccessor(messageSource);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CommonResponseDto handleHttpMessageNotReadableException(
			final HttpMessageNotReadableException e,
			final HttpServletRequest request,
			final HttpServletResponse response) {
		logger.error(ApiConstants.BLANK, e);
		return this.handleException(new PetrolException(ApiConstants.BLANK, ApiConstants.BLANK, e), request, response);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CommonResponseDto handleException(
			final RuntimeException e,
			final HttpServletRequest request,
			final HttpServletResponse response) {
		logger.error(ApiConstants.BLANK, e);
		return this.handleException(new PetrolException(ApiConstants.BLANK, ApiConstants.BLANK, e), request, response);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CommonResponseDto handleException(
			final Exception e,
			final HttpServletRequest request,
			final HttpServletResponse response) {
		logger.error(ApiConstants.BLANK, e);
		return this.handleException(new PetrolException(ApiConstants.BLANK, ApiConstants.BLANK, e), request, response);
	}

	@ExceptionHandler
	public @ResponseBody CommonResponseDto handleException(
			final PetrolException exception,
			final HttpServletRequest request,
			final HttpServletResponse response) {
		logger.error(MessageFormat.format("[{0}] {1}", exception.getErrorCode(), exception.getErrorMessage()));
		logger.error(ApiConstants.BLANK, exception);
		HttpStatus status = exception.getHttpStatus();

		if (status != null) {
			response.setStatus(status.value());
		} else {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return this.createErrorDto(exception.getErrorCode());
	}

	private CommonResponseDto createErrorDto(final String errorCode) {
		CommonResponseDto dto = new CommonResponseDto();
		dto.setStatus(ApiConstants.STATUS_CODE_ERROR);
		dto.setResultCode(errorCode);
		return dto;
	}
}