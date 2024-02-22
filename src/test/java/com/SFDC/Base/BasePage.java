package com.SFDC.Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SFDC.Utilities.ExtentReportsUtility;
import com.google.common.io.Files;

public class BasePage {
	protected static WebDriver driver;
	protected Wait<WebDriver> wait;
	protected Logger baseTestlog=LogManager.getLogger();
	protected ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();

	
	public void enterText(WebElement ele, String data, String objectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			System.out.println("Data is entered in " + objectName + " textbox");
		} else {
			System.out.println(objectName + " element is not displayed");
		}
	}
	
	public void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			System.out.println(objectName + "button is clicked");
			
		} else {
			System.out.println(objectName+" element is not enabled");
			
		}
	}
	
	public String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		System.out.println("Text is extracted from "+objectName);
		return data;
	}
	
	/*public void closeBrowser() {
		driver.close();
		System.out.println("Current Browser instance is closed");
		driver=null;
	}
	
	
	public void maximiseBrowser() {
		driver.manage().window().maximize();
		//baseTestlog.info("browser window has maximized");
	}

	
	public void quitBrowser() {
		driver.quit();
		baseTestlog.info("all browser closed");
		driver=null;
		
	}
	*/
	
	public String getPageTitle() {
		return driver.getTitle();
	}

	public void refreshPage() {
		driver.navigate().refresh();
		baseTestlog.info("page is refreshed");

	}

	public void clearElement(WebElement ele, String ObjectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			baseTestlog.info(ObjectName + " is cleared");
		} else {
			baseTestlog.info(ObjectName + " element is not displayed");
		}
	}
	
		public Alert switchToAlert() 
		{
        Alert al= driver.switchTo().alert();
        baseTestlog.info("current window is switched to alert "+al);
        return al;
	    }
		
		public void AcceptAlert(Alert alert) {

			alert.accept();
			baseTestlog.info("Alert has been accepted.");

		}
		
		public String getAlertText(Alert alert, String objectname) {
			
			String alertText= alert.getText();
			baseTestlog.info("Alert is displayed as: "+ alertText);
			return alertText;
			

		}
		
		public void dismisAlert(Alert alert) {

			alert.dismiss();
			baseTestlog.info("Alert has been dismissed.");

		}
		
		

	

	/* ****************handling alerts reusable methods*************************-Implemented

	public Alert switchToAlert() {

		
	}

	public void AcceptAlert(Alert alert) {

		

	}

	public String getAlertText(Alert alert, String objectname) {
		

	}

	
	// ******************************alert ends**************************************

	// ******************************Action class reusable methods**************************************
	

	public void mouseOverOnElement(WebElement ele, String objName) {
		
	}

	public void ContextClickOnElement(WebElement ele, String objName) {
		
	}

	// ******************************Action class reusable method ends**************************************

	// ******************************Select class reusable method starts*************************************

	public void selectByTextData(WebElement element, String text, String objName) {
		

	}

	public void selectByIndexData(WebElement element, int index, String objName) {
		

	}

	public void selectByValueData(WebElement element, String text, String objName) {
		
	}
	
	public WebElement selectFromListUsingText(List<WebElement> list, String text) {
		

	}

	// ******************************select class reusable method ends**************************************
*/
	// ******************************wait reusable method starts**************************************

	public void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		baseTestlog.info("Waiting until page loads with maximum of 20 seconds.");
		
	}
	public void waitForVisibility(WebElement ele, int time, int pollingtime, String objectName) {
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofSeconds(pollingtime)).
		withMessage(objectName+"is not visible-fluent wait time expires.");
		
	}

	public void WaitUntilPresenceOfElementLocatedBy(By locator, String objName) {
		wait= new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		baseTestlog.info("Waiting for a webelement for visibility.");

	}

	public void waitUntilElementToBeClickable(By locator, String objName) {
		
		wait= new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		baseTestlog.info("Waiting for a webelement"+ objName+" to be clickable.");
	}

	public void waitForVisibility(WebElement ele, int time, String objectName) {
		
		wait= new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(ele));
		baseTestlog.info("Waiting for a webelement to be visible.");
		
	}

	public void waitForAlertPresent(int time) {
		wait= new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.alertIsPresent());
		baseTestlog.info("Waiting for an alert to be visible.");
	}
	
public void mouseOverOnElement(WebElement ele, String objName) {
	
	Actions actions= new Actions(driver);
	actions.moveToElement(ele).build().perform();
	baseTestlog.info("Mouse over has been performed for "+ objName);
		
	}

public void takescreenshots(String filepath)
{
TakesScreenshot screenCapture=	(TakesScreenshot)driver;
File src=screenCapture.getScreenshotAs(OutputType.FILE);
File destination=new File(filepath);
try
{
	Files.copy(src, destination);
	baseTestlog.info("captured the screen");
	}
	catch(IOException e)
	{
		e.printStackTrace();
		baseTestlog.info("went wrong when capturing the screen");
	}
}

public void takescreenshot(WebElement element, String filepath)	
{
	File src=element.getScreenshotAs(OutputType.FILE);
	File destination=new File(filepath);
	try {
		Files.copy(src,destination);
		baseTestlog.info("captured element screenshot");
		}
catch(IOException e)
{
	e.printStackTrace();
	baseTestlog.info("went wrong when capturing the screen");
}

}


}
