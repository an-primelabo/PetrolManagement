package ah.petrolmanagement.enums;

import ah.petrolmanagement.constants.ApiConstants;

public enum Shifts {
	ALLDAY(0, "Cả Ngày"), MORNING(1, "Ca Sáng"), EVENING(2, "Ca Tối");

	private int code;
	private String value;

	private Shifts(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public int getCode() {
		return this.code;
	}

	public String getValue() {
		return this.value;
	}

	public static String getByCode(int code) {
		for (Shifts shift : Shifts.values()) {
			if (shift.getCode() == code) {
				return shift.getValue();
			}
		}
		return ApiConstants.BLANK;
	}
}