package my;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestCallHttp {
//    public static void main(String[] args) {
//        httpURLPOSTCase();
//    }
	static void httpURLPOSTCase() {
		String POST_URL = "http://3.9.172.108:8090/api/file/database/open";
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

			String parm = "database_path=/home/ec2-user/FamilyFacts/sqlite/init.db";

			dataout.writeBytes(parm);

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