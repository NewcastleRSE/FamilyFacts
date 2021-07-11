package my;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestCallHttpUnlinkFather {

	static void httpURLPOSTCase(String personid, String fatherid) {
		String POST_URL = "http://3.9.172.108:8090/api/person/unlink/father";
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

			String parm = "person_id=135&father_id=136";

			StringBuilder sb1 = new StringBuilder();

			sb1.append("person_id=");
			String person_id = personid;
			sb1.append(person_id);

			sb1.append("&father_id=");
			String father_id = fatherid;
			sb1.append(father_id);

			String s = sb1.toString();
			System.out.println("------------------------");
			System.out.println(s);
			System.out.println("------------------------");

			dataout.writeBytes(s);

			dataout.flush();
			dataout.close();

			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			StringBuilder sb = new StringBuilder();
			System.out.println("--+++++++++++++++++----");

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
