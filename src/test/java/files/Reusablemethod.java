package files;

import io.restassured.path.json.JsonPath;

public class Reusablemethod {
	
public static JsonPath rawJson(String response) {
	JsonPath js1 = new JsonPath(response);
	return js1;
}
}
