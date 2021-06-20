package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import af.common.json.AfJSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestCallHttpPersonSearch {

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
		connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
		connection.setRequestMethod("GET");// 默认GET请求
		connection.connect();// 建立TCP连接
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求
			StringBuilder result = new StringBuilder();
			// 循环读取流
			while ((line = reader.readLine()) != null) {
				result.append(line).append(System.getProperty("line.separator"));// "\n"
			}

			String s = result.toString();
			System.out.println(s);//输出为“{”---所以需要转换成Object
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
