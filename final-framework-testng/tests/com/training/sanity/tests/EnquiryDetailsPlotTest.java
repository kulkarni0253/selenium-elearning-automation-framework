package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.EnquiryDetailsPlotPOM;
import com.training.pom.PropertysearchPOM;

import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class EnquiryDetailsPlotTest {
	private WebDriver driver;
	private String baseUrl;
	private static Properties properties;
	private ScreenShot screenShot;
	private EnquiryDetailsPlotPOM enquiryDetailsPlotPOM;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);	
		enquiryDetailsPlotPOM = new EnquiryDetailsPlotPOM(driver);
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
	public void enquirydetailsplotform() throws InterruptedException {
		try{
		enquiryDetailsPlotPOM.clickplots(); //clicks on PLOTS button tab
		System.out.println(enquiryDetailsPlotPOM.getplotpagetitle()); // prints PLOTS page title

		try {
			Assert.assertEquals(enquiryDetailsPlotPOM.getplotpagetitle(), "Plots – Real Estate"); // verifying PLOTS page title
			System.out.println("User is on Plot Search as expected");

		} catch (Exception e) {
			System.out.println("user landed on wrong page...please check with developer on this..");
			screenShot.captureScreenShot("Incorrect_Plot_title_page");
		}
		enquiryDetailsPlotPOM.enteraddress("Electronic City, Bengaluru, Karnataka, India"); // enters address in address field
		enquiryDetailsPlotPOM.selectpropertytypePLOT(); // select PLOTS from the drop down options
		enquiryDetailsPlotPOM.selectregionCentralBengalore(); // select CENTRAL BANGLORE as a region from drop down options
		enquiryDetailsPlotPOM.searchbuttonclick(); // clicks search button
		System.out.println(enquiryDetailsPlotPOM.searchresultspagecheck()); // prints search results page title

		try {
			Assert.assertEquals(enquiryDetailsPlotPOM.searchresultspagecheck(), "Recent Posts"); // verifies search results page title
			System.out.println("User is on Property Search results page after inputs as expected");


		} catch (Exception e) {
			System.out.println("user landed on wrong page...please check with developer on this..");
			screenShot.captureScreenShot("Incorrect_search_results_title_page");// captures screenshot if error
		}
		
		
		enquiryDetailsPlotPOM.dropusaline(); // clicks DROP US A LINE
		enquiryDetailsPlotPOM.givename("selenium"); // sends name
		enquiryDetailsPlotPOM.giveemail("selenium@gmail.com");// sends gamil
		enquiryDetailsPlotPOM.givesubject("apartment");// sends subject
		enquiryDetailsPlotPOM.givemessage("looking for apartment");// sends Message
		enquiryDetailsPlotPOM.sendbuttonclick();//clicks on SEND button
		
		System.out.println("After click on SEND button message received is: "+enquiryDetailsPlotPOM.getmessagePostSubmit());//prints post SEND button click message

		if(enquiryDetailsPlotPOM.getmessagePostSubmit().equalsIgnoreCase("Thank you for your message. It has been sent")) // verifies  post SEND button click message
		{
			System.out.println("***Message Submitted*****");
		}
		else{

		screenShot.captureScreenShot("Error_EnquiryDetailsPlot_Test"); // captues screenshot if any error
		 System.out.println("***Message cound NOT be submitted****");
		}
		}catch(Exception e){
			e.printStackTrace();
			screenShot.captureScreenShot("enquirydetailsplotform() issue screenshot");
			System.out.println("Something is wrong with your test case screenshot is captured at location : C:\\Users\\HarshalKulkarni\\Desktop\\screenshots please check here");
		}
	}
}
