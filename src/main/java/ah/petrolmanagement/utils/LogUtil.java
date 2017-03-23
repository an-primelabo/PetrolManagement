package ah.petrolmanagement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ah.petrolmanagement.constants.ApiConstants;

public final class LogUtil {
	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

	private LogUtil() {
	}

	public static void startMethod(final String className, final String methodName, final Object... objects) {
		logger.debug(String.format("Start Class[%s] Method[%s] Params[%s]", className, methodName, objects));
	}

	public static void endMethod(final String className, final String methodName, final Object... objects) {
		logger.debug(String.format("End Class[%s] Method[%s] Params[%s]", className, methodName, objects));
	}

	public static void errorLog(final String className, final String methodName, final Object... objects) {
		logger.error(String.format("Error Class[%s] Method[%s] Params[%s]", className, methodName, objects));
	}

	public static void errorLog(final String className, final String methodName, final Throwable t, final Object... objects) {
		logger.error(String.format("Error Class[%s] Method[%s] Exception[%s] Params[%s]", className, methodName, t.getMessage(), objects));
		logger.error(ApiConstants.BLANK, t);
	}
}