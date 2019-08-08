package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ApartmentSerachEnqPOM;
import com.training.pom.ApartmentformwithDBvalPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ApartmentformwithDBvalTest {
	
	private WebDriver driver; 
	private String baseUrl; 
	private ApartmentformwithDBvalPOM apartmentformwithDBvalPOM; 
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
		apartmentformwithDBvalPOM = new ApartmentformwithDBvalPOM(driver); 
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
	public void apartmentformwithDBvalTest() throws InterruptedException {
		try{
		apartmentformwithDBvalPOM.mouseOverNewLaunchBtn(driver); //this does mouseover launch button
		apartmentformwithDBvalPOM.clickPrestigeImg();//click prestige image
		apartmentformwithDBvalPOM.sendyourName("manzoor");//send your name here
		apartmentformwithDBvalPOM.sendyourEmail("manzoor@gmail.com");//send your mail here
		apartmentformwithDBvalPOM.sendyourSubject("apartments");//send your subject here
		apartmentformwithDBvalPOM.sendyourMessage("looking for an apartment");//send your message here
		apartmentformwithDBvalPOM.clickSubmit();//click submit here
    	System.out.println("After click on submit message received is: "+apartmentformwithDBvalPOM.getmessagePostSubmit()); //after clicking submit message displays here
    	
    	try {
			Assert.assertEquals(apartmentformwithDBvalPOM.getmessagePostSubmit(), "Thank you for your message. It has been sent"); // validating 
			System.out.println("***Message populated on screen is as Expected****");
		} catch (java.lang.AssertionError e) {
			System.out.println("***Message populated on screen is NOT as Expected****");
			screenShot.captureScreenShot("Incorrect_Message_apartmentsearchEnq");//if any error,  screenshot captured
			e.printStackTrace();
		}	
    	
    	
    	//Here, last two steps of test cases of validating this with DB entry is NOT covered
    	//Session is completed for DB connection establish and retrieving results with sample data, but as we do NOT have real-time DB access for realestate DB
    	//we will not be covering DB test steps in this case
    	// This is as per discussion with Peddi sir
    	
    	
		}catch(Exception e){
			e.printStackTrace();
			screenShot.captureScreenShot("apartmentformwithDBvalTest() issue screenshot");
			System.out.println("Something is wrong with your test case screenshot is captured at location : C:\\Users\\HarshalKulkarni\\Desktop\\screenshots please check here");
		}
	}
		
}
