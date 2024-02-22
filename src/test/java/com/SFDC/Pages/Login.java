package com.SFDC.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.SFDC.Base.BasePage;

public class Login extends BasePage{
	WebDriver driver;
	protected Logger automationTestlog=LogManager.getLogger();
	//public static BasePage bp=new BasePage();
	
	@FindBy(id="username") WebElement userName;//WebElement uName= driver.findElement(By.id("username"));
	@FindBy(id="password") WebElement userPwd;//driver.findElement(By.id("password"))
	@FindBy(id="Login") WebElement loginbtn;//WebElement loginbtn=driver.findElement(By.id("Login"));
	@FindBy(id="error") WebElement errorMsg;//WebElement errorMsg=driver.findElement(By.id("error")
	@FindBy(id="rememberUn") WebElement remUname;//WebElement remUname= driver.findElement(By.id("rememberUn"));
	@FindBy(id="forgot_password_link") WebElement forgotPwdLink;
	@FindBy(id="un") WebElement forgotPwdUName;
	@FindBy(id="continue") WebElement continueBtn;
	@FindBy(name="cancel") WebElement cancelBtn;
	@FindBy(linkText="Return to Login") WebElement returnToLoginBtn;
	@FindBy(xpath="//span[@id='idcard-identity']") WebElement remUName;
	
	
	public Login(WebDriver driver)
	{
		this.driver=driver;	
		PageFactory.initElements(driver,this);
	}
	
	public void setUserName(String uName)
	{
		userName.sendKeys(uName);
		automationTestlog.info("User name is entered.");
	}

	public void setPassword(String pwd)
	{
		userPwd.sendKeys(pwd);
		automationTestlog.info("Password is entered.");
	}
	
	public void clickLogin() throws InterruptedException
	{
		loginbtn.click();
		automationTestlog.info("Login btn is clicked");
		Thread.sleep(5000);
		
	}
	
	public void clickContinue() throws InterruptedException
	{
		continueBtn.click();
		automationTestlog.info("Continue btn is clicked");
		Thread.sleep(5000);
		
	}
	
	public void clickCancel()
	{
		continueBtn.click();
		automationTestlog.info("Continue btn is clicked");
	}
	
	public void checkrememberUserName()
	{
		remUname.click();
		automationTestlog.info("RememberUserName checkbox is checked");
		
	}
	
	public String getErrorMsg()
	{
		automationTestlog.info("Text of error message is retrieved.");
		return errorMsg.getText();	
	}
	
	public void setForgotPwdUName(String uName)
	{
		forgotPwdUName.sendKeys(uName);
		automationTestlog.info("User name is entered after clicking forgot password link.");
		
	}
	
	public void clickForgotPwd() throws InterruptedException
	{
		forgotPwdLink.click();
		automationTestlog.info("ForgotPwd link is clicked");
		Thread.sleep(4000);
	}
	
	public String getRememberedUName()
	{
		automationTestlog.info("Text of UserName  after clicking RememberMe checkbox is retrieved.");
		return remUName.getText();	
	}
	
	public void displayReturnToLogin()
	{
		if(returnToLoginBtn.isDisplayed())
		{
			automationTestlog.info("Return to Login btn is displayed");
		}
		else
		{
			automationTestlog.info("Return to Login btn is not displayed");
		}
	}
	
	
	public WebDriver login(String strUserName, String strPassword) throws InterruptedException
	{
		this.setUserName(strUserName);
		this.setPassword(strPassword);
		this.checkrememberUserName();
		this.clickLogin();
		Thread.sleep(5000);
		 automationTestlog.info("Login is successful.");
		return driver;
        //bp.waitUntilPageLoads();
       
	}
}
