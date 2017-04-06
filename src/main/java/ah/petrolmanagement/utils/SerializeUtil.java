package ah.petrolmanagement.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;

public final class SerializeUtil {
	private SerializeUtil() {
	}

	public static String encode(final Object obj) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		XMLEncoder encorder = new XMLEncoder(out);
		encorder.writeObject(obj);
		encorder.close();

		try {
			return new String(out.toByteArray(), UrlConstants.BASE_CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiConstants.BLANK;
		}
	}

	public static Object decode(final String xml) {
		try {
			byte[] bytes = xml.getBytes(UrlConstants.BASE_CHARSET);
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);

			XMLDecoder decoder = new XMLDecoder(in);

			return decoder.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiConstants.BLANK;
		}
	}
}