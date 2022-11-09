package testCases;

/*
https://demoqa.com/utilities/weather/city

Request Type: GET

http://restapi.demoqa.com/utilities/weather/city/Hyderabad

SUCCESS RESPONSE
{
"City":"Hyderabad",
"Temperature":"67 Degree celsius",
"Humidity":"138 Percent",
"Weather Description":"scattered clouds",
"Wind Speed":"24 Km per hour",
"Wind Direction degree":"39 Degree"
}

STATUS CODE : 200
Status Line: HTTP/1.1 200 OK"

 */

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {
	
	@Test
	public void getWeatherDetails()
	{
		
		// Specify base URI
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";		
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response Object
		Response response = httpRequest.request(Method.GET, "/Hyderabad");
		
		// Printing response in console window
		String responseBody = response.getBody().asString();	// getting response body and converting the json body to string format
		System.out.println("Response Body is: " + responseBody);
		
		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// Status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Status line is: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	

}
