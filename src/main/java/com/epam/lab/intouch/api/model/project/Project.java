package com.epam.lab.intouch.api.model.project;

import java.util.Date;
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
	@SerializedName("creationDate")
	private Date creationDate;
	
	@Expose
	@SerializedName("completionDate")
	private Date completionDate;
	
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

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("\n\tId: ").append(id)
		.append("\n\tProject name: ").append(projectName)
		.append("\n\tCreation date: ").append(creationDate)
		.append("\n\tCompletion date: ").append(completionDate)
		.append("\n\tDescription: ").append(description)
		.append("\n\tStatus: ").append(status)
		.append("\n\tMembers in project: ");
		
		int i=1;
		for(Member member:members){
			StringBuilder memberStringBuilder=new StringBuilder();
			memberStringBuilder.append("\n\tMember #").append(i)
			.append("\n\t\tLogin: ").append(member.getLogin())
			.append("\n\t\tName: ").append(member.getFirstName())
			.append("\n\t\tSurname: ").append(member.getLastName())
			.append("\n\t\tProject role: ").append(member.getRole());
			
			sb.append(memberStringBuilder.toString());
			
			i++;
		}
		
		return sb.toString();
	}

	
}
