package ah.petrolmanagement.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ah.petrolmanagement.constants.UrlConstants;

public final class EncodeUtil {
	private static final Logger logger = LoggerFactory.getLogger(EncodeUtil.class);

	private EncodeUtil() {
	}

	public static String encodeBase64FromString(final String str) throws UnsupportedEncodingException {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Before: [%s]", str));
		}
		return Base64.encodeBase64String(str.getBytes(UrlConstants.BASE_CHARSET));
	}

	public static String encodeBase64FromByte(final byte[] b) throws UnsupportedEncodingException {
		if (b == null) {
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Before: [%s]", b));
		}
		String byteStr = new String(b, UrlConstants.BASE_CHARSET);
		return encodeBase64FromString(byteStr);
	}

	public static String decodeBase64ToString(final String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Before: [%s]", str);
		}
		return new String(Base64.decodeBase64(str));
	}

	public static byte[] decodeBase64ToByte(final String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Before: [%s]", str);
		}
		return Base64.decodeBase64(str);
	}
}