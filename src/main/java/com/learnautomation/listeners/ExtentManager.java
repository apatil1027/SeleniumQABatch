package com.learnautomation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.learnautomation.helper.Utility;

public class ExtentManager 
{
	public static ExtentReports extent;
	
	public static ExtentReports getInstance()
	{
		
		if(extent==null)
		{
			extent=createInstance();
			return extent;
		}
		else
		{
			return extent;
		}
	
	}

	public static ExtentReports createInstance()
	{
		ExtentSparkReporter reporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/Report_"+Utility.getCurrentDateTime() +".html");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("LearnAutomation");
		reporter.config().setReportName("Automation Report");
		
		ExtentReports extent=new ExtentReports();
		
		extent.attachReporter(reporter);
		
		return extent;
		
		
	}
	
}
