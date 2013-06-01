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

/**
 * This class provides three different types of users, and it also provides photo associated with them
 * 
 * @author Zatorsky D.B
 * 
 */
public class MemberService {

	/**
	 * Builds URL for accessing to inTouch service provider
	 * 
	 * @param restServiceName
	 *            service name
	 * @return URL to inTouch Service
	 * @throws IOException
	 */
	private String getURL(String restServiceName) throws IOException {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(getProperty("protocol")).append(getProperty("host")).append(getProperty("port.separator")).append(getProperty("port"))
				.append(getProperty("root.path")).append(getProperty("rest.path")).append(restServiceName);

		System.out.println(urlBuilder.toString());

		return urlBuilder.toString();
	}

	/**
	 * executes request to chosen service
	 * 
	 * @param params
	 *            list of parameters and their values
	 * @param restServiceName
	 *            service name
	 * @return Returns received response if data was found on server, otherwise null
	 * @throws IOException
	 */
	private HttpResponse executeRequest(List<NameValuePair> params, String restServiceName) throws IOException {
		UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(params, "UTF-8");
		HttpPost httpPost = new HttpPost(getURL(restServiceName));
		httpPost.setEntity(encodedEntity);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);

		return response;
	}

	/**
	 * Deserializes member from json response
	 * 
	 * @param json
	 *            json response from inTouch server
	 * @return Member object
	 */
	private Member deserializeMember(String json) {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		Gson gson = builder.create();
		Member member = gson.fromJson(json, Member.class);

		return member;
	}

	/**
	 * This method returns member in accordance to his "weight". Member weight means how much information saves in that member
	 * 
	 * @param member
	 *            Member must have login and password, otherwise runtime exception
	 * @param memberType
	 *            means member weight. This parameter can be "lightMember", "middleMember", "heavyMember".
	 * @return Member object. Default will return light member (even in case of memberType==null)
	 * @throws IOException
	 */
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

	/**
	 * Builds member object and set login and password inside it
	 * 
	 * @param login
	 *            member login
	 * @param password
	 *            member password
	 * @return member object
	 */
	private Member buildMember(String login, String password) {
		Member member = new Member();
		member.setLogin(login);
		member.setPassword(password);

		return member;
	}

	/**
	 * Light member is the member with some info and projects inside, but that projects has only id. It is good when you need to receive up to date information
	 * about member's projects. But in this case you will pay an extra traffic, because you will load full project from ProjectService every time you load
	 * member.
	 * 
	 * @param login
	 *            member login
	 * @param password
	 *            member password
	 * @return light member
	 * @throws IOException
	 */
	public Member getLightweightMember(String login, String password) throws IOException {
		return getLightweightMember(buildMember(login, password));
	}

	/**
	 * It's the same that getLightweightMember(String login, String password). The difference is that in this method you can put member object parameter and it
	 * will parse login and password from member object
	 * 
	 * @param member
	 *            member object
	 * @return light member
	 * @throws IOException
	 * @see#getLightweightMember(String login, String password)
	 */
	public Member getLightweightMember(Member member) throws IOException {
		Member loginedMember = getMember(member, Attribute.LIGHT_MEMBER);
		return loginedMember;
	}

	/**
	 * Heavy member is the member with some info and projects inside. That projects have members with some info inside and they also have some info and projects
	 * with ONLY info about that project inside. It is good choise when you want to receive authorize member once, and hold save it in in your system. But in
	 * this case you will be not receiving up to date data. If you load member once, and manager closed the project or has made changes - you will never know
	 * about it, until new request data.
	 * 
	 * @param login
	 *            member login
	 * @param password
	 *            member password
	 * @return heavy member
	 * @throws IOException
	 */
	public Member getHeavyMember(String login, String password) throws IOException {
		return getHeavyMember(buildMember(login, password));
	}

	/**
	 * It's the same that getHeavyMember(String login, String password). The difference is that in this method you can put member object and it will parse login
	 * and password from member object
	 * 
	 * @param member
	 *            member object with login and password inside
	 * @return heavy member
	 * @throws IOException
	 */
	public Member getHeavyMember(Member member) throws IOException {
		Member loginedMember = getMember(member, Attribute.HEAVY_MEMBER);
		return loginedMember;
	}

	/**
	 * It's the same that getMiddleweightMember(String login, String password). The difference is that in this method you can put member object and it will
	 * parse login and password from member object
	 * 
	 * @param member
	 *            object
	 * @return middle member
	 * @throws IOException
	 */
	public Member getMiddleweightMember(Member member) throws IOException {
		Member loginedMember = getMember(member, Attribute.MIDDLE_MEMBER);
		return loginedMember;
	}

	/**
	 * Middle member is the member with some info and projects inside. That projects have only info about themselves. This is alternative type of member, use it
	 * when you need member and just information about projects, where this member works.
	 * 
	 * @param login
	 *            member login
	 * @param password
	 *            member password
	 * @return middle member
	 * @throws IOException
	 */
	public Member getMiddleweightMember(String login, String password) throws IOException {
		return getMiddleweightMember(buildMember(login, password));
	}

	/**
	 * This method provides possibility to get member avatar from inTouch
	 * 
	 * @param login
	 *            member login
	 * @return member photo in InputStream
	 * @throws IOException
	 */
	public InputStream getPhoto(String login) throws IOException {
		Member member = new Member();
		member.setLogin(login);

		return getPhoto(member);
	}

	/**
	 * It's the same that getPhoto(String login). The difference is that in this method you can put member object and it will parse login and password from
	 * member object
	 * 
	 * @param member
	 *            member object
	 * @return member photo in InputStream
	 * @throws IOException
	 */
	public InputStream getPhoto(Member member) throws IOException {
		StringBuilder urlBuilder = new StringBuilder();

		String url = getURL(getProperty("rest.photo"));
		urlBuilder.append(url).append("?").append(Attribute.MEMBER_LOGIN).append("=").append(member.getLogin());

		return new URL(urlBuilder.toString()).openStream();
	}

}
