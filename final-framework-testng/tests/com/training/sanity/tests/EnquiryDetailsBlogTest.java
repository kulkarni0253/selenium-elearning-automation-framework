package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.EnquiryDetailsBlogPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class EnquiryDetailsBlogTest {
	
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private EnquiryDetailsBlogPOM enquiryDetailsBlogPOM;
	private GenericMethods genericMethods = new GenericMethods(driver);
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);	
		enquiryDetailsBlogPOM = new EnquiryDetailsBlogPOM(driver);
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
	public void enquirydetailsblogform() throws InterruptedException {
		try{
		enquiryDetailsBlogPOM.clickBLOG(); //clicks BLOGS button
		System.out.println(enquiryDetailsBlogPOM.getblogpagetitle());

		try {
			Assert.assertEquals(enquiryDetailsBlogPOM.getblogpagetitle(), "Blog – Real Estate"); // checking BLOG title page
			System.out.println("User is on Blog page as expected");

		} catch (Exception e) {
			System.out.println("user landed on wrong page...please check with developer on this..");
			screenShot.captureScreenShot("Incorrect_Home_title_page");
		}
		
		enquiryDetailsBlogPOM.enterpropsearchbox("Nullam hendrerit apartment"); // entering given text for property search box		
		genericMethods.switchToWindow(driver, 1); // // moving to newly open window
		System.out.println("Newly Opened Tab - page title is: "+enquiryDetailsBlogPOM.newlyopentabtitle());
		try {
			Assert.assertEquals(enquiryDetailsBlogPOM.newlyopentabtitle(), "Nullam hendrerit Apartments – Real Estate"); // verifying Nullam hendrerit Apartments – Real Estate Title page
			System.out.println("User is on Blog page as expected");

		} catch (Exception e) {
			System.out.println("user landed on wrong page...please check with developer on this..");
			screenShot.captureScreenShot("Incorrect_title_page for newly opened tab");
		}
		genericMethods.switchToWindow(driver, 0); // going back to parent window

		enquiryDetailsBlogPOM.dropusaline(); //clicks on Drop us a Line 
		enquiryDetailsBlogPOM.givename("selenium"); // sends name
		enquiryDetailsBlogPOM.giveemail("selenium@gmail.com");// sends gmail
		enquiryDetailsBlogPOM.givesubject("apartment");// sends subject
		enquiryDetailsBlogPOM.givemessage("looking for apartment");// sends message
		enquiryDetailsBlogPOM.sendbuttonclick();//clicks submit button
		
		System.out.println("After click on SEND button message received is: "+enquiryDetailsBlogPOM.getmessagePostSubmit());

		if(enquiryDetailsBlogPOM.getmessagePostSubmit().equalsIgnoreCase("Thank you for your message. It has been sent")) // verfying message post submit button click
		{
			System.out.println("***Message Submitted*****");
		}
		else{

		screenShot.captureScreenShot("Error_EnquiryDetailsPlot_Test"); // if any error, captures its screenshot
		 System.out.println("***Message cound NOT be submitted****");
		}
		System.out.println("Test case completed");
		}catch(Exception e){
			e.printStackTrace();
			screenShot.captureScreenShot("enquirydetailsblogform() issue screenshot");
			System.out.println("Something is wrong with your test case screenshot is captured at location : C:\\Users\\HarshalKulkarni\\Desktop\\screenshots please check here");
		}
		
	}

}
