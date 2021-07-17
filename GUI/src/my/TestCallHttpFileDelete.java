package my;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class TestCallHttpFileDelete {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		httpURLPOSTCase();
//	}
	static String ss;

	static void httpURLPOSTCase(String database_path) {
		String POST_URL = "http://3.9.172.108:8090/api/file/database/delete" + "";
		try {
			URL url = new URL(POST_URL);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);

			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			connection.setInstanceFollowRedirects(true);

			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

			connection.connect();

			DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());

			String parm = "database_path=/home/ec2-user/FamilyFacts/sqlite/test.db";

			StringBuilder sb1 = new StringBuilder();

			sb1.append("database_path=");
			String databasepath = database_path;
			sb1.append(databasepath);

			String s1 = sb1.toString();
			System.out.println(s1);

			dataout.writeBytes(s1);

			dataout.flush();
			dataout.close();

			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			StringBuilder sb = new StringBuilder(); // 用来存储响应数据

			while ((line = bf.readLine()) != null) {
				sb.append(line).append(System.getProperty("line.separator"));
			}
			bf.close(); // 重要且易忽略步骤 (关闭流,切记!)
			connection.disconnect(); // 销毁连接
			System.out.println(sb.toString());

			String success = sb.toString();
			System.out.println(success);
			if (success.length() != 0) {
				int i1 = success.indexOf(':');
				int i2 = success.indexOf(',');
				ss = success.substring(i1 + 1, i2);// success后数据
				System.out.println("--success后数据(字符串)---");
				System.out.println(ss);
				if (ss.equals("true")) {

					JOptionPane.showMessageDialog(null, "Delete successfully！", "prompt", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Delete Failed！！！", "prompt", JOptionPane.PLAIN_MESSAGE);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
