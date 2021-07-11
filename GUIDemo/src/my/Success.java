package my;

public class Success {
	
	String connect =null;
	
	public void test3(String success) {
		
		connect = success;
		System.out.println(connect); 

	}

	public Success(String connect1) {
		this.connect=connect1;
	}
	
	
	//set 方法传字符串s
	public void setconnect(String s){
		this.connect=s;
	}
	public String getS(){
	return connect;
	}
	
	
	
}
