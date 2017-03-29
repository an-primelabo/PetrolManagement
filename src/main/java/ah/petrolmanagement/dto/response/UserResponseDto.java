package ah.petrolmanagement.dto.response;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class UserResponseDto extends CommonResponseDto {
	private static final long serialVersionUID = 271198259591053137L;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String username;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String password;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String firstname;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String lastname;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Date birthday;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}