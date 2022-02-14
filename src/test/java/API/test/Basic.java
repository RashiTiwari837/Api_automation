package API.test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*; // given belongs to static package hence manually imported
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
import files.Reusablemethod;
public class Basic {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// resouce and http method should be mentioned under when method, input details in given
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String newAddress = "70 winter walk, USA";
	String response =	given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json")
		.body(Payload.addplace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		// body method can be used when we just want to assert without extracting body
		.header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
	System.out.println("Response is"+ response);
	// retrive place id to use put method to update it
	JsonPath js = new JsonPath(response); // to parse JSon
	String place_id= js.getString("place_id");
	System.out.println(place_id);
	// Update place
	given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+place_id+"\",\r\n"
			+ "\"address\":\""+newAddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}")
	.when().put("maps/api/place/update/json").then().assertThat().log().all()
	.body("msg", equalTo("Address successfully updated"));
	
	// verify if the updation is correct
	String gt = given().log().all().queryParam("place_id", place_id).queryParam("key", "qaclick123")
	.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
	.extract().response().asString();
	
	JsonPath js2=Reusablemethod.rawJson(gt);
	String addrs = js2.getString("address");
	System.out.println(addrs);
	Assert.assertEquals(newAddress, addrs);
		

	
}
}
