package ah.petrolmanagement.entity;

import java.io.Serializable;

public class RoleEntity implements Serializable {
	private static final long serialVersionUID = -2874730873393707437L;

	private Integer id;
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}