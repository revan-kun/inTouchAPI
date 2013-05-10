package com.epam.lab.intouch.api.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.epam.lab.intouch.api.model.project.Project;
import com.epam.lab.intouch.api.service.util.Attribute;
import com.epam.lab.intouch.api.service.util.PropertyConfigurator;
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

		String host = PropertyConfigurator.getProperty("host");
		String port = PropertyConfigurator.getProperty("port");
	
		HttpGet httpget = new HttpGet("http://" + host + ":" + port + "/InTouch/rest/project?" + Attribute.PROJECT_ID + "=" + projectID);

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
