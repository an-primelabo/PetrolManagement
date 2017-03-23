package ah.petrolmanagement.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonUtil {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private JsonUtil() {
	}

	public static String object2Json(final Object object) throws JsonGenerationException, JsonMappingException, IOException {
		if (object == null) {
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Before: [%s]", ReflectionToStringBuilder.toString(object)));
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	public static <T> T json2Object(final String json, final Class<T> valueType) throws JsonParseException, JsonMappingException, IOException {
		if (StringUtils.isEmpty(json) || valueType == null) {
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("JSON Before: [%s]", json));
		}
		ObjectMapper mapper = new ObjectMapper();
		return (T) mapper.readValue(json, valueType);
	}

	public static String map2Json(final Map<String, Object> map) throws JsonGenerationException, JsonMappingException, IOException {
		if (map == null) {
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Map Before: [%s]", map));
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(map);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(final String json) throws JsonParseException, JsonMappingException, IOException {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("JSON Before: [%s]", json);
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, new HashMap<String, Object>().getClass());
	}
}