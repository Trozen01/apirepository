package APITestCases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TC5_get_Request_Axis_API {
public static ExtentHtmlReporter reporter;
public static ExtentReports extent;
public static ExtentTest logger;

	@BeforeTest
	public void Starttest() {

		System.out.println(System.getProperty("user.dir"));

	    reporter = new ExtentHtmlReporter("./Extendedreport/ExtendedReport.html");   // initialize the HtmlReporter
		
	    //initialize ExtentReports and attach the HtmlReporter
	    extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		//To add system or environment info by using the setSystemInfo method.
		extent.setSystemInfo("OS", "Windoes7");
        extent.setSystemInfo("Browser", "chrome");
        
		logger = extent.createTest("getCurrencyRate");    // name of the test case in the extend report.
	}

	@Test
	public void getCurrencyRate() {

		try {

			logger.log(Status.PASS, "Start API testing");

			RestAssured.baseURI = "http://10.10.11.90/dcc100/api/currency"; // Specify base URI

			logger.log(Status.INFO, "baseURL is:-" + RestAssured.baseURI);

			RequestSpecification request = RestAssured.given(); // get Request object

			// Specifying request payload in JSON format.
			JSONObject requestParams = new JSONObject();
			requestParams.put("RequestType", "DCC100");
			requestParams.put("BankCode", "00031");
			requestParams.put("RequestDate", "20082019");

			// Add header string on the request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the json to the body of the request (Attach above data to body)
			request.body(requestParams.toJSONString());

			// Post the request and check the response and store in response object.
			Response response = request.post("/register");

			logger.log(Status.PASS, "Post Request Successfully");

			// Print response.
			System.out.println("Response Body is:-" + response.body().asString());

			logger.log(Status.INFO, "Successfully Response generated");

			// Response status code.
			int statuscode = response.getStatusCode();
			Assert.assertEquals(statuscode, 200, "Correct Status code receive from response");
			System.out.println("Response status code:-" + statuscode);

			logger.log(Status.INFO, "Status code is:" + statuscode);

		//	JSONParser jsonParser = new JSONParser();  //Parse the JSON data present in the string format

			Object obj = new JSONParser().parse(response.body().asString()); // pass JSON string
			
			JSONObject jo = (JSONObject) obj; // Typecast to JSON object
			
			JSONArray arr = (JSONArray) jo.get("Currencies"); // store json in array. Currencies is name of the json  
			
			arr.size(); // find size of the array.
			
			for (int i = 0; i < arr.size(); i++) {
			
				JSONObject objectInArray = (JSONObject) arr.get(i);

				if ((Long) objectInArray.get("CurrencyCode") == 72)
				
				{Assert.assertEquals((Double) objectInArray.get("CurrencyRate"), 6.41,"Correct Status code receive from response");

					System.out.println("Currency Code is "+ (Long) objectInArray.get("CurrencyCode"));
					System.out.println("Currency Rate is "+ (Double) objectInArray.get("CurrencyRate"));

					logger.log(Status.PASS, "Currency code is:"+ (Long) objectInArray.get("CurrencyCode"));
					logger.log(Status.PASS, "Currency Rate is "+ (Double) objectInArray.get("CurrencyRate"));
				}

				/*
				 * System.out.println((Long) objectInArray.get("CurrencyCode"));
				 * System.out.println((Double)
				 * objectInArray.get("CurrencyRate"));
				 */
				else {
					// logger.log(Status.FAIL,
					// "Currency code not match with Json");
					// logger.log(Status.FAIL,
					// "Currency Rate not match with Json");
				}
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
    @AfterTest
	public static void endTest() {
		logger.log(Status.PASS, "TerstCase Pass Successfully");
		extent.flush();
		// this code is for auto mail from our outlook.
					try {   
					String script = "C:\\Jitu\\Work\\SoftwareTesting\\Practical\\Test\\sendmail.vbs";
					//String script = System.getProperty("user.dir")+"\\sendmail.vbs";
					// search for real path:
					String executable = "C:\\windows\\System32\\wscript.exe"; 
					String cmdArr [] = {executable, script};
					Runtime.getRuntime ().exec (cmdArr);
					
					
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
		
	}
}

// you can learn how to create extend report by using follwoing URL.
// http://learn-automation.com/extent-report-with-selenium-webdriver/

// we can make a extended report using many type i m trying with two type (aventstack)
// 1.aventstack 2.relevantcodes.

// we can add system detils also in the extend report.
//configuration items to change the look and feel  and add content, manage tests etc.

//reporter.config().setChartVisibilityOnOpen(true);
//reporter.config().setDocumentTitle("Title of the Report Comes here");
//reporter.config().setReportName("Name of the Report Comes here");
//reporter.config().setTestViewChartLocation(ChartLocation.TOP);
//reporter.config().setTheme(Theme.STANDARD);
//reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

//extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
//extent.setSystemInfo("Environment", "Automation Testing");
//extent.setSystemInfo("User Name", "Rajkumar SM");