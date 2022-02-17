package Authentictn;
import static io.restassured.RestAssured.*; // given belongs to static package hence manually imported
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.Courses;
import pojo.getcourse;
import pojo.webAutomation;
public class OAuth2 {

	public static void main(String[] args) throws InterruptedException {
		
//		ChromeOptions o= new ChromeOptions();
//		o.addArguments("−−incognito");
//		DesiredCapabilities c = new DesiredCapabilities();
//		c.setCapability(ChromeOptions.CAPABILITY, o);
//		System.setProperty("webdriver.chrome.driver", "C:/Users/rashi.tiwari/git/bdd/src/test/resources/Driver/chromedriver.exe");
//		WebDriver driver = new ChromeDriver(o);
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//		
//		driver.findElement(By.cssSelector("input[type='email]")).sendKeys("rashitiwarijbp@gmail.com");
//		driver.findElement(By.cssSelector("input[type='email]")).sendKeys(Keys.ENTER);
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("input[type='password]")).sendKeys("Rashi#3161");
//		driver.findElement(By.cssSelector("input[type='password]")).sendKeys(Keys.ENTER);
//		String url = driver.getCurrentUrl();
//		String pc = url.split("code=")[1];
//		String code = pc.split("&scope")[0];
		String code="4%2F0AX4XfWgff2mP0Aq-45t__izUumEgfS3F6Q3sHgcOWE6CZ7b461DCvM1MC8Fvs-2YWwY6PQ";
		
		System.out.println(code);
			String [] ctitle= {"Selenium Webdriver Java", "Cypress", "Protractor"};
	String token=	given().urlEncodingEnabled(false).queryParams("code",code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps"
		+ ".googleusercontent.com").queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code").when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token")
		.asString();
	JsonPath atoken = new JsonPath(token);
	String acc = atoken.getString("access_token");
	System.out.println(acc);
		
		getcourse co = given().queryParam("access_token", acc ).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.as(getcourse.class);
		ArrayList<String> aa = new ArrayList();
		List<webAutomation> web= 	co.getCourses().getWebAutomation();
		for(int i =0;i<web.size();i++) {
		aa.add(web.get(i).getCourseTitle());
		
		
//		System.out.println(tit);
		
//		if((web.get(i).getCourseTitle()).equalsIgnoreCase("Cypress")) {
//			String cpr = web.get(i).getPrice();
//			System.out.println(cpr);
//		}
		}
		List<String> exp = Arrays.asList(ctitle); //returns arraylist of expected courses whicj
		// was previously stored in an array
Assert.assertTrue(aa.equals(exp));
	}
}
