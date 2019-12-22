package APITestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC7Get_ValueOffNode {
	
	@Test
	public void getRequestDetials(){
	
	try{
    
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";      // Specify base URI
		
		RequestSpecification Request = RestAssured.given();                      // get Request object
		
		Response response = Request.request(Method.GET,"/Delhi");        // get Response object
		
		JsonPath  jpath = response.jsonPath();
		
		
		
		
		String city = jpath.get("City");
		String tep = jpath.get("Temperature");
		String humidity = jpath.get("Humidity");
		String temp = jpath.get("Temperature");
		String wedesc = jpath.get("WeatherDescription");
		
		Assert.assertEquals(jpath.get("City"), "Delhi");
		
		
	}
	
   	catch (Exception e) {
		
		System.out.println("Exception:" + e);
			
		}

}
	

}
