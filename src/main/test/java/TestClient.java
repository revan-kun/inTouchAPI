package main.test.java;

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

/**
 * @author Revan
 * 
 */
public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("userlogin", "test1"));
		params.add(new BasicNameValuePair("password", "1111"));

		UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(params, "UTF-8");
		HttpPost httpPost = new HttpPost("http://localhost:8080/rest/login");
		httpPost.setEntity(encodedEntity);

		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);

		HttpEntity gsonEntity = response.getEntity();
		String gson = EntityUtils.toString(gsonEntity, "UTF-8");

		System.out.println(gson);

	}

}
