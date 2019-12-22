package APITestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC6_Get_PrintAllHeader {
	
	@Test
	public void getRequestDetials(){
	
	try{
    
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";      // Specify base URI
		
		RequestSpecification Request = RestAssured.given();                      // get Request object
		Response response = Request.request(Method.GET,"/Delhi");        // get Response object
		
		//Extract body from response.
		String responsebody = response.getBody().asString();          
		
		//print response body in console window.
		System.out.println("Response body is:-"+ responsebody);
		
		//Check delhi in responsebody.
		Assert.assertEquals(responsebody.contains("Delhi"),true);
		
	}
	
   	catch (Exception e) {
		
		System.out.println("Exception:" + e);
			
		}

}
	
}