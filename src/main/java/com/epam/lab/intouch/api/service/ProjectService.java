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
	public Project getProgect(Long id) throws IOException {
		Project project = new Project();
		project.setId(id);
		return getProgect(project);
	}

	public Project getProgect(Project project) throws IOException {
		Long projectID = project.getId();
		// HttpGet httpget = new HttpGet("http://" + host + ":" + port + "/InTouch/rest/project?" + Attribute.PROJECT_ID + "=" + projectID);
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(getProperty("protocol")).append(getProperty("host")).append(getProperty("port.separator")).append(getProperty("port"))
				.append(getProperty("root.path")).append(getProperty("rest.path")).append(getProperty("rest.project")).append("?").append(Attribute.PROJECT_ID).append("=").append(projectID);
		
		HttpGet httpget = new HttpGet(urlBuilder.toString());
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpget);

		HttpEntity gsonEntity = response.getEntity();
		String gsonString = EntityUtils.toString(gsonEntity, "UTF-8");

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		Gson gson = builder.create();
		Project deserializedProject = gson.fromJson(gsonString, Project.class);

		EntityUtils.consume(gsonEntity);

		return deserializedProject;
	}
}
