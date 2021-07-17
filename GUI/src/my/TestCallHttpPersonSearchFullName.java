package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import smx.common.json.AfJSON;

public class TestCallHttpPersonSearchFullName {
	// http://3.9.172.108:8090/api/person/search?first_name=Wilhelm%20Friederich&last_name=Henning
	// http://3.9.172.108:8090/api/person/search?first_name=Gustav Carl
	// Diederich&last_name=Henning
	// Gustav Carl Diederich,Henning
	public static void main(String[] args) {
		String first_name = "Gustav%20Carl%20Diederich";
		String last_name = "Henning";
		httpURLGETCase(first_name, last_name);
	}

	public static JSONArray httpURLGETCase(String first_name, String last_name) {
		// TODO Auto-generated method stub
		// http://3.9.172.108:8090/api/person/search?first_name=Wilhelm%20Friederich&last_name=Henning
		String methodUrl = "http://3.9.172.108:8090/api/person/search?first_name=" + first_name + "&last_name="
				+ last_name;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String line = null;
		JSONArray array = null;
		//JSONObject a = null;
		try {
			URL url = new URL(methodUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			System.out.println("qqqqqq4444444444444444444qqqqqq");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
				System.out.println("qqqqqq555555555555555555qqqqqq");
				StringBuilder result = new StringBuilder();
			
				while ((line = reader.readLine()) != null) {
					result.append(line).append(System.getProperty("line.separator"));// "\n"
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
					
					AfJSON afJson = new AfJSON();
					array = afJson.jsonToArray(ss);
		
				}
			}
			return array;
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
