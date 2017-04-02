package ah.petrolmanagement.enums;

import java.io.Serializable;

import ah.petrolmanagement.constants.ApiConstants;

public enum Categories implements Serializable {
	FUEL(1, "Xăng"), LUBE(2, "Nhớt Bình"), OIL(3, "Nhớt Lẻ");

	private Integer code;
	private String value;

	private Categories(Integer code, String value) {
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
		for (Categories category : Categories.values()) {
			if (category.getCode() == code) {
				return category.getValue();
			}
		}
		return ApiConstants.BLANK;
	}
}