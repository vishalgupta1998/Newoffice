package Tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import ScoutAPI.RestAssuredDemo.Base;
import ScoutAPI.RestAssuredDemo.payloads;
import Utilities.Assertions;
import Utilities.Reporting;
import io.restassured.RestAssured;

public class LoadProductionChartAPI {
	static ExtentTest test;
	@Test(dataProvider="data")
	public static void LoadProductionChart(Object AccountId, Object UserId, Object ChartType, Object StartDate, Object EndDate, int statuscode,Map<String,Object> a,Object y) throws Exception {
		
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		RestAssured.baseURI = Base.Propertis("baseURI");
		

		String response = given().log().all().header("Content-Type", Base.Propertis("Content-Type")).header("access_token",Base.Propertis("access_token")).body(payloads.LoadProductionChart(AccountId,  UserId,  ChartType,  StartDate,  EndDate))
				.when().post("api/report/LoadProductionChart")
				.then().log().all().assertThat().statusCode(statuscode).body("scope", equalTo(null)).extract().response().asString();
		System.out.println(response);
//		String hey=Base.JsonConverter(response,"Message");
//		System.out.println(hey);
		
		/*Base.Assert(Base.JsonConverter(response,""+AssertionElement+""), ""+AssertionElementExpected+"");*/
		//41729, 0, 2, "", ""
		for (Map.Entry<String, Object> pair : a.entrySet()) {
			   //System.out.println(pair.getKey(), pair.getValue());   
			String s=pair.getKey();
			String l=String.valueOf(pair.getValue());
			Base.Assert(Base.JsonConverter(response,""+s+""), ""+l+"");
			}

	}
	@DataProvider
	public Object[][] data()
	{
		Object[][] test={
				{null, 58779, null, null, null, 400,Assertions.LoadProduction("m1"),"First"}
		,{41728,    null, 1, "", "",200,Assertions.LoadProduction("m2"),"Second"},
		{41728,    587, 1, "", "",400,Assertions.LoadProduction("m3"),"Third"},
		{41728,     58779, 1, "2021-01-06T22:35:46.03:00", "2021-02-07T22:35:46.0327652-07:00",200,Assertions.LoadProduction("m4"),"Fourth"}};
		
		return test;
	}

}

