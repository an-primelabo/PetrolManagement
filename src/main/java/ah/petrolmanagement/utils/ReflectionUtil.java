package ah.petrolmanagement.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReflectionUtil {
	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

	private ReflectionUtil() {
	}

	public static Method getGetterMethod(final String propertyName, final Object object) throws IntrospectionException {
		if (StringUtils.isBlank(propertyName)) {
			logger.error("getGetterMethod called. but, propertyName is nothing.");
			throw new InvalidParameterException("getGetterMethod called. but, propertyName is nothing.");
		}
		if (object == null) {
			logger.error("getGetterMethod called. but, target object is nothing.");
			throw new InvalidParameterException("getGetterMethod called. but, target object is nothing.");
		}
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, object.getClass());
		return propertyDescriptor.getReadMethod();
	}

	public static Method getSetterMethod(final String propertyName, final Object object) throws IntrospectionException {
		if (StringUtils.isBlank(propertyName)) {
			logger.error("getSetterMethod called. but, propertyName is nothing.");
			throw new InvalidParameterException("getSetterMethod called. but, propertyName is nothing.");
		}
		if (object == null) {
			logger.error("getSetterMethod called. but, target object is nothing.");
			throw new InvalidParameterException("getSetterMethod called. but, target object is nothing.");
		}
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, object.getClass());
		return propertyDescriptor.getWriteMethod();
	}
}