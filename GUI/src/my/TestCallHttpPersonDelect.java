package my;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class TestCallHttpPersonDelect {
	String ss;

	public void httpURLGETCase(String filter) {

		String methodUrl = "http://3.9.172.108:8090/api/person/delete/" + filter;
		HttpURLConnection connection = null;

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

			String success = sb.toString();
			System.out.println(success);
			if (success.length() != 0) {
				int i1 = success.indexOf(':');
				int i2 = success.indexOf(',');
				ss = success.substring(i1 + 1, i2);
				System.out.println("--success后数据(字符串)---");
				System.out.println(ss);
				if (ss.equals("true")) {

					JOptionPane.showMessageDialog(null, "Delete successfully！", "prompt", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Delete Failed！！！", "prompt", JOptionPane.PLAIN_MESSAGE);
				}
			}

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