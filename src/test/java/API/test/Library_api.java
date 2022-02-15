package API.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.Reusablemethod;

import static io.restassured.RestAssured.*; // given belongs to static package hence manually imported
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Library_api {
@Test(dataProvider="testdata")
public void  addbook(String isbn, String aisle) {
	RestAssured.baseURI="https://rahulshettyacademy.com/";
	
	String response = given().header("Content-Type","application/json").
			body(Payload.libapi(isbn,aisle)).when()
	.post("Library/Addbook.php").
	then().log().all().assertThat().statusCode(200).extract().
	response().asString();
	JsonPath js1 =  Reusablemethod.rawJson(response);
	String id = js1.get("ID"); //.get provides id in string 
	System.out.println(id);
	
	//Insert delete code here
	
	
	
}
@DataProvider(name="testdata")
public Object[][] getdata() {
	return new Object [][] {{"don","568"},{"hjj","5268"},{"tyff","6588"}};
	
}

}
