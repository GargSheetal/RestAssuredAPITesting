package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_ExtractValuesOfEachNodeInJSON {
	
	@Test
	public void validateEachNodeInJSON()
	{
		// Specify base URI
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response object 
		Response response = httpRequest.request(Method.GET, "/Delhi");

		JsonPath jsonpath = response.jsonPath();	// get the jsonPath
		System.out.println(jsonpath.getString("City"));
		System.out.println(jsonpath.getString("Temperature"));
		System.out.println(jsonpath.getString("Humidity"));
		System.out.println(jsonpath.getString("'Weather Description'"));
		System.out.println(jsonpath.getString("'Wind Speed'"));
		System.out.println(jsonpath.getString("'Wind Direction degree'"));
		
		// Validating values of jsonPath
		Assert.assertEquals(jsonpath.getString("'Weather Description'"), "scattered clouds");
	}

}
