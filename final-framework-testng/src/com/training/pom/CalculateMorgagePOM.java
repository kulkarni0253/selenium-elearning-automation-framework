package com.training.pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculateMorgagePOM{

      private WebDriver driver; 


	public CalculateMorgagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//ul[@id='responsive']//a[contains(text(),'New Launch')]")
	private WebElement newLaunch;

	public void mouseOverNewLaunchBtn() {

		Actions a = new Actions(driver);
		a.moveToElement(newLaunch).build().perform();;

	}

	@FindBy(xpath="//ul[@id='responsive']//a[text()='Donec quis']")
	private WebElement donecQuisImg;

	public void ClickDonecQuisImg() {
		this.donecQuisImg.click(); 
	}



	@FindBy(name="amount")
	private WebElement saleprice;

	public void entersaleprice(String saleamount) {
		this.saleprice.clear(); 
		this.saleprice.sendKeys(saleamount); 
	}

	@FindBy(id="downpayment")
	private WebElement downpaymentfield;

	public void enterdownpayment(String downamount) {
		this.downpaymentfield.clear(); 
		this.downpaymentfield.sendKeys(downamount); 
	}



	@FindBy(id="years")
	private WebElement loantermyrs;

	public void enterloantermyears(String loanterm) {
		this.loantermyrs.clear(); 
		this.loantermyrs.sendKeys(loanterm); 
	}

	@FindBy(id="interest")
	private WebElement interestrate;

	public void enterinterestrate(String rate) {
		this.interestrate.clear(); 
		this.interestrate.sendKeys(rate); 
	}
	

	@FindBy(xpath="//button[@class='button calc-button']")
	private WebElement calculate;

	public void clickCalculate() {
		this.calculate.click();
	}

	@FindBy(xpath="//div[@class = 'notification success']")
	private WebElement calculatedvalue;

	public String getcalculatedvaluetext() throws InterruptedException
	{

		Thread.sleep(5000L);
		String msg =calculatedvalue.getText();
		return msg;

	}
//
//
//	@FindBy(xpath="//a[@class='sign-in']")
//	private WebElement loginRegisterlink;
//
//	public void ClickloginRegisterlink() {
//		this.loginRegisterlink.click(); 
//	}
//
//	@FindBy(xpath="//input[@id='user_login']")
//	private WebElement userLogin;
//
//	public void senduserLogin() throws IOException {
//		this.userLogin.clear(); 
//
//		FileInputStream fis = new FileInputStream("./resources/others.properties");
//		Properties pro=new Properties();
//		pro.load(fis);
//		String userID=pro.getProperty("UserID");
//		this.userLogin.sendKeys(userID); 
//
//
//	}


}