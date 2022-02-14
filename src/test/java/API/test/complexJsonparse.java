package API.test;

import org.testng.Assert;
import files.Payload;
import io.restassured.path.json.JsonPath;
public class complexJsonparse {

	public static void main(String[] args) {
		int sum =0;
JsonPath js = new JsonPath(Payload.courseprice());	
int count = js.getInt("courses.size()");
System.out.println(count);
int amount = js.get("dashboard.purchaseAmount");
System.out.println(amount);
String s = js.get("courses[0].title");
System.out.println(s);
for(int i =0; i<count;i++) {
	String title = js.get("courses["+i+"].title");
	int price = js.get("courses["+i+"].price");
	//System.out.println("total price"+price);
	System.out.println(title+price);	
}
// print no of copies of RPA
for(int i=0; i<count;i++) {
	String title = js.get("courses["+i+"].title");
	if(title.equalsIgnoreCase("rpa")) {
		int rpacopies = js.getInt("courses["+i+"].copies");
		System.out.println(rpacopies);
		break;
	}
}
for(int i=0; i<count;i++) {
		int price = js.get("courses["+i+"].price");
		int copies =  js.get("courses["+i+"].copies");
	int total = price*copies;
		System.out.println(total);
		sum = sum+total;		
		}
System.out.println(sum);
int tt = js.get("dashboard.purchaseAmount");
System.out.println(tt);
Assert.assertEquals(tt, sum);
}

	}



