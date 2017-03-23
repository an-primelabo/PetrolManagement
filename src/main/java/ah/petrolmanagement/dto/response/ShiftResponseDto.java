package ah.petrolmanagement.dto.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class ShiftResponseDto extends CommonResponseDto {
	private static final long serialVersionUID = -5309258621240265272L;

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