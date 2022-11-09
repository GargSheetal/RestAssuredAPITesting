
/*
 Test Case: Register Customer API

Request Type: POST

https://demoqa.com/customer/register

BODY

{
   “FirstName” : “value”
   “LastName” : “value”,
   “UserName” : “value”,
   “Password” : “value”,
   “Email”    : “Value”
 }

SUCCESS RESPONSE

{
“SuccessCode”: “OPERATION_SUCCESS”,
“Message”: “Operation completed successfully”
}

STATUS CODE : 201

*/


package testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {
	
	@Test
	void RegistrationSuccessful()
	{
		//Specify base URI
		RestAssured.baseURI = "https://reqres.in/api";
		
		// Create Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Request Payload sending along with POST request
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", "morpheus");
		requestParams.put("job", "leader");
//		requestParams.put("UserName", "JohnABC");
//		requestParams.put("Password", "JohnABC123");
//		requestParams.put("Email", "JohnABC@gmail.com");

		// Specifying the type of content in the payload
		httpRequest.header("Content-type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());	// converting the parameters in the body to json format and attaching to the request
		
		// Response object
		Response response = httpRequest.request(Method.POST, "/users");
		
		// Print response 
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);
		
		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is: " + statusCode);
		Assert.assertEquals(statusCode, 201);
		
		// Success code validation
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println("Success Code is: " + successCode);
		Assert.assertEquals(successCode, "null");
		
		
		
	}

}
