package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

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
import com.training.pom.PropertysearchPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class PropertysearchPOMTest {

	private WebDriver driver;
	private String baseUrl;
	private PropertysearchPOM propertysearchPOM;
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
		propertysearchPOM = new PropertysearchPOM(driver);
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
	public void propertysearchtest() throws InterruptedException {

		propertysearchPOM.clicknewlaunch();
		System.out.println(propertysearchPOM.getfindyourhometitle());

		try {
			Assert.assertEquals(propertysearchPOM.getfindyourhometitle(), "New Launch � Real Estate");
			System.out.println("User is on Property Search as expected");

		} catch (Exception e) {
			System.out.println("user landed on wrong page...please check with developer on this..");
			screenShot.captureScreenShot("Incorrect_Home_title_page");
		}
		propertysearchPOM.enteraddress("Electronic City, Bengaluru, Karnataka, India");
		propertysearchPOM.selectpropertytypePLOT();
		propertysearchPOM.selectregionCentralBengalore();
		propertysearchPOM.searchbuttonclick();
		System.out.println(propertysearchPOM.searchresultspagecheck());

		try {
			Assert.assertEquals(propertysearchPOM.searchresultspagecheck(), "Recent Posts");
			System.out.println("User is on Property Search results page after inputs as expected");


		} catch (Exception e) {
			System.out.println("user landed on wrong page...please check with developer on this..");
			screenShot.captureScreenShot("Incorrect_search_results_title_page");
		}

	}
}