package APITestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC3_get_request {
	
	@Test
	public void userList(){
	
		try {
			
		
		RestAssured.baseURI="https://reqres.in";      //URI
		
		
		RequestSpecification Request = RestAssured.given();
		
		Response response = Request.request(Method.GET,"/api/users?page=2");   // get request
		
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
		Assert.assertEquals(String.valueOf(statuscode),"200");
		
		}
		catch (Exception e) {
		System.out.println("Exception:" + e);
			
		}
	}

}
