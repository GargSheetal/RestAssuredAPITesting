package dataDrivenTesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {
	
	@Test(dataProvider = "empDataProvider")
	void postNewEmployees(String eName, String eSal, String eAge)
	{
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		// Created data which will be sent along with the POST request
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", eName);
		requestParams.put("salary", eSal);
		requestParams.put("age", eAge);
		
		// Adding a header stating the request body is JSON
		httpRequest.header("content-type", "application/json");
		
		// Adding JSON to the body of the data
		httpRequest.body(requestParams.toJSONString());
		
		// Post request
		Response response = httpRequest.request(Method.POST, "/create");
		
		// Capturing the response body to perform validations
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);
		
		Assert.assertEquals(responseBody.contains(eName), true);
		Assert.assertEquals(responseBody.contains(eSal), true);
		Assert.assertEquals(responseBody.contains(eAge), true);
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
	
/*	@DataProvider(name="empDataProvider")
 * 
	String [][] getEmpData()
	{
		String empData[][] = {{"abc123", "30000", "32"}, {"pqr123", "35000", "45"}, {"xyz123", "40000", "40"} };
		return empData;
	}	*/
	
	@DataProvider(name="empDataProvider")
	String [][] getEmpData() throws IOException
	{
		// Reading data from XL
		String path = System.getProperty("user.dir") + "/src/test/java/dataDrivenTesting/empTestDataRestAssured.xlsx";
		int rowCount = XLUtils.getRowCount(path, "Sheet1");
		int colCount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String empData[][] = new String[rowCount][colCount];	// creating an array to store the values coming from the XL file
		
		for(int i=1; i<=rowCount; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				empData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return empData;
	}
	
	
	

}
