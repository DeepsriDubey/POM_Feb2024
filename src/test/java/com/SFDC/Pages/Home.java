package com.SFDC.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SFDC.Base.BasePage;

public class Home extends BasePage {
	WebDriver driver;
	protected Logger automationTestlog=LogManager.getLogger();
	
	@FindBy(xpath="//input[@id='phSearchInput']") WebElement searchBox;
	@FindBy(id="userNav-arrow") WebElement homeIcon;
	@FindBy(xpath="//a[@title='Logout']") WebElement logout;
	
	public Home(WebDriver driver)
	{
		this.driver=driver;	
		PageFactory.initElements(driver,this);
	}
	
	public void displaySearchInputBox()
	{
		if(searchBox.isDisplayed())
		{
			automationTestlog.info("Search Input btn is displayed");
		}
		else
		{
			automationTestlog.info("Search Input btn is not displayed");
		}
	}
	
	public void clickHomeIcon()
	{
		homeIcon.click();
		automationTestlog.info("Home Icon is clicked");
	}
	
	public void clickLogout() throws InterruptedException
	{
		logout.click();
		automationTestlog.info("Logout is clicked");
		Thread.sleep(5000);
	}
	
}
