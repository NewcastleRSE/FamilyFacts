package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import af.common.json.AfJSON;
import net.sf.json.JSONArray;

public class TestCallHttpList {

	public JSONArray httpURLGETCase(String apiUrl) {
		String methodUrl = apiUrl;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String line = null;
		JSONArray array = null;
		try {
			URL url = new URL(methodUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
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
