package com.epam.lab.intouch.api.model.member;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.intouch.api.model.member.enums.Role;
import com.epam.lab.intouch.api.model.project.Project;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member {
	@Expose
	@SerializedName("login")
	private String login;

	@Expose
	@SerializedName("password")
	private String password;

	@Expose
	@SerializedName("lastName")
	private String lastName;

	@Expose
	@SerializedName("firstName")
	private String firstName;

	@Expose
	@SerializedName("projectRole")
	private Role projectRole;

	@Expose
	@SerializedName("projects")
	private List<Project> activeProjects;

	public Member() {
		activeProjects = new ArrayList<Project>();
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

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setLogin(final String login) {
		this.login = login;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public List<Project> getActiveProjects() {
		return activeProjects;
	}

	public void setActiveProjects(List<Project> activeProjects) {
		this.activeProjects = activeProjects;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n Member login: ").append(login).append("\n Name: ").append(firstName).append("\n Surname: ").append(lastName).append("\n Project role: ")
				.append(projectRole).append("\n Member projects: ");

		for (Project project : activeProjects) {
				sb.append(project.toString());
		}

		return sb.toString();
	}

}
