package Authentictn;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*; // given belongs to static package hence manually imported
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
public class googlemap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		location loc = new location();
		lc lat = new lc();
		loc.setAccuracy(24);
		loc.setAddress("TATA goa");
		loc.setLanguage("eng");
		lat.setLat(23.45);
		lat.setLng(45.32);
		loc.setLocation(lat);
		loc.setName("Rashi");
		loc.setPhone_number("389398");
		ArrayList<String> ty = new ArrayList<>();
		ty.add("don");
		ty.add("java");
		loc.setTypes(ty);
		loc.setWebsite("www.test.com");
		
		
		
			Response res =	given().queryParam("key", "qaclick123").body(loc).
				when().post("maps/api/place/add/json").then().assertThat()
				.statusCode(200).extract().response();
			String  resStr= res.asString();
			System.out.println(resStr);
				
	}

}
