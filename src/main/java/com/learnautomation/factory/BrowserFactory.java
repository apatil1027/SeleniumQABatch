package com.learnautomation.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.learnautomation.dataprovider.ConfigReader;

public class BrowserFactory {
	
	
	static WebDriver driver;
	
	// This method will return WebDriver Instance
	public static WebDriver getBrowserInstance()
	{
		return driver;
	}
	
	
	public static WebDriver startBrowser(String browser,String url)
	{
	
		ChromeOptions opt=new ChromeOptions();
		
		if(browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("Google Chrome") || browser.equalsIgnoreCase("GC"))
		{
			if(ConfigReader.getProperty("headless").equalsIgnoreCase("true"))
			{
				opt.addArguments("--headless=new");
			}
			
			driver=new ChromeDriver(opt);
		}
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("Edge"))
		{
			driver=new EdgeDriver();
		}
		else 
		{
			System.out.println("Sorry We Do Not Support This Browser");
		}
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("pageLoad"))));
		
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("scriptTimeout"))));
		
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("implicitWait"))));
		
		
		return driver;
	}

}
