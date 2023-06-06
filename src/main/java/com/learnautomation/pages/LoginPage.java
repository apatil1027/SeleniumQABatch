package com.learnautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.learnautomation.helper.Utility;

public class LoginPage 
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By email=By.id("email1");
	
	By password=By.id("password1");
	
	By loginButton=By.className("submit-btn");
	
	By errorMessage=By.xpath("//h2[@class='errorMessage']");
	
	By logo=By.xpath("//img[@alt='Login']");
	
	By socialMedia=By.xpath("//div[@class='social']//div[@class='social-btns']//a");
	
	public void loginToApplication(String emailAddress,String passwordField)
	{
		Utility.getElement(driver, email,10).sendKeys(emailAddress);
		Utility.getElement(driver, password,10).sendKeys(passwordField);
		Utility.getElement(driver, loginButton,10).click();

	}
	
	public String captureErrorMessage()
	{
		return driver.findElement(errorMessage).getText();
	}
	
	public boolean isLogoPresent()
	{
		return driver.findElement(logo).isDisplayed();
	}
	
	public int getSocialMediaCount()
	{
		return driver.findElements(socialMedia).size();
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
