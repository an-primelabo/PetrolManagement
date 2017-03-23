package ah.petrolmanagement.exception;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.http.HttpStatus;

import ah.petrolmanagement.utils.JsonUtil;

public class PetrolException extends Exception {
	private static final long serialVersionUID = 2860858657069758119L;

	private BaseError error = new BaseError();
	private HttpStatus httpStatus;

	public PetrolException() {
	}

	public PetrolException(final String errorCode, final String errorMessage) {
		this.error.setErrorCode(errorCode);
		this.error.setErrorMessage(errorMessage);
	}

	public PetrolException(final String errorCode, final String errorMessage, final Throwable t) {
		super(t);
		this.error.setErrorCode(errorCode);
		this.error.setErrorMessage(errorMessage);
	}

	public String getErrorCode() {
		return this.error.getErrorCode();
	}

	public void setErrorCode(final String errorCode) {
		this.error.setErrorCode(errorCode);
	}

	public String getErrorMessage() {
		return this.error.getErrorMessage();
	}

	public void setErrorMessage(final String errorMessage) {
		this.error.setErrorMessage(errorMessage);
	}

	public String toJson() throws IOException {
		return JsonUtil.object2Json(this.error);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(final HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}

	private class BaseError implements Serializable {
		private static final long serialVersionUID = -2780969401787087233L;

		private String errorCode;
		private String errorMessage;

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
	}
}