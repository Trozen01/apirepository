package APITestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC4_Get_Request_GoogleMap {
	
	@Test
	public void googleMapTest(){
		
		try {
			
			
			RestAssured.baseURI="http://maps.googleapis.com";      // Specify base URI
			
			RequestSpecification Request = RestAssured.given();                      // get Request object
			Response response = Request.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=33.8670522.151.1957362&radius=1500&type=supermarket&key=AlzaSyBjGCE3VpLU4lgtqSTDmHmJ2HoELb4JyIs");        // get Response object
			
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
			Assert.assertEquals(statuscode, 200);
			
			}
			catch (Exception e) {
			System.out.println("Exception:" + e);
				
			}
		}
	

}
