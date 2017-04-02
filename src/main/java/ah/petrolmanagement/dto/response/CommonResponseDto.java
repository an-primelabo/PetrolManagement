package ah.petrolmanagement.dto.response;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import ah.petrolmanagement.dto.BaseDto;

public class CommonResponseDto extends BaseDto {
	private static final long serialVersionUID = 3759025953033144829L;

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