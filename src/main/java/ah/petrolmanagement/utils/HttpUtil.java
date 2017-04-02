package ah.petrolmanagement.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.BaseDto;
import ah.petrolmanagement.entity.QueryString;

public final class HttpUtil {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final DefaultHttpClient httpClient = new DefaultHttpClient();

	public HttpResponse executeGet(
			final List<QueryString> queryStrings,
			final String uri,
			final Map<String, String> headers,
			final String jsessionId) throws URISyntaxException, ClientProtocolException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("Request Post Paramter ----");
			logger.debug(String.format("queryStrings : %s", queryStrings != null ? queryStrings.toString() : ApiConstants.BLANK));
			logger.debug(String.format("URI : %s", uri));
			logger.debug(String.format("headers : %s", headers != null ? headers.toString() : ApiConstants.BLANK));
			logger.debug(String.format("jsessionId : %s", jsessionId));
		}
		HttpGet httpGet = new HttpGet();

		this.addHeader(httpGet, headers);

		String requestUri = this.setQueryString(uri, queryStrings);

		httpGet.setURI(new URI(requestUri));

		this.setSessionId(httpGet, jsessionId);
		return httpClient.execute(httpGet);
	}

	public HttpResponse executePut(
			final List<QueryString> queryStrings,
			final BaseDto dto,
			final String uri,
			final Map<String, String> headers,
			final String jsessionId) throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("Request Post Paramter ----");
			logger.debug(String.format("queryStrings : %s", queryStrings != null ? queryStrings.toString() : ApiConstants.BLANK));
			logger.debug(String.format("dto : %s", dto != null ? dto.toString() : ApiConstants.BLANK));
			logger.debug(String.format("URI : %s", uri));
			logger.debug(String.format("headers : %s", headers != null ? headers.toString() : ApiConstants.BLANK));
			logger.debug(String.format("jsessionId : %s", jsessionId));
		}
		HttpPut httpPut = new HttpPut();

		this.addHeader(httpPut, headers);

		String requestUri = this.setQueryString(uri, queryStrings);

		httpPut.setURI(new URI(requestUri));

		if (dto != null) {
			String requestJson = JsonUtil.object2Json(dto);
			StringEntity params = new StringEntity(requestJson, UrlConstants.BASE_CHARSET);

			httpPut.addHeader(UrlConstants.CONTENT_TYPE_KEY, UrlConstants.HEADER_CONTENT_TYPE_VALUE_JSON);
			httpPut.setEntity(params);
		}
		this.setSessionId(httpPut, jsessionId);
		return httpClient.execute(httpPut);

	}

	public HttpResponse executeDelete(
			final List<QueryString> queryStrings,
			final String uri,
			final Map<String, String> headers,
			final String jsessionId) throws URISyntaxException, ClientProtocolException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("Request Post Paramter ----");
			logger.debug(String.format("queryStrings : %s", queryStrings != null ? queryStrings.toString() : ApiConstants.BLANK));
			logger.debug(String.format("URI : %s", uri));
			logger.debug(String.format("headers : %s", headers != null ? headers.toString() : ApiConstants.BLANK));
			logger.debug(String.format("jsessionId : %s", jsessionId));
		}
		HttpDelete httpDelete = new HttpDelete();

		this.addHeader(httpDelete, headers);

		String requestUri = this.setQueryString(uri, queryStrings);

		httpDelete.setURI(new URI(requestUri));

		this.setSessionId(httpDelete, jsessionId);
		return httpClient.execute(httpDelete);
	}

	public HttpResponse executePost(
			final List<QueryString> queryStrings,
			final BaseDto dto,
			final String uri,
			final Map<String, String> headers,
			final String jsessionId) throws ClientProtocolException, IOException, URISyntaxException {
		if (logger.isDebugEnabled()) {
			logger.debug("Request Post Paramter ----");
			logger.debug(String.format("queryStrings : %s", queryStrings != null ? queryStrings.toString() : ApiConstants.BLANK));
			logger.debug(String.format("dto : %s", dto != null ? dto.toString() : ApiConstants.BLANK));
			logger.debug(String.format("URI : %s", uri));
			logger.debug(String.format("headers : %s", headers != null ? headers.toString() : ApiConstants.BLANK));
			logger.debug(String.format("jsessionId : %s", jsessionId));
		}
		HttpPost httpPost = new HttpPost();

		this.addHeader(httpPost, headers);

		String requestUri = this.setQueryString(uri, queryStrings);

		httpPost.setURI(new URI(requestUri));

		if (dto != null) {
			String requestJson = JsonUtil.object2Json(dto);
			StringEntity params = new StringEntity(requestJson, UrlConstants.BASE_CHARSET);

			httpPost.addHeader(UrlConstants.CONTENT_TYPE_KEY, UrlConstants.HEADER_CONTENT_TYPE_VALUE_JSON);
			httpPost.setEntity(params);
		}
		this.setSessionId(httpPost, jsessionId);
		return httpClient.execute(httpPost);
	}

	private void setSessionId(final HttpRequestBase requestBase, final String jsessionId) {
		if (StringUtils.isEmpty(jsessionId)) {
			return;
		}
		requestBase.addHeader(UrlConstants.REQUEST_HEADER_COOKIE, UrlConstants.REQUEST_COOKIE_JSESSIONID + "=" + jsessionId);
	}

	private void setProxy(final boolean isProxy, final HttpHost httpHost) {
		if (isProxy) {
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, httpHost);
		}
	}

	public void setProxy(final boolean isProxy, final String proxyHost, final Integer proxyPort, final String proxySheme) {
		HttpHost httpHost = new HttpHost(proxyHost, proxyPort, proxySheme);
		this.setProxy(isProxy, httpHost);
	}

	public void setProxy(
			final boolean isProxy, final String proxyHost,
			final Integer proxyPort, final String proxySheme,
			final String userName, final String password) {
		if (isProxy) {
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(proxyHost, proxyPort),
					new UsernamePasswordCredentials(userName, password));
		}
		HttpHost httpHost = new HttpHost(proxyHost, proxyPort, proxySheme);
		this.setProxy(isProxy, httpHost);
	}

	private void addHeader(final HttpRequestBase httpRequestBase, final Map<String, String> headers) {
		if (headers == null || headers.size() == 0) {
			return;
		}
		for (Entry<String, String> entry : headers.entrySet()) {
			httpRequestBase.addHeader(entry.getKey(), entry.getValue());
		}
	}

	private String setQueryString(final String uri, final List<QueryString> queryStrings) {
		if (queryStrings == null || queryStrings.size() == 0) {
			return uri;
		}
		String addedUri = uri;

		for (int i = 0; i < queryStrings.size(); i++) {
			if (i == 0) {
				addedUri += UrlConstants.SIGN_QST;
			}
			addedUri += queryStrings.get(i).getKey() + UrlConstants.SIGN_EQL + queryStrings.get(i).getValue();

			if (i < queryStrings.size() - 1) {
				addedUri += UrlConstants.SIGN_AMP;
			}
		}
		return addedUri;
	}
}
