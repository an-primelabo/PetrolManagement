package ah.petrolmanagement.dto.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class ShiftRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -4629901158489613382L;

	public static final String ID = "id";
	public static final String SHIFT_CODE = "shiftCode";
	public static final String SHIFT_NAME = "shiftName";
	public static final String USERNAME = "username";

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer id;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer shiftCode;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String shiftName;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String username;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(Integer shiftCode) {
		this.shiftCode = shiftCode;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}