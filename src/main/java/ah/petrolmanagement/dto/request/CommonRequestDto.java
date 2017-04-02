package ah.petrolmanagement.dto.request;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import ah.petrolmanagement.dto.BaseDto;

public class CommonRequestDto extends BaseDto {
	private static final long serialVersionUID = -3452004146554257620L;

	public static final String INS_TIME = "insTime";
	public static final String INS_USER = "insUser";
	public static final String UPD_USER = "updUser";
	public static final String DEL_FLAG = "delFlag";
	public static final String DEL_USER = "delUser";
	public static final String MODE = "mode";

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Date insTime;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String insUser;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Date updTime;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String updUser;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer delFlag;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Date delTime;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String delUser;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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