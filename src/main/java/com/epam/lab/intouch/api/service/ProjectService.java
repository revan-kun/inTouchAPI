package com.epam.lab.intouch.api.service;

import static com.epam.lab.intouch.api.service.util.PropertyConfigurator.getProperty;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.epam.lab.intouch.api.model.project.Project;
import com.epam.lab.intouch.api.service.util.Attribute;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Zatorsky D.B
 * 
 */
public class ProjectService {
	private static final String LIGHT_PROJECT = "lightProject";
	private static final String MIDDLE_PROJECT = "middleProject";

	/**
	 * Builds URL for accessing to inTouch service provider
	 * 
	 * @param projectID
	 *            project id
	 * @param projectType
	 *            project type (full project or project's info only)
	 * @return URL to project service
	 * @throws IOException
	 */
	private String getURL(Long projectID, String projectType) throws IOException {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(getProperty("protocol")).append(getProperty("host")).append(getProperty("port.separator")).append(getProperty("port"))
				.append(getProperty("root.path")).append(getProperty("rest.path")).append(getProperty("rest.project")).append("?").append(Attribute.PROJECT_ID)
				.append("=").append(projectID).append("&").append(Attribute.PROJECT_TYPE).append("=").append(projectType);
		return urlBuilder.toString();
	}

	/**
	 * Deserializes project from json response
	 * 
	 * @param json
	 *            response from inTouch server
	 * @return Member object
	 */
	private Project deserializeProject(String json) {
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		Gson gson = builder.create();
		Project deserializedProject = gson.fromJson(json, Project.class);

		return deserializedProject;
	}

	/**
	 * This method returns project in accordance to it's type (full project or project's info only).
	 * 
	 * @param project
	 *            project object
	 * @param projectType
	 *            project type
	 * @return project object
	 * @throws IOException
	 */
	public Project getProgect(Project project, String projectType) throws IOException {
		Long projectID = project.getId();

		HttpGet httpget = new HttpGet(getURL(projectID, projectType));
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpget);

		HttpEntity gsonEntity = response.getEntity();
		String gsonString = EntityUtils.toString(gsonEntity, "UTF-8");

		EntityUtils.consume(gsonEntity);

		Project deserializedProject = deserializeProject(gsonString);

		return deserializedProject;
	}

	/**
	 * This method provides light project - project with only information about project (without members on that project)
	 * 
	 * @param id
	 *            project id
	 * @return project with info
	 * @throws IOException
	 */
	public Project getProgectInfo(Long id) throws IOException {
		Project project = new Project();
		project.setId(id);
		return getProgect(project, LIGHT_PROJECT);
	}

	/**
	 * This method provides project with information about it inside and also it includes members who works on the project.
	 * 
	 * @param id
	 *            project id
	 * @return full project with information and members inside
	 * @throws IOException
	 */
	public Project getProgect(Long id) throws IOException {
		Project project = new Project();
		project.setId(id);
		return getProgect(project, MIDDLE_PROJECT);
	}

}
