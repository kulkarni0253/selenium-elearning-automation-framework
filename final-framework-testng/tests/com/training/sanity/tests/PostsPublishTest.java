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
import com.training.pom.PostsPublishPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class PostsPublishTest {
	
	private WebDriver driver;
	private String baseUrl;
	private PostsPublishPOM postsPublishPOM;
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
		postsPublishPOM = new PostsPublishPOM(driver);
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
	public void postpublishtest() throws InterruptedException {
		try{
		postsPublishPOM.userloggedinasadmin("admin", "adminuser@123");// entered username/password here, users logs in to application as ADMIN
		
		try {
			Assert.assertEquals(postsPublishPOM.getdashboardtitle(), "Dashboard ‹ Real Estate — WordPress");//verifies DASHBOARD page title
			postsPublishPOM.hoverposts(driver);
			System.out.println("The text is: "+postsPublishPOM.hoverallpostsandgettext(driver)); // prints ALL POSTS tag - POSTS
			Assert.assertEquals(postsPublishPOM.hoverallpostsandgettext(driver), "All Posts"); // verifies ALL POSTS tag text - POSTS
			Assert.assertEquals(postsPublishPOM.hoveraddnewandgettext(driver), "Add New");// verifies ADD NEW tag text- POSTS
			Assert.assertEquals(postsPublishPOM.hovercategoriesandgettext(driver), "Categories");// verifies CATEGORIES tag text - POSTS
			Assert.assertEquals(postsPublishPOM.hovertagsandgettext(driver), "Tags");// verifies TAG tag text - POSTS
			System.out.println("User is on DashBoard as expected and ALL LINKS fo POST are present as expected");

		} catch (Exception e) {
			System.out.println("user landed on wrong page OR ONE of the LINKS fo POST are missing...please check with developer on this..");
			screenShot.captureScreenShot("Incorrect_Dashboard_page OR ONE of the LINKS fo POST are missing");
		}
		
		postsPublishPOM.clickoncategories();//clicks on categories link
		System.out.println("User is on below page as expected: "+postsPublishPOM.getdashboardtitle());//prints categories page title
		postsPublishPOM.categorypagedetailsform("New Launches", "launch", "my launch", "New Launches of villas, apartments, flats NYC");//Fills in Categories details for new category addition
		postsPublishPOM.newaddcategoryclick();//clicks on ADD NEW CATEGORY button
		
		try {
			Assert.assertEquals(postsPublishPOM.checknewcategoryadded("New Launches","New Launches of villas, apartments, flats NYC" ), "New Launches of villas, apartments, flats NYC");// verfies newly added category
			System.out.println("Added Category is validated to be correct");

		} catch (Exception e) {
			System.out.println("Added Category is validated to be incorrect");
			screenShot.captureScreenShot("Added Category is validated to be incorrect");
		}
		
		postsPublishPOM.clickallposts(driver);//click ALL POSTS under DASHBOARD
		System.out.println("User is on below page as expected: "+postsPublishPOM.getdashboardtitle());//prints ALL POSTS page title
		
		postsPublishPOM.clickonaddnew();//click on ADD NEW POST button
		postsPublishPOM.enternewposttitle("New Launches"); // enters new post title
		postsPublishPOM.enternewposttitledesc("New Launch in Home");// enters new post description
		postsPublishPOM.selectnewpostcategory();// selects new post category as required
		postsPublishPOM.clickpublishbuttonforpost();//clicks on PUBLISH button for POST to publish
		
		try {
			Assert.assertTrue(postsPublishPOM.getpulishedpostmsg().contains("Post published. View post"));//verifies POST is PUBLISHED successfully..
			System.out.println("Post is Published as expected");

		} catch (Exception e) {
			System.out.println("Post is NOT Published as expected");
			screenShot.captureScreenShot("Post is NOT Published as expected");
		}
		}catch(Exception e){
			e.printStackTrace();
			screenShot.captureScreenShot("postpublishtest() issue screenshot");
			System.out.println("Something is wrong with your test case screenshot is captured at location : C:\\Users\\HarshalKulkarni\\Desktop\\screenshots please check here");
		}
	}

}
