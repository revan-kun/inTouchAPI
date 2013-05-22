package com.epam.lab.intouch.api.service;

import static com.epam.lab.intouch.api.service.util.PropertyConfigurator.getProperty;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MemberService {

	private String getURL(String restServiceName) throws IOException {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(getProperty("protocol")).append(getProperty("host")).append(getProperty("port.separator")).append(getProperty("port"))
				.append(getProperty("root.path")).append(getProperty("rest.path")).append(restServiceName);
		
		System.out.println(urlBuilder.toString());
		
		return urlBuilder.toString();
	}

	private HttpResponse executeRequest(List<NameValuePair> params, String restServiceName) throws IOException {
		UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(params, "UTF-8");
		HttpPost httpPost = new HttpPost(getURL(restServiceName));
		httpPost.setEntity(encodedEntity);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);

		return response;
	}

	private Member deserializeMember(String json) {
		
		System.out.println(json);
		
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		Gson gson = builder.create();
		Member member = gson.fromJson(json, Member.class);

		System.out.println(member);
		
		return member;
	}

	private Member getMember(Member member, String memberType) throws IOException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair(Attribute.MEMBER_LOGIN, member.getLogin()));
		params.add(new BasicNameValuePair(Attribute.MEMBER_PASSWORD, member.getPassword()));
		params.add(new BasicNameValuePair(Attribute.MEMBER_WEIGHT, memberType));

		HttpResponse response = executeRequest(params, getProperty("rest.login"));

		HttpEntity gsonEntity = response.getEntity();
		String gsonString = EntityUtils.toString(gsonEntity, "UTF-8");

		Member loginedMember = deserializeMember(gsonString);

		EntityUtils.consume(gsonEntity);

		return loginedMember;
	}

	private Member buildMember(String login, String password) {
		Member member = new Member();
		member.setLogin(login);
		member.setPassword(password);

		return member;
	}

	public Member getLightweightMember(String login, String password) throws IOException {
		return getLightweightMember(buildMember(login, password));
	}

	public Member getLightweightMember(Member member) throws IOException {
		Member loginedMember = getMember(member, Attribute.LIGHT_MEMBER);
		return loginedMember;
	}

	public Member getHeavyMember(String login, String password) throws IOException {
		return getHeavyMember(buildMember(login, password));
	}

	public Member getHeavyMember(Member member) throws IOException {
		Member loginedMember = getMember(member, Attribute.HEAVY_MEMBER);
		return loginedMember;
	}

	public Member getMiddleweightMember(Member member) throws IOException {
		Member loginedMember = getMember(member, Attribute.MIDDLE_MEMBER);
		return loginedMember;
	}

	public Member getMiddleweightMember(String login, String password) throws IOException {
		return getMiddleweightMember(buildMember(login, password));
	}

	public InputStream getPhoto(String login) throws IOException {
		Member member = new Member();
		member.setLogin(login);

		return getPhoto(member);
	}

	public InputStream getPhoto(Member member) throws IOException {
		StringBuilder urlBuilder = new StringBuilder();

		String url = getURL(getProperty("rest.photo"));
		urlBuilder.append(url).append("?").append(Attribute.MEMBER_LOGIN).append("=").append(member.getLogin());

		return new URL(urlBuilder.toString()).openStream();
	}

}
