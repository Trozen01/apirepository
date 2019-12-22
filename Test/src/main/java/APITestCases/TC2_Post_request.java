package APITestCases;

//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC2_Post_request {

	@Test
	public void registerCustomer(){
		
		try {
			
				
		RestAssured.baseURI="http://restapi.demoqa.com/customer";       // Specify base URI
		
		RequestSpecification request =RestAssured.given();              // get Request object
		
		
		//Specifying request payload in JSON format.
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "jitu");
		requestParams.put("LastName", "Sawant");
		requestParams.put("UserName", "jitu");
		requestParams.put("Password", "jitu123");
		requestParams.put("Email", "jitu@gmail.com");

        
		//Add header string on the request body is a JSON
		request.header("Content-Type","application/json");
        
		//Add the json to the body of the request (Attach above data to body)
        request.body(requestParams.toJSONString());
        
        //Post the request and check the response.
        Response response = request.post("/register");
        
        //Print response.
        System.out.println("Response Body is:-"+ response.body().asString());
        
        //Response status code.
        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode, 201);
        System.out.println("Response status code:-" + statuscode);
        
        //Validate Response success code.
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode, "OPERATION_SUCCESS");
        
		} catch (Exception e) {
			
			System.out.println("Exception:" + e);
		}
        
	}
}
