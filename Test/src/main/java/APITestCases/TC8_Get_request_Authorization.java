package APITestCases;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;

public class TC8_Get_request_Authorization {
	
	
	public void Authorization(){
		
		try{
		
			RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";      // Specify base URI
			
			
			//basic Authentication
			PreemptiveBasicAuthScheme authscheem = new PreemptiveBasicAuthScheme(); 
			authscheem.setUserName("ToolsQA");
			authscheem.setPassword("TestPassword");
			
			RestAssured.authentication=authscheem;
			
			
			RequestSpecification Request = RestAssured.given();                      // get Request object
			Response response = Request.request(Method.GET,"/");                    // get Response object
			
			//Extract body from response.
			String responsebody = response.getBody().asString();          
			
			//print response body.
			System.out.println("Response body is:-"+ responsebody);
		
			//Extract Status code.
			int statuscode = response.statusCode();
			System.out.println("Response Status code is:-"+ statuscode);

			//validation for status code.
			Assert.assertEquals(String.valueOf(statuscode),200);
			
			//Extract status Line.
			String statusline = response.statusLine();
			System.out.println("Response Status line is:-"+ statusline);
			
			
			
		}
		
		catch(Exception e){
			
			
		}
	}

}
