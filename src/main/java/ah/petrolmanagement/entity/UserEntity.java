package ah.petrolmanagement.entity;

import java.util.Date;

public class UserEntity extends CommonEntity {
	private static final long serialVersionUID = 303010784044928402L;

	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private Date birthday;
	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserEntity [username=" + username + ", password=" + password
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", birthday=" + birthday + ", phone=" + phone + "]";
	}
}