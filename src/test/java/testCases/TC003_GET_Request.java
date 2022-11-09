
/*
 Test Case: Validating Headers

https://fakerestapi.azurewebsites.net/api/v1/Books


"Headers:
Content type is: application/json; charset=utf-8; v=1.0
Content type is: chunked
"
 */


package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Request {
	
	@Test
	void fakeRestAPIBookTest()
	{
		//Specify Base URI
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api";
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response Object
		Response response = httpRequest.request(Method.GET, "/v1/Books");
		
		// Printing the Response body
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);
		
		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// Capture details of Headers from response
		String contentType = response.getHeader("content-type");  // capture details of content-type header
		System.out.println("Content type is: " + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8; v=1.0"); 
		
		String transferEnco = response.getHeader("transfer-encoding");  // capture details of transfer-encoding header
		System.out.println("Content type is: " + transferEnco);
		Assert.assertEquals(transferEnco, "chunked"); 

		
	}

}
