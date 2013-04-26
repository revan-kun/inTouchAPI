package com.epam.lab.intouch.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.epam.lab.intouch.api.model.lib.SimpleMember;
import com.google.gson.Gson;

/**
 * @author Revan
 *
 */
public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		final URL url = new URL("http://localhost:8080/InTouch/rest/login?userName=member");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

		StringBuilder builder = new StringBuilder();
		
		String temp;
		while ((temp = reader.readLine()) != null) {
			builder.append(temp);
		}
		System.out.println(builder.toString());

		SimpleMember member = new Gson().fromJson(builder.toString(), SimpleMember.class);
		System.out.println(member);

	}

}
