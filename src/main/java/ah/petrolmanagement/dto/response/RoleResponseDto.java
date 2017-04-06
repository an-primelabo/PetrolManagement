package ah.petrolmanagement.dto.response;

import ah.petrolmanagement.dto.BaseDto;

public class RoleResponseDto extends BaseDto {
	private static final long serialVersionUID = -100844693245139062L;

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