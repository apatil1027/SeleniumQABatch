package com.learnautomation.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.learnautomation.dataprovider.ConfigReader;

public class Utility 
{

	public static String getCurrentDateTime()
	{
		return new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
	}

	public static WebElement elementHighlight(WebDriver driver,WebElement ele)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px red; background: yellow');",ele);
		
		wait(1);
	
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');",ele);
		
		return ele;
		
	}

	public static void captureScreenShotOfWebElement(WebDriver driver,WebElement ele)
	{
		try 
		{
			FileHandler.copy(ele.getScreenshotAs(OutputType.FILE), new File("./screenshots/selenium"+System.currentTimeMillis()+".png"));
			
		} catch (IOException e) {
			
			System.out.println("Could not save the screenshot");
		}
	}
	
	public static void captureScreenShot(WebDriver driver)
	{
		try 
		{
			FileHandler.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File("./screenshots/selenium"+System.currentTimeMillis()+".png"));
			
		} catch (IOException e) {
			
			System.out.println("Could not save the screenshot");
		}
	}
	
	public static String captureScreenShotInBase64(WebDriver driver)
	{
		String screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		
		return screenshot;
	}
	
	
	public static void wait(int time)
	{
		
		try 
		{
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			
		}
		
	}
	
	
	public static WebElement getElement(WebDriver driver,By locator,int time)
	{
			WebElement element=waitForWebElement(driver, locator, time);
			
			if(ConfigReader.getProperty("highlightElement").contains("true")) 
			{
			
				return elementHighlight(driver,element);
				
			}
			else
			{
				return element;
			}
	}

	public static WebElement waitForWebElement(WebDriver driver,By locator,int time)
	{
		
		WebElement element = null;
		
		for(int i=0;i<time;i++)
		{
			
				try 
				{
					element=driver.findElement(locator);
					if(element.isDisplayed() && element.isEnabled())
					{
						break;
					}
				} catch (NoSuchElementException e)
				{
					System.out.println("Element not present - Waiting ... !!!");
					wait(1);
					
				}
				catch (Exception e) {
					System.out.println("Element not present - Waiting ... !!!");
					wait(1);
				}
			
		}
		

		return element;
	}
	
	
	
	
	public static Alert waitForAlert(WebDriver driver,int time)
	{
		Alert alt=null;
		
		for(int i=0;i<time;i++)
		{
			
			
			try 
			{
				alt=driver.switchTo().alert();
				
				System.out.println("Alert found");
				
				break;
				
			} catch (NoAlertPresentException e) 
			{
				System.out.println("Alert Not Found - Trying again...!!!");
				
				wait(1);
				
			}
	
		}
			
		return alt;
			
	}
	
	
	public static void clickElement(WebDriver driver,WebElement ele)
	{
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(ele));
		
		System.out.println("LOG:INFO - Element is ready to click");
		
		try 
		{
			element.click();
			
		} catch (Exception e) 
		{
			System.out.println("LOG:INFO - WebElement Click Failed --- Trying With JS Click");
			
			try 
			{
				JavascriptExecutor js=(JavascriptExecutor)driver;
				
				js.executeScript("arguments[0].click()", element);
			} 
			catch (Exception e1) 
			{
				System.out.println("LOG:INFO - JS Click Failed --- Trying With Actions Class Click");
				
				new Actions(driver).moveToElement(element).click().build().perform();
				
				
			}
			
			
		}
		
	}
	
	

	public static void clickElement(WebDriver driver,By ele)
	{
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(ele));
		
		System.out.println("LOG:INFO - Element is ready to click");
		
		try 
		{
			element.click();
			
		} catch (Exception e) 
		{
			System.out.println("LOG:INFO - WebElement Click Failed --- Trying With JS Click");
			
			try 
			{
				JavascriptExecutor js=(JavascriptExecutor)driver;
				
				js.executeScript("arguments[0].click()", driver.findElement(ele));
			} 
			catch (Exception e1) 
			{
				System.out.println("LOG:INFO - JS Click Failed --- Trying With Actions Class Click");
				
				new Actions(driver).moveToElement(driver.findElement(ele)).click().build().perform();
				
				
			}
			
			
		}
		
	}
	
	
	
	
	/*
	 * 	
	 * 
	 */
	
	
	
	/*
	 * 	This method will fetch all values from list and store in WebElement
	 * 	It performs click method based on condition
	 * 
	 * 	@param - driver - Pass the driver from main class/test
	 *  @param - xpath value- which will match all elements
	 *  @param - condition or value to search
	 *   
	 */
	public static void selectValueFromList(WebDriver driver,String xpathLocator,String valueToSearch)
	{
		
		List<WebElement> allOptions=driver.findElements(By.xpath(xpathLocator));
		
		for(WebElement ele:allOptions)
		{
					if(ele.getText().contains(valueToSearch))
					{
						ele.click();
						break;
					}
		}
	}
	
	/*
	 * 	This method will fetch all values from list and store in WebElement
	 * 	It performs click method based on condition
	 * 
	 * 	@param - driver - Pass the driver from main class/test
	 *  @param - By class reference
	 *  @param - condition or value to search
	 *   
	 */
	
	public static void selectValueFromList(WebDriver driver,By locator,String valueToSearch)
	{
		
		List<WebElement> allOptions=driver.findElements(locator);
		
		for(WebElement ele:allOptions)
		{
					if(ele.getText().contains(valueToSearch))
					{
						ele.click();
						break;
					}
		}
	}

}
