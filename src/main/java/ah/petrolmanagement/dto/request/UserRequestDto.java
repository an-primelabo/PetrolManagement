package ah.petrolmanagement.dto.request;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class UserRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = 3721673980859484844L;

	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String FIRSTNAME = "firstname";
	public static final String LASTNAME = "lastname";
	public static final String BIRTHDAY = "birthday";
	public static final String PHONE = "phone";

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