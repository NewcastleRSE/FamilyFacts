package my;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class TestCallHttpPersonEdit {
//  public static void main(String[] args) {
//	  httpURLPOSTCase("136","Jacob","Henning","female","1633","1777","newcaslte");
//}
//  

	// http://3.9.172.108:8090/api/person/update?person_id=1&first_name=Jacob&last_name=Henning&sex=female&birth=1633&death=1704&address=newcaslte
	static String ss;

	static void httpURLPOSTCase(String filter, String firstname, String lastname, String sex, String birth,
			String death, String address) {
		String POST_URL = "http://3.9.172.108:8090/api/person/update";
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

			String parm = "person_id=1&first_name=Jacob&last_name=Henning&sex=female&birth=1633&death=1704&address=newcaslte";

			StringBuilder sb1 = new StringBuilder();

			sb1.append("person_id=");
			String person_id = filter;
			sb1.append(person_id);

			sb1.append("&first_name=");
			String first_name = firstname;
			sb1.append(first_name);

			sb1.append("&last_name=");
			String last_name = lastname;
			sb1.append(last_name);

			sb1.append("&sex=");
			String sex1 = sex;
			sb1.append(sex1);

			sb1.append("&birth=");
			String birth1 = birth;
//			Integer birth1 = null;
//			if(birth!=null){
//				birth1 = Integer.valueOf(birth);
//			}
			sb1.append(birth1);

			sb1.append("&death=");
			String death1 = death;
//			Integer death1 = null;
//			if(death!=null){
//				death1 = Integer.valueOf(death);
//			}
			sb1.append(death1);

			sb1.append("&address=");
			String address1 = address;
			sb1.append(address1);

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

			String success = sb.toString();
			System.out.println(success);
			if (success.length() != 0) {
				int i1 = success.indexOf(':');
				int i2 = success.indexOf(',');
				ss = success.substring(i1 + 1, i2);
				System.out.println("--success后数据(字符串)---");
				System.out.println(ss);
				if (ss.equals("true")) {

					JOptionPane.showMessageDialog(null, "edit successfully！", "prompt", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "edit Failed！！！", "prompt", JOptionPane.PLAIN_MESSAGE);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
