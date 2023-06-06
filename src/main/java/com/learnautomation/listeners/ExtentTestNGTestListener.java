package com.learnautomation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.learnautomation.dataprovider.ConfigReader;
import com.learnautomation.factory.BrowserFactory;
import com.learnautomation.helper.Utility;

public class ExtentTestNGTestListener implements ITestListener {


	ExtentReports extent=ExtentManager.getInstance();
	
	ThreadLocal<ExtentTest> parentTest=new ThreadLocal<ExtentTest>();
	
	ExtentTest extentTest;
	
	public synchronized void onTestStart(ITestResult result) {
		
		System.out.println("LOG:INFO - Test Created In Report");
		
		extentTest =extent.createTest(result.getName());
		
		parentTest.set(extentTest);
		
	  }
	
	public synchronized void onTestSuccess(ITestResult result) 
	{
		System.out.println("LOG:INFO - Test Executed Successfully");
		
		if(ConfigReader.getProperty("screenShotOnSuccess").contains("true"))
		{
			String screenshot=Utility.captureScreenShotInBase64(BrowserFactory.getBrowserInstance());
			parentTest.get().log(Status.PASS, "Test Passed",MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		}
		else
		{
			parentTest.get().log(Status.PASS, "Test Passed");
		}
		
	
		System.out.println("LOG:INFO - Appending Test Result To Report");
		
	  }
	
	public synchronized void onTestFailure(ITestResult result) 
	{
		System.out.println("LOG:INFO - Test Failed");
		
		String screenshot=Utility.captureScreenShotInBase64(BrowserFactory.getBrowserInstance());
		
		parentTest.get().log(Status.FAIL,result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		
		System.out.println("LOG:INFO - Appending Test Result To Report");
	  }
	
	public synchronized void onTestSkipped(ITestResult result) 
	{
		System.out.println("LOG:INFO - Test Skipped");
		
		if(ConfigReader.getProperty("screenShotOnSuccess").contains("true"))
		{
			String screenshot=Utility.captureScreenShotInBase64(BrowserFactory.getBrowserInstance());
			parentTest.get().log(Status.SKIP, "Test Skipped",MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		}
		else
		{
			parentTest.get().log(Status.SKIP, "Test Skipped");
		}
		

		System.out.println("LOG:INFO - Appending Test Result To Report");
	  }

	public synchronized void onFinish(ITestContext context) {
	   
		extent.flush();
		System.out.println("LOG:INFO - Writing all result to report");
		
	  }
}
