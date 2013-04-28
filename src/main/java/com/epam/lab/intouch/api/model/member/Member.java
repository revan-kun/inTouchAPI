package main.java.com.epam.lab.intouch.api.model.member;

import java.util.Date;

import main.java.com.epam.lab.intouch.api.model.member.enums.Role;
import main.java.com.epam.lab.intouch.api.model.member.enums.Sex;

/**
 * @author Revan
 *
 * @version 0.2
 */
public class Member {

	private String login;
	private String password;
	
	private String lastName;
	private String firstName;
	
	private Sex sex;
	
	private Role projectRole;
	
	private Date birthday;
	private Date registrationDate;

	public Member() {

	}
	
	public Member(final String login, final String password) {
		this.login = login;
		this.password = password;
	}

	public Role getRole() {
		return this.projectRole;
	}

	public void setRole(final Role role) {
		this.projectRole = role;
	}

	public void setBirthday(final Date birthday) {
		this.birthday = birthday;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public void setRegistrationDate(final Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setSex(final Sex sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getLogin() {
		return this.login;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public Sex getSex() {
		return this.sex;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
