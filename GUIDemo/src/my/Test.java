package my;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



///**
// * 取出逗号前面的字符
// */
//public class Test {
//    public static void main(String[] args) {
//        String string = "1,2,3,4,5";
//        //长度5 下标从0开始 到4
//        String substring = string.substring(0, string.length()-1);
//        //以逗号分割，得出的数据存到 result 里面
//        String[] result = substring.split(",");
//        for (String r : result) {
//            System.out.println("分割结果是: " + r);
//        }
//    }
//}


public class Test {
	
	
	public void ttest(String firstname, String lastname, String birth, String death, String address) {
		System.out.println(firstname); 
		System.out.println(lastname); 
		System.out.println(birth); 
		System.out.println(death); 
		System.out.println(address); 
		
	
	}
	
	public void test1(String firstname) {
		System.out.println(firstname); 

	}
	public void test2(String firstname,String lastname) {
		System.out.println(firstname); 
		System.out.println(lastname); 

	}
	
	
	
	
	
}
