package ah.petrolmanagement.enums;

import java.io.Serializable;

import ah.petrolmanagement.constants.ApiConstants;

public enum Shifts implements Serializable {
	ALLDAY(0, "Cả Ngày"), MORNING(1, "Ca Sáng"), EVENING(2, "Ca Tối");

	private Integer code;
	private String value;

	private Shifts(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getValue() {
		return this.value;
	}

	public static String getByCode(Integer code) {
		for (Shifts shift : Shifts.values()) {
			if (shift.getCode() == code) {
				return shift.getValue();
			}
		}
		return ApiConstants.BLANK;
	}
}