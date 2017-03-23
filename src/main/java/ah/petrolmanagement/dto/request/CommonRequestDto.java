package ah.petrolmanagement.dto.request;

import java.util.Date;

import ah.petrolmanagement.dto.BaseDto;

public class CommonRequestDto extends BaseDto {
	private static final long serialVersionUID = -3452004146554257620L;

	public static final String INS_TIME = "insTime";
	public static final String DEL_FLAG = "delFlag";

	private Date insTime;
	private String insUser;
	private Date updTime;
	private String updUser;
	private Integer delFlag;
	private Date delTime;
	private String delUser;

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
}