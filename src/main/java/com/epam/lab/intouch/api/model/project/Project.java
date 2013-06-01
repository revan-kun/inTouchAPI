package com.epam.lab.intouch.api.model.project;

import java.util.List;

import com.epam.lab.intouch.api.model.member.Member;
import com.epam.lab.intouch.api.model.project.enums.ProjectStatus;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project {
	@Expose
	@SerializedName("id")
	private Long id;

	@Expose
	@SerializedName("projectName")
	private String projectName;

	@Expose
	@SerializedName("description")
	private String description;

	@Expose
	@SerializedName("members")
	private List<Member> members;

	@Expose
	@SerializedName("status")
	private ProjectStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

}
