package Authentictn;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*; // given belongs to static package hence manually imported
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
public class Specbuldr {

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
		
		RequestSpecification res=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
		.addQueryParam("key", "qaclick123").setContentType("application/json").build();
		
		
		RequestSpecification t =	given().spec(res).body(loc);
		
	ResponseSpecification res3=	new ResponseSpecBuilder().expectStatusCode(200).build();
				
	Response res2=	t.when().post("maps/api/place/add/json").then().spec(res3)
			.extract().response();
		
			String  resStr= res2.asString();
			System.out.println(resStr);
				
	}

}
