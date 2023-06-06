package com.learnautomation.dataprovider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider 
{
	
	
	@DataProvider(name="logins")
	public static Object[][] testDataProvider()
	{
		
		Object [][]arr=ExcelReader.getDataFromSheet("login");
		
		return arr;

	}
	
	@DataProvider(name="Users")
	public static Object[][] dataProviderForUser() 
	{
		
		Object [][]arr=ExcelReader.getDataFromSheet("Users");
		
		return arr;

	}
	
	


}
