package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CalMorgageExcelIncorrectDataPOM;
import com.training.pom.CalculateMorgageusingExcelPOM;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CalMorgageExcelIncorrectDataTest {
	private WebDriver driver;
	private String baseUrl;
	private CalMorgageExcelIncorrectDataPOM calMorgageExcelIncorrectDataPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private ApachePOIExcelRead apachePOIExcelRead;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData() throws IOException {
		String fileName = properties.getProperty("excelfile2");
		return new ApachePOIExcelRead().getExcelContent(fileName);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		calMorgageExcelIncorrectDataPOM = new CalMorgageExcelIncorrectDataPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		driver.get(baseUrl);

	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(5000);
		driver.quit();
	}

	@Test(dataProvider = "excel-inputs")// here passing data using dataprovider via excel sheet
	public void calculateMorgageIncorrectdatausingExcel(String Sales_Price, String Down_Payment, String Loan_Term,
			String Rate_Of_Interest) throws InterruptedException {
		try{
		calMorgageExcelIncorrectDataPOM.mouseOverNewLaunchBtn();// mouseover Launch button element here
		calMorgageExcelIncorrectDataPOM.clickPrestigeImg();// clicks Prestige Image here
		calMorgageExcelIncorrectDataPOM.entersaleprice(Sales_Price);//enter SALES PRICE in excel under this column name here
		calMorgageExcelIncorrectDataPOM.enterdownpayment(Down_Payment);//enter SALES PRICE in excel under this column name here
		calMorgageExcelIncorrectDataPOM.enterloantermyears(Loan_Term);//enter SALES PRICE in excel under this column name here
		calMorgageExcelIncorrectDataPOM.enterinterestrate(Rate_Of_Interest);//enter SALES PRICE in excel under this column name here
		calMorgageExcelIncorrectDataPOM.clickCalculate();// clicks CALCULATE button

		try {
			Assert.assertTrue(calMorgageExcelIncorrectDataPOM.getcalculatedvaluetext().contains("Rs"));//displaying monthly payment Text after hitting calculate button
			System.out.println("Calculated Value as per Excel data is : "
					+ calMorgageExcelIncorrectDataPOM.getcalculatedvaluetext());//displaying monthly payment value after hitting calculate button

		} catch (java.lang.AssertionError e) {

			System.out.println(
					"Monthly Payment value is NOT calculated.. as one of MANDATORY FILEDS for MORGAGE calculation is missing in your excel.");
			System.out.println(" Mandantory Fields are : SALE PRICE | DOWNPAYMENT | LOAN TERMS | INTEREST RATE, please check one of these once");
			System.out.println("Screenshot is captured for your reference at location : C:\\Users\\HarshalKulkarni\\Desktop\\screenshots");
			
			screenShot.captureScreenShot("Morgage value not calculated screenshot");// if error, screenshot captured here
		}
		}catch(Exception e){
			e.printStackTrace();
			screenShot.captureScreenShot("calculateMorgageIncorrectdatausingExcel() issue screenshot");
			System.out.println("Something is wrong with your test case screenshot is captured at location : C:\\Users\\HarshalKulkarni\\Desktop\\screenshots please check here");
		}
	}
}
