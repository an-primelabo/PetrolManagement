package ah.petrolmanagement.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.properties.ControllerUtilProperties;

public class ControllerUtil {
	public static String callAPI(final String apiPath) {
		return callAPI(apiPath, new HashMap<String, Object>());
	}

	public static String callAPI(final String apiPath, final Map<String, Object> requestJsonMap) {
		final String uri = ControllerUtilProperties.getInstance().getHost() + apiPath;
		return callAPIBase(uri, requestJsonMap);
	}

	private static String callAPIBase(final String uri, final Map<String, Object> requestJsonMap) {
		String resultString = null;
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setHeader(UrlConstants.CONTENT_TYPE_KEY, UrlConstants.HEADER_CONTENT_TYPE_VALUE_JSON);

		try {
			if (requestJsonMap != null) {
				httpPost.setEntity(new StringEntity(JsonUtil.map2Json(requestJsonMap), UrlConstants.BASE_CHARSET));
			}
			HttpClient httpClient = new DefaultHttpClient();
			HttpParams httpParams = httpClient.getParams();

			HttpConnectionParams.setConnectionTimeout(httpParams, ControllerUtilProperties.getInstance().getConnectionTimeout());
			HttpConnectionParams.setSoTimeout(httpParams, ControllerUtilProperties.getInstance().getSocketTimeout());

			HttpResponse httpResponse = httpClient.execute(httpPost);

			resultString = EntityUtils.toString(httpResponse.getEntity(), UrlConstants.BASE_CHARSET);
		} catch (UnsupportedEncodingException e) {
			return null;
		} catch (ClientProtocolException e) {
			return null;
		} catch (ParseException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return resultString;
	}

	public static <T> T convertJson2Dto(final String jsonStr, Class<T> t) {
		T resultData = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			resultData = objectMapper.readValue(jsonStr, t);
		} catch (JsonParseException e) {
			return null;
		} catch (JsonMappingException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return resultData;
	}
}