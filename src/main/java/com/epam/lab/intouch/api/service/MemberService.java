package com.epam.lab.intouch.api.service;

import static com.epam.lab.intouch.api.service.util.PropertyConfigurator.getProperty;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MemberService {
	private static final String LIGHT_MEMBER = "lightMember";
	private static final String MIDDLE_MEMBER = "middleMember";
	private static final String HEAVY_MEMBER = "heavyMember";

	private String getURL() throws IOException {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(getProperty("protocol")).append(getProperty("host")).append(getProperty("port.separator")).append(getProperty("port"))
				.append(getProperty("root.path")).append(getProperty("rest.path")).append(getProperty("rest.login"));
		return urlBuilder.toString();
	}

	private HttpResponse executeRequest(List<NameValuePair> params) throws IOException {
		UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(params, "UTF-8");
		HttpPost httpPost = new HttpPost(getURL());
		httpPost.setEntity(encodedEntity);

		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);

		return response;
	}

	private Member deserializeMember(String json) {
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		Gson gson = builder.create();
		Member member = gson.fromJson(json, Member.class);

		return member;
	}

	private Member getMember(Member member, String memberType) throws IOException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair(Attribute.MEMBER_LOGIN, member.getLogin()));
		params.add(new BasicNameValuePair(Attribute.PASSWORD_LOGIN, member.getPassword()));
		params.add(new BasicNameValuePair(Attribute.MEMBER_TYPE, memberType));

		HttpResponse response = executeRequest(params);

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
		Member loginedMember = getMember(member, LIGHT_MEMBER);
		return loginedMember;
	}

	public Member getHeavyMember(String login, String password) throws IOException {
		return getHeavyMember(buildMember(login, password));
	}

	public Member getHeavyMember(Member member) throws IOException {
		Member loginedMember = getMember(member, HEAVY_MEMBER);
		return loginedMember;
	}

	public Member getMiddleweightMember(Member member) throws IOException {
		Member loginedMember = getMember(member, MIDDLE_MEMBER);
		return loginedMember;
	}

	public Member getMiddleweightMember(String login, String password) throws IOException {
		return getMiddleweightMember(buildMember(login, password));
	}

}
