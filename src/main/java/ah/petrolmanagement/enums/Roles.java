package ah.petrolmanagement.enums;

import java.io.Serializable;

public enum Roles implements Serializable {
	ADMIN("ADMIN"), EMPLOYEE("EMPLOYEE");

	private String role;

	private Roles(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}