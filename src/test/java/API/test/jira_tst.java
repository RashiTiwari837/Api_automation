package API.test;
import static io.restassured.RestAssured.*; // given belongs to static package hence manually imported
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;

import files.Reusablemethod;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class jira_tst {
public static void main(String[] args) {
	RestAssured.baseURI="http://localhost:8080/";
	
	SessionFilter session = new SessionFilter();
	
	String response=	given().relaxedHTTPSValidation().header("Content-Type","application/json")
			.body("{ \"username\": \"rashitiwari\", \r\n"
			+ "\"password\": \"Rashi837\" \r\n"
			+ "}").log().all().filter(session).post("/rest/auth/1/session")
			.then().log().all().extract().response()
			.asString();
	//create comment
	String addcomment = given().pathParam("id","10106").header("Content-Type", "application/json").body("{\r\n"
			+ "    \"body\": \"yellow\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}").when().filter(session).post("/rest/api/2/issue/{id}/comment")//{} considers it path parameter
		.then().log().all().assertThat().statusCode(201).extract().response()
		.asString();
	JsonPath js1 = Reusablemethod.rawJson(addcomment);
	int commen_id = js1.getInt("id");
	
	//upload file
	given().headers("X-Atlassian-Token","no-check").filter(session)
	.pathParam("id","10106").header("Content-Type", "multipart/form-data").
	multiPart("file",new File("jira.txt")).when()
	.post("/rest/api/2/issue/{id}/attachments").then().log().all().assertThat()
	.statusCode(200);
	
	//get issue details
	String details = given().filter(session).pathParam("id","10106").queryParam("fields","comment")
	.when().get("rest/api/2/issue/{id}")
	.then().log().all().extract().response().asString();
	
	JsonPath js2 = Reusablemethod.rawJson(details);
	int comment_num=js2.get("fields.comment.comments.size()");
	
	for(int i =0; i<comment_num; i++) {
		int cmnt = js2.getInt("fields.comment.comments["+i+"].id");
//		System.out.println(cmnt);
		if(cmnt==commen_id) {	
			String comntcontent =js2.get("fields.comment.comments["+i+"].body");
			System.out.println(comntcontent);
			Assert.assertEquals("yellow", comntcontent);
			break;
		}
	}
}
	
}
