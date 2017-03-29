package ah.petrolmanagement.entity;

import java.io.Serializable;

import ah.petrolmanagement.enums.Roles;

public class RoleEntity implements Serializable {
	private static final long serialVersionUID = -2874730873393707437L;

	private Integer id;
	private String username;
	private String role = Roles.EMPLOYEE.getRole();

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

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", username=" + username + ", role="
				+ role + "]";
	}
}