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

public class ProjectService {
	private String getURL(Long projectID) throws IOException {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(getProperty("protocol")).append(getProperty("host")).append(getProperty("port.separator")).append(getProperty("port"))
				.append(getProperty("root.path")).append(getProperty("rest.path")).append(getProperty("rest.project")).append("?").append(Attribute.PROJECT_ID)
				.append("=").append(projectID);
		return urlBuilder.toString();
	}

	private Project deserializeProject(String json) {
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		Gson gson = builder.create();
		Project deserializedProject = gson.fromJson(json, Project.class);

		return deserializedProject;
	}

	public Project getProgect(Project project) throws IOException {
		Long projectID = project.getId();

		HttpGet httpget = new HttpGet(getURL(projectID));
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpget);

		HttpEntity gsonEntity = response.getEntity();
		String gsonString = EntityUtils.toString(gsonEntity, "UTF-8");

		EntityUtils.consume(gsonEntity);

		Project deserializedProject = deserializeProject(gsonString);

		return deserializedProject;
	}

	public Project getProgect(Long id) throws IOException {
		Project project = new Project();
		project.setId(id);
		return getProgect(project);
	}
}
