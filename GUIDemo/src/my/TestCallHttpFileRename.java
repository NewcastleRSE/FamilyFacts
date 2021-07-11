package my;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestCallHttpFileRename {

	static void httpURLPOSTCase(String old_name, String new_name, String database_path) {
		String POST_URL = "http://3.9.172.108:8090/api/file/database/rename";
		try {
			URL url = new URL(POST_URL);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中

			connection.setDoOutput(true);

			connection.setDoInput(true);

			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			connection.setInstanceFollowRedirects(true);

			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

			connection.connect();

			DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());

			String parm = "old_name=test&new_name=demo&database_path=/home/ec2-user/FamilyFacts/sqlite/";

			StringBuilder sb1 = new StringBuilder();

			sb1.append("old_name=");
			String oldname = old_name;
			sb1.append(oldname);

			sb1.append("&new_name=");
			String newname = new_name;
			sb1.append(newname);

			sb1.append("&database_path=");
			String databasepath = database_path;
			sb1.append(databasepath);

			String s1 = sb1.toString();
			System.out.println(s1);

			dataout.writeBytes(s1);

			dataout.flush();
			dataout.close();

			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			StringBuilder sb = new StringBuilder();

			while ((line = bf.readLine()) != null) {
				sb.append(line).append(System.getProperty("line.separator"));
			}
			bf.close();
			connection.disconnect();
			System.out.println(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
