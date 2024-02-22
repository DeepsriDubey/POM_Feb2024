package com.SFDC.Base;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.SFDC.Utilities.Constants;
import com.SFDC.Utilities.ExtentReportsUtility;
import com.SFDC.Utilities.PropertiesUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends BasePage {
	protected static WebDriver driver =null;
	protected static ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();
	protected Wait<WebDriver> wait = null;
	protected Logger baseTestlog=LogManager.getLogger();
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeMethod(@Optional("chrome") String name, ITestResult result) {
		baseTestlog.info(".........BeforeMethod setUpBeforeMethod executed---------------");
		launchBrowser(name);
		/*ExtentReports extent =new ExtentReports();
		ExtentSparkReporter spark= new ExtentSparkReporter("reports/Spark.html");
		extent.attachReporter(spark);
		ExtentTest test= extent.createTest("My Test");
		*/
		String url=PropertiesUtilities.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"url");
		goToUrl(url);
	}
	
	@AfterMethod
	public void tearDownAfterTestMethod() {
		/*ExtentReports extent =new ExtentReports();
		ExtentSparkReporter spark= new ExtentSparkReporter("reports/Spark.html");
		extent.attachReporter(spark);
		ExtentTest test= extent.createTest("My Test");
		*/
		closeBrowser();
		baseTestlog.info("******tearDownAfterTestMethod executed***********");
		//test.log(Status.PASS,"This is a successful execution of test");
	}

	public static void launchBrowser(String browserName) {

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Browser instance for Chrome is created.");
			driver.manage().window().maximize();
			System.out.println("Window is maximized");
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Browser instance for Firefox is created.");
			driver.manage().window().maximize();
			System.out.println("Window is maximized");
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Browser instance for Edge is created.");
			driver.manage().window().maximize();
			System.out.println("Window is maximized");
			break;

		case "opera":
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			System.out.println("Browser instance for Opera is created.");
			driver.manage().window().maximize();
			System.out.println("Window is maximized");
			break;

		case "safari":
			// Safari does not require a separate driver setup, as it is included with macOS
			driver = new SafariDriver();
			break;

		default:
			System.out.println("Unsupported browser: " + browserName);
		}

		// return driver;
	}
	
	public static void goToUrl(String url) {
		driver.get(url);
		System.out.println(url + "is entered");
		
	}

	public void closeBrowser() {
		driver.close();
		System.out.println("Current Browser instance is closed");
		driver=null;
	}
	
	public void maximiseBrowser() {
		driver.manage().window().maximize();
	}

	public void quitBrowser() {
		driver.quit();
		baseTestlog.info("all browser closed");
		driver=null;
		
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
