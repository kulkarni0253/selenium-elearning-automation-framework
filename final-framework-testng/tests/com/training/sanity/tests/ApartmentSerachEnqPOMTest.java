package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ApartmentSerachEnqPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ApartmentSerachEnqPOMTest {

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
		apartmentSerachEnqPOM.mouseOverNewLaunchBtn();
		apartmentSerachEnqPOM.ClickDonecQuisImg();
		apartmentSerachEnqPOM.sendyourName("manzoor");
		apartmentSerachEnqPOM.sendyourEmail("manzoor@gmail.com");
		apartmentSerachEnqPOM.sendyourSubject("apartments");
		apartmentSerachEnqPOM.sendyourMessage("looking for an apartment");
		apartmentSerachEnqPOM.clickSubmit();
    	System.out.println("After click on submit message received is: "+apartmentSerachEnqPOM.getmessagePostSubmit());

		if(apartmentSerachEnqPOM.getmessagePostSubmit().equalsIgnoreCase("Thank you for your message. It has been sent"))
		{
			System.out.println("***Message Submitted*****");
		}
		else{

		screenShot.captureScreenShot("Error_apartmentserachenq_Test");
		 System.out.println("***Message cound NOT be submitted****");

		}
	}

}