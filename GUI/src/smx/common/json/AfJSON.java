package smx.common.json;

import java.util.List;

import org.json.JSONObject;

import my.Person;
import net.sf.json.JSONArray;

public class AfJSON {

	public JSONArray jsonToArray(String jsonStr) {
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		return jsonArray;
	}


	public static void main(String[] args) {
//		jsonToArray(null);
	}
}
