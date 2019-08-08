package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.CalculateMorgagePOM;
import com.training.pom.CalculateMorgageusingExcelPOM;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CalculateMorgageusingExcelTest {
	
	private WebDriver driver; 
	private String baseUrl; 
	private CalculateMorgageusingExcelPOM calculateMorgageusingExcelPOM; 
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
	public Object[][] getExcelData() throws IOException{
		String fileName =properties.getProperty("excelfile1"); 
		return new ApachePOIExcelRead().getExcelContent(fileName);
	}
    
	@BeforeMethod
	public void setUp() throws Exception {	
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		calculateMorgageusingExcelPOM = new CalculateMorgageusingExcelPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
	
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(5000);
		driver.quit();
	}

	@Test(dataProvider = "excel-inputs") // here passing data using dataprovider via excel sheet
	public void calculateMorgageAMTusingExcel(String Sales_Price, String Down_Payment, String Loan_Term, String Rate_Of_Interest) throws InterruptedException {
		try{
		calculateMorgageusingExcelPOM.mouseOverNewLaunchBtn(); // mouseover Launch button element here
		calculateMorgageusingExcelPOM.clickPrestigeImg(); // clicks Prestige Image here
		calculateMorgageusingExcelPOM.entersaleprice(Sales_Price); // enter SALES PRICE in excel under this column name here
		calculateMorgageusingExcelPOM.enterdownpayment(Down_Payment);// enter DOWNPAYMENT in excel under this column name here
		calculateMorgageusingExcelPOM.enterloantermyears(Loan_Term);// enter LOAN TERM YEARS in excel under this column name here
		calculateMorgageusingExcelPOM.enterinterestrate(Rate_Of_Interest);// enter INTEREST RATE in excel under this column name here
		calculateMorgageusingExcelPOM.clickCalculate();// clicks CALCULATE button
		System.out.println("Calculated Value as per Excel data is : "+calculateMorgageusingExcelPOM.getcalculatedvaluetext());//displaying monthly payment value after hitting calculate button
		}catch(Exception e){
			e.printStackTrace();
			screenShot.captureScreenShot("calculateMorgageAMTusingExcel() issue screenshot");
			System.out.println("Something is wrong with your test case screenshot is captured at location : C:\\Users\\HarshalKulkarni\\Desktop\\screenshots please check here");
		}
	}
	

}
