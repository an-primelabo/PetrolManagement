package ah.petrolmanagement.dto.request;

import ah.petrolmanagement.dto.BaseDto;

public class RoleRequestDto extends BaseDto {
	private static final long serialVersionUID = -3714260269081033072L;

	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String ROLE = "role";

	private Integer id;
	private String username;
	private String role;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}