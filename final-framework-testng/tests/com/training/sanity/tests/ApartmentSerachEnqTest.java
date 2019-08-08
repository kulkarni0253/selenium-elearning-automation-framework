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
import com.training.pom.ApartmentSerachEnqPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ApartmentSerachEnqTest {

	private WebDriver driver; 
	private String baseUrl; 
	private ApartmentSerachEnqPOM apartmentSerachEnqPOM; 
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
		apartmentSerachEnqPOM = new ApartmentSerachEnqPOM(driver); 
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
	public void apartmentserachenq() throws InterruptedException {
		try{
		apartmentSerachEnqPOM.mouseOverNewLaunchBtn(driver);//this does mouseover launch button
		apartmentSerachEnqPOM.ClickDonecQuisImg();//click DonecQuisImg image
		apartmentSerachEnqPOM.sendyourName("manzoor");//send your name here
		apartmentSerachEnqPOM.sendyourEmail("manzoor@gmail.com");//send your email here
		apartmentSerachEnqPOM.sendyourSubject("apartments");//send your subject here
		apartmentSerachEnqPOM.sendyourMessage("looking for an apartment");//send your message here
		apartmentSerachEnqPOM.clickSubmit();
    	System.out.println("After click on submit message received is: "+apartmentSerachEnqPOM.getmessagePostSubmit());//after clicking submit message displays here
    	
    	try {
			Assert.assertEquals(apartmentSerachEnqPOM.getmessagePostSubmit(), "Thank you for your message. It has been sent");
			System.out.println("***Message populated on screen is as Expected****");

		} catch (java.lang.AssertionError e) {
			System.out.println("***Message populated on screen is NOT as Expected****");
			screenShot.captureScreenShot("Incorrect_Message_apartmentsearchEnq");//if any error,  screenshot captured
			e.printStackTrace();
		}
		}catch(Exception e){
			e.printStackTrace();
			screenShot.captureScreenShot("apartmentserachenq() issue screenshot");
			System.out.println("Something is wrong with your test case screenshot is captured at location : C:\\Users\\HarshalKulkarni\\Desktop\\screenshots please check here");
		}
	}
}