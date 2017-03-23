package ah.petrolmanagement.enums;

import ah.petrolmanagement.constants.ApiConstants;

public enum Categories {
	FUEL(1, "Xăng"), LUBE(2, "Nhớt Bình"), OIL(3, "Nhớt Lẻ");

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