package ah.petrolmanagement.dto.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class UserRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = 3721673980859484844L;

	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String FIRSTNAME = "firstname";
	public static final String LASTNAME = "lastname";
	public static final String EMAIL = "email";
	public static final String ROLE_ID = "roleId";

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer id;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String username;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String password;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String firstname;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String lastname;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String email;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}