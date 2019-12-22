package APITestCases;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC1_Get_request {
	
	@Test
	public void getrequestdetials(){
	
		try {
			
		
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";      // Specify base URI
		
		
		RequestSpecification Request = RestAssured.given();                      // get Request object
		Response response = Request.request(Method.GET,"/Hyderabad");        // get Response object
		
		//Extract body from response.
		String responsebody = response.getBody().asString();          
		
		//print response body.
		System.out.println("Response body is:-"+ responsebody);
	
		//Extract Status code.
		int statuscode = response.statusCode();
		System.out.println("Response Status code is:-"+ statuscode);

		
		//Extract status Line.
		String statusline = response.statusLine();
		System.out.println("Response Status line is:-"+ statusline);
		
		//validation for status code.
		//Assert.assertEquals(String.valueOf(statuscode),200);
		
		}
		catch (Exception e) {
		System.out.println("Exception:" + e);
			
		}
	}

}
