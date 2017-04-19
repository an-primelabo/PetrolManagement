package ah.petrolmanagement.dto.request;

import java.util.Date;

import ah.petrolmanagement.dto.BaseDto;

public class CommonRequestDto extends BaseDto {
	private static final long serialVersionUID = -3452004146554257620L;

	public static final String ID = "id";
	public static final String CATEGORY_ID = "categoryId";
	public static final String CATEGORY_NAME = "categoryName";
	public static final String PRODUCT_ID = "productId";
	public static final String PRODUCT_NAME = "productName";
	public static final String PRICE_ID = "priceId";
	public static final String PRICE = "price";
	public static final String TANK_ID = "tankId";
	public static final String TANK_NAME = "tankName";
	public static final String INS_TIME = "insTime";
	public static final String INS_USER = "insUser";
	public static final String UPD_TIME = "updTime";
	public static final String UPD_USER = "updUser";
	public static final String DEL_FLAG = "delFlag";
	public static final String DEL_TIME = "delTime";
	public static final String DEL_USER = "delUser";
	public static final String MODE = "mode";

	private Date insTime;
	private String insUser;
	private Date updTime;
	private String updUser;
	private Integer delFlag;
	private Date delTime;
	private String delUser;
	private String mode;

	public Date getInsTime() {
		return insTime;
	}

	public void setInsTime(Date insTime) {
		this.insTime = insTime;
	}

	public String getInsUser() {
		return insUser;
	}

	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public String getDelUser() {
		return delUser;
	}

	public void setDelUser(String delUser) {
		this.delUser = delUser;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}