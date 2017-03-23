package ah.petrolmanagement.dto.response;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class PersistentLoginResponseDto extends CommonResponseDto {
	private static final long serialVersionUID = -5420682695263556983L;

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