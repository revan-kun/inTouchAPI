package main.java.com.epam.lab.intouch.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.epam.lab.intouch.api.model.member.Member;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class MemberService {
	public Member login(Member member) throws ParseException, IOException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("userlogin", member.getLogin()));
		params.add(new BasicNameValuePair("password", member.getPassword()));
		
		UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(params, "UTF-8");
		HttpPost httpPost = new HttpPost("http://localhost:8080/rest/login");
		httpPost.setEntity(encodedEntity);
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse response=client.execute(httpPost);
		
		HttpEntity gsonEntity = response.getEntity();
		String gson=EntityUtils.toString(gsonEntity, "UTF-8");
		
		Member loginedMember = new Gson().fromJson(gson, Member.class);
		
		EntityUtils.consume(gsonEntity);
		
		return loginedMember;

	}
}
