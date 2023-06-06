package com.learnautomation.framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.learnautomation.dataprovider.CustomDataProvider;
import com.learnautomation.helper.BaseClass;
import com.learnautomation.pages.LoginPage;

public class LoginTest extends BaseClass {

	
	LoginPage login;

	@Test(dataProvider = "logins", dataProviderClass = CustomDataProvider.class)
	public void invaidLoginApplication(String email, String pass) 
	{
		login = new LoginPage(driver);

		login.loginToApplication(email, pass);

		boolean status = login.captureErrorMessage().contains("USER Email Doesn't Exist");

		Assert.assertTrue(status, "Message could not verify");
	}



}
