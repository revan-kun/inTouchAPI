package com.epam.lab.intouch.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.epam.lab.intouch.api.model.member.Member;
import com.epam.lab.intouch.api.service.util.Attribute;
import com.epam.lab.intouch.api.service.util.PropertyConfigurator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MemberService {
	public Member login(String login, String password) throws IOException {
		Member member = new Member();
		member.setLogin(login);
		member.setPassword(password);
		return login(member);
	}

	public Member login(Member member) throws IOException {
		String host = PropertyConfigurator.getProperty("host");
		String port = PropertyConfigurator.getProperty("port");

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair(Attribute.MEMBER_LOGIN, member.getLogin()));
		params.add(new BasicNameValuePair(Attribute.PASSWORD_LOGIN, member.getPassword()));

		UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(params, "UTF-8");
		HttpPost httpPost = new HttpPost("http://" + host + ":" + port + "/InTouch/rest/login");
		httpPost.setEntity(encodedEntity);

		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);

		HttpEntity gsonEntity = response.getEntity();
		String gsonString = EntityUtils.toString(gsonEntity, "UTF-8");

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		Gson gson = builder.create();
		Member loginedMember = gson.fromJson(gsonString, Member.class);

		EntityUtils.consume(gsonEntity);

		return loginedMember;
	}
}
