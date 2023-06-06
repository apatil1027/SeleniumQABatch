package com.learnautomation.helper;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.learnautomation.dataprovider.ConfigReader;
import com.learnautomation.factory.BrowserFactory;

public class BaseClass {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void startApplication()
	{
		System.out.println("LOG:INFO - Running Before Method - Application Getting Ready");
		driver=BrowserFactory.startBrowser(ConfigReader.getProperty("browser"),ConfigReader.getProperty("url"));

	}
	
	@AfterMethod
	public void closeApplication()
	{
		driver.quit();
		System.out.println("LOG:INFO - Running After Method - Application Closed");
	}

}
