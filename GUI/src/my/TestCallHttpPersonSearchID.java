package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import smx.common.json.AfJSON;

public class TestCallHttpPersonSearchID {
//  public static void main(String[] args) {
//  httpURLGETCase();
//}
	public JSONObject httpURLGETCase(String filter) {
	// TODO Auto-generated method stub
	// String methodUrl = "http://3.9.172.108:8090/api/person/search/{person_id}";
	String methodUrl = "http://3.9.172.108:8090/api/person/search/" + filter;
	HttpURLConnection connection = null;
	BufferedReader reader = null;
	String line = null;
	JSONObject a = null;
	try {
		URL url = new URL(methodUrl);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			StringBuilder result = new StringBuilder();
			
			while ((line = reader.readLine()) != null) {
				result.append(line).append(System.getProperty("line.separator"));
			}

			String s = result.toString();
			System.out.println(s);
			if (s.length() == 0) {
				return null;
			}
			int i1 = s.indexOf(':');
			int i2 = s.indexOf(':', i1 + 1);
			int i3 = s.indexOf(':', i2 + 1);
			int i4 = s.indexOf(':', i3 + 1);
			String ss = s.substring(i4 + 1, s.length() - 2);
			if (ss.length() != 0) {
				
				a = JSONObject.fromObject(ss);
				System.out.println("----------------");
				
				
			}
		}
		return a;
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.disconnect();
	}
	return null;
}

}
