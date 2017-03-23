package ah.petrolmanagement.entity;

public class ShiftEntity extends CommonEntity {
	private static final long serialVersionUID = 4829424380443468274L;

	private Integer id;
	private Integer shiftCode;
	private String shiftName;
	private String username;

	public Integer getId() {
		return id;
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