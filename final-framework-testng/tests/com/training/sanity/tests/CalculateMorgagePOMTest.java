package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CalculateMorgagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CalculateMorgagePOMTest{

	private WebDriver driver; 
	private String baseUrl; 
	private CalculateMorgagePOM calculateMorgagePOM; 
	private static Properties properties; 
	private ScreenShot screenShot; 


	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		calculateMorgagePOM = new CalculateMorgagePOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(5000);
		driver.quit();
	}

	@Test
	public void calculateMorgageAMT() throws InterruptedException {
		calculateMorgagePOM.mouseOverNewLaunchBtn();
		calculateMorgagePOM.ClickDonecQuisImg();
		calculateMorgagePOM.entersaleprice("400000");
		calculateMorgagePOM.enterdownpayment("20000");
		calculateMorgagePOM.enterloantermyears("20");
		calculateMorgagePOM.enterinterestrate("7.25");
		calculateMorgagePOM.clickCalculate();
		
		try{
			Assert.assertEquals(calculateMorgagePOM.getcalculatedvaluetext(), "Monthly Payment: 3003.43 Rs.");
			System.out.println("Value is calculated correctly and its : :"+calculateMorgagePOM.getcalculatedvaluetext());
			
		}catch(Exception e){
			System.out.println("OOPS !!! value is incorrect)");
			screenShot.captureScreenShot("Incorrect_morgage_screenshot");
			e.printStackTrace();
			
		}
		

	}

}