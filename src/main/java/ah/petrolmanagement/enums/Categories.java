package ah.petrolmanagement.enums;

import java.io.Serializable;

import ah.petrolmanagement.constants.ApiConstants;

public enum Categories implements Serializable {
	FUEL(1, "Xăng"), OIL(2, "Nhớt"), LUBE(3, "Nhớt Bình"), OTHER(4, "Nhớt Lẻ");

	private int code;
	private String value;

	private Categories(int code, String value) {
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
		for (Categories category : Categories.values()) {
			if (category.getCode() == code) {
				return category.getValue();
			}
		}
		return ApiConstants.BLANK;
	}
}