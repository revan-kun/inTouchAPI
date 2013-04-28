package main.java.com.epam.lab.intouch.api.model.project;

import java.util.Date;
import java.util.List;

import main.java.com.epam.lab.intouch.api.model.member.Member;
import main.java.com.epam.lab.intouch.api.model.project.enums.ProjectStatus;

public class Project {
	private Long id;
	private String projectName;
	private Date creationDate;
	private Date completionDate;
	private String description;
	private List<Member> members;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
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
