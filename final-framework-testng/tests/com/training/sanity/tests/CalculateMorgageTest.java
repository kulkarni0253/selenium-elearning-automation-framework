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

public class CalculateMorgageTest{

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
		try{
		calculateMorgagePOM.mouseOverNewLaunchBtn();//this makes mouseover a LAUNCH button
		calculateMorgagePOM.ClickDonecQuisImg();// this clicks DoneQuisImg
		calculateMorgagePOM.entersaleprice("400000");// enter SALES PRICE here
		calculateMorgagePOM.enterdownpayment("20000");// enter DOWNPAYMENT here
		calculateMorgagePOM.enterloantermyears("20");// enter LOAN TERM YEARS here
		calculateMorgagePOM.enterinterestrate("7.25");// enter INTEREST RATE here
		calculateMorgagePOM.clickCalculate(); // clicks CALCULATE button
		
		try{
			Assert.assertEquals(calculateMorgagePOM.getcalculatedvaluetext(), "Monthly Payment: 3003.43 Rs."); //Verifying Monthly Payment value here
			System.out.println("Value is calculated correctly and its : :"+calculateMorgagePOM.getcalculatedvaluetext());
			
		}catch(Exception e){
			System.out.println("OOPS !!! value is incorrect)");
			screenShot.captureScreenShot("Incorrect_morgage_screenshot");// if error, screenshot captured here
			e.printStackTrace();
			
		}
		}catch(Exception e){
			e.printStackTrace();
			screenShot.captureScreenShot("calculateMorgageAMT() issue screenshot");
			System.out.println("Something is wrong with your test case screenshot is captured at location : C:\\Users\\HarshalKulkarni\\Desktop\\screenshots please check here");
		}
		

	}

}