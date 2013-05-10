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
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("userlogin", member.getLogin()));
		params.add(new BasicNameValuePair("password", member.getPassword()));

		UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(params, "UTF-8");
		//HttpPost httpPost = new HttpPost("http://intouch.j.rsnx.ru/inTouchAPI/rest/login");
		HttpPost httpPost = new HttpPost("http://localhost:8080/InTouch/rest/login");
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
