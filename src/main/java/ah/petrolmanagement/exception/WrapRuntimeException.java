package ah.petrolmanagement.exception;

public class WrapRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 2582677380785719498L;

	public WrapRuntimeException(final Throwable cause) {
		super(cause);
	}

	public WrapRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}
}