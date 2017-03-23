package ah.petrolmanagement.dto.request;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class PersistentLoginRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = 4700623282617451835L;

	public static final String USERNAME = "username";
	public static final String SERIES = "series";
	public static final String TOKEN = "token";
	public static final String LAST_USED = "lastUsed";

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String username;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String series;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String token;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Date lastUsed;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
}