package my;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestCallHttpPersonDelect {

	Object success;

	public void httpURLGETCase(String filter) {
		// TODO Auto-generated method stub
		// filter = "135";
		String methodUrl = "http://3.9.172.108:8090/api/person/delete/" + filter;
		HttpURLConnection connection = null;
		// BufferedReader reader = null;
		String success = null;
		String line = null;
		System.out.println("-----------------------------------------------------------");
		System.out.println(filter);
		System.out.println("-----------------------------------------------------------");
		try {

			URL url = new URL(methodUrl);

			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);

			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			connection.setInstanceFollowRedirects(true);

			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

			connection.connect();

			DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());

			dataout.flush();

			dataout.close();

			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();

			while ((line = bf.readLine()) != null) {
				sb.append(line).append(System.getProperty("line.separator"));
			}
			bf.close();
			connection.disconnect();
			System.out.println(sb.toString());

			String s = sb.toString();
			int i1 = s.indexOf(':');
			String ss = s.substring(i1 + 1, 15);
			System.out.println(ss);
			if (ss.equals("true")) {
				success = "true";
//			

			} else {
				success = "false";
//				
			}
			System.out.println(success);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			connection.disconnect();

		}
	}

}

//public void httpURLGETCase(String filter) {
//	// TODO Auto-generated method stub
//	// String methodUrl = "http://3.9.172.108:8090/api/person/search/{person_id}";
//	String methodUrl = "http://3.9.172.108:8090/api/person/search/" + filter;
//	HttpURLConnection connection = null;
//	BufferedReader reader = null;
//	String line = null;
//	try {
//		URL url = new URL(methodUrl);
//		connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
//		connection.setRequestMethod("GET");// 默认GET请求
//		connection.connect();// 建立TCP连接
//		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求
//			StringBuilder result = new StringBuilder();
//			// 循环读取流
//			while ((line = reader.readLine()) != null) {
//				result.append(line).append(System.getProperty("line.separator"));// "\n"
//			}
//
//			String s = result.toString();
//			System.out.println(s);
//
//		}
//
//	} catch (IOException e) {
//		e.printStackTrace();
//	} finally {
//		try {
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		connection.disconnect();
//	}
//}