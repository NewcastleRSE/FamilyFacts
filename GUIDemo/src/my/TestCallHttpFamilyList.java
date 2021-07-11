package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class TestCallHttpFamilyList {

	public static void main(String[] args) {
		httpURLGETCase("135");
	}

	public static JSONObject httpURLGETCase(String filter) {
		// String filter
		// TODO Auto-generated method stub
		// String methodUrl = "http://3.9.172.108:8090/api/person/search/{person_id}";
		// String methodUrl = "http://3.9.172.108:8090/api/family/" + filter;
		// String methodUrl = "http://3.9.172.108:8090/api/family/135 ";
		String methodUrl = "http://3.9.172.108:8090/api/family/" + filter;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String line = null;
		JSONObject a = null;

//	JSONObject fatherlistpersonvoObject = null;
//	JSONObject fatherObject =null;

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
				System.out.println(s);// 输出为“{”---所以需要转换成Object
				if (s.length() == 0) {
					return null;
				}
				int i1 = s.indexOf(':');
				int i2 = s.indexOf(':', i1 + 1);
				int i3 = s.indexOf(':', i2 + 1);
				int i4 = s.indexOf(':', i3 + 1);
				String ss = s.substring(i4 + 1, s.length() - 2);
				System.out.println("--data后数据(字符串)---");
				System.out.println(ss);

				if (ss.length() != 0) {

					a = JSONObject.fromObject(ss);
					System.out.println("--+——+—以上是TestCallHttpFamilyList—+——+——+---");

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
