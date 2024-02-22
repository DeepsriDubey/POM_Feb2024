package com.SFDC.AutomationScripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.SFDC.Base.BaseTest;
import com.SFDC.Pages.*;
import com.SFDC.Utilities.Constants;
import com.SFDC.Utilities.PropertiesUtilities;

public class AutomationScripts_Login extends BaseTest {
	
	protected Logger AutomationScriptslog=LogManager.getLogger();
	
	//public static BasePage bp=new BasePage();
	//public static Login loginPage=new Login(driver);
	
	@Test
	public static void invalidLogin_SFDC() throws InterruptedException {
		Login loginPage=new Login(driver);
		String expError = "Please enter your password.";
		String userName=PropertiesUtilities.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		loginPage.setUserName(userName);
		loginPage.setPassword(" ");
		loginPage.clickLogin();
		String getActualtext= loginPage.getErrorMsg();
		Assert.assertEquals(getActualtext , expError);

	}
	
	@Test
	public static void validLogin_SFDC() throws InterruptedException {
		Login loginPage=new Login(driver);
		String userName=PropertiesUtilities.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		String passWord=PropertiesUtilities.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");	
		//extentReport.logTestInfo("username and password extracted from properties file");
		loginPage.login(userName, passWord);
		
	}
	
	@Test
	public static void rememberUserName_chkBox() throws InterruptedException
	{
	 Login loginPage=new Login(driver);
	 //return driver= loginPage.login("ddubey@ability.com", "ability@1#");
	 String userName=PropertiesUtilities.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		String passWord=PropertiesUtilities.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");	
		//extentReport.logTestInfo("username and password extracted from properties file");
	 loginPage.login(userName, passWord);
	 Home homeObj=new Home(driver);
	 homeObj.clickHomeIcon();
	 homeObj.clickLogout();
	 String text=loginPage.getRememberedUName();
	 String expText="ddubey@ability.com";
	 Assert.assertEquals(text, expText);
	}

	@Test
	public static void testForgetPwd() throws InterruptedException 
	{
		Login loginPage=new Login(driver);
		loginPage.clickForgotPwd();
		loginPage.setForgotPwdUName("ddubey@ability.com");
		loginPage.clickContinue();
		loginPage.displayReturnToLogin();
		
	}
	
	@Test
	public static void invalidCredentials_SFDC() throws InterruptedException {
		Login loginPage=new Login(driver);
		
		String expError = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		loginPage.login("123", "22131");
		String getActualtext= loginPage.getErrorMsg();
		Assert.assertEquals(getActualtext , expError);
		
	}
}
