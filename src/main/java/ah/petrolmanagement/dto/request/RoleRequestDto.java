package ah.petrolmanagement.dto.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class RoleRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -3714260269081033072L;

	public static final String ID = "id";
	public static final String ROLE = "role";

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer id;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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