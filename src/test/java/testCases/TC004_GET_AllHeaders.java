
/*
 Test Case: Capture all headers
 site for practice: https://fakerestapi.azurewebsites.net/index.html
 
 */

package testCases;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_GET_AllHeaders {
	
	@Test
	public void getUserDetails()
	{
		// Specify base URI
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api";
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response object 
		Response response = httpRequest.request(Method.GET, "/v1/Users");
		
		// Print response in console
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);
		
		// Printing all headers
		Headers allHeaders = response.getHeaders(); // capture all the headers from response 
		
		for(Header header:allHeaders)
		{
			System.out.println(header.getName() + "  :  " + header.getValue());
		}
		
	}
	
	
	

}
