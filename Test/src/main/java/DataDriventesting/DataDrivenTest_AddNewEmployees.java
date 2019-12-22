package DataDriventesting;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest_AddNewEmployees {
	
	
	@Test(dataProvider = "empdataprovider")
	public void addemployee(String fname, String lname, String Uname, String Pass, String email){
		
	    RestAssured.baseURI="http://restapi.demoqa.com/customer";       // Specify base URI
		
		RequestSpecification request =RestAssured.given();              // get Request object
		
		JSONObject requestParams = new JSONObject();   //Hear we create data which we can send with the post request.
		requestParams.put("FirstName", fname);
		requestParams.put("LastName", lname);
		requestParams.put("UserName", Uname);
		requestParams.put("Password", Pass);
		requestParams.put("Email", email);
		
		//Add header string on the request body is a JSON
		request.header("Content-Type","application/json");
		
		//Add the json to the body of the request (Attach above data to body)
        request.body(requestParams.toJSONString());
        
        //Post the request and check the response.
        Response response = request.post("/register");
        
        String Responsebody = response.body().asString();
        
        System.out.println("Response body is: "+Responsebody);
        
        Assert.assertEquals(Responsebody.contains("jitu"), true);
        Assert.assertEquals(Responsebody.contains("Sawant"), true);
        Assert.assertEquals(Responsebody.contains("jitu123"), true);
        Assert.assertEquals(Responsebody.contains("jitu@gmail.com"), true);
        
        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode, 200);
	}

	@DataProvider(name ="empdataprovider")
	 String [] [] getEmpdata() throws IOException
	{
		//read data from Excel.
		
		System.out.println(System.getProperty("user.dir"));
		
		//D:\jitu\selenium project\Test
		
		String path = System.getProperty("user.dir")+"\\src\\main\\java\\DataDriventesting\\EmpData.xlsx";
		
		
		int rownum=XLUtil.getRowCount(path,"Sheet1");
		int colcount =XLUtil.getCellCount(path, "Sheet1", 1);
		
		String empdata [] [] = new String [rownum] [colcount];
		
	 for(int i=1; i <=rownum; i++){
		 
		 for(int j=0; j<colcount; j++){
			 
			 empdata[i - 1][j] = XLUtil.getCelldata(path, "Sheet1", i, j);
		 }
		 
	 }
	
		//String empdata [][] = {{"Jitu1","sawant1","jitu1","Jitu1234","jitu1@gmail.com"}, {"Jitu2","sawant2","jitu2","Jitu1235","jitu2@gmail.com"}};
	    return(empdata);	
	}
}
