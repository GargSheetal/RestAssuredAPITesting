package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_ValidatingJSONResponse {

	@Test
	public void validateResponseBody()
	{
		// Specify base URI
				RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
				
				// Request object
				RequestSpecification httpRequest = RestAssured.given();
				
				// Response object 
				Response response = httpRequest.request(Method.GET, "/Delhi");
				
				// Print response in console
				String responseBody = response.getBody().asString();
				System.out.println("Response body is: " + responseBody);
				
				// Verifying response body contains Delhi as a city
				Assert.assertEquals(responseBody.contains("Delhi"), true);
				
				
	}
	
	
}
