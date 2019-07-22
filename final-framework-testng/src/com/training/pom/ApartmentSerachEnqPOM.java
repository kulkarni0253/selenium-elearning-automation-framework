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

public class ApartmentSerachEnqPOM {

      private WebDriver driver; 


	public ApartmentSerachEnqPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//ul[@id='responsive']//a[contains(text(),'New Launch')]")
	private WebElement newLaunch;

	public void mouseOverNewLaunchBtn() {

		Actions a = new Actions(driver);
		a.moveToElement(newLaunch).build().perform();

	}

	@FindBy(xpath="//ul[@id='responsive']//a[text()='Donec quis']")
	private WebElement donecQuisImg;

	public void ClickDonecQuisImg() {
		this.donecQuisImg.click(); 
	}



	@FindBy(name="your-name")
	private WebElement yourNameEdt;

	public void sendyourName(String name) {
		this.yourNameEdt.clear(); 
		this.yourNameEdt.sendKeys(name); 
	}

	@FindBy(name="your-email")
	private WebElement yourEmailEdt;

	public void sendyourEmail(String email) {
		this.yourEmailEdt.clear(); 
		this.yourEmailEdt.sendKeys(email); 
	}

	@FindBy(name="your-subject")
	private WebElement yourSubjectEdt;

	public void sendyourSubject(String subject) {
		this.yourSubjectEdt.clear(); 
		this.yourSubjectEdt.sendKeys(subject); ;
	}

	@FindBy(name="your-message")
	private WebElement yourMsgEdt;

	public void sendyourMessage(String message) {
		this.yourMsgEdt.clear(); 
		this.yourMsgEdt.sendKeys(message); 
	}
	@FindBy(xpath="//input[@type='submit']")
	private WebElement submit;

	public void clickSubmit() {
		this.submit.click();
	}

	@FindBy(xpath="//form[@class='wpcf7-form failed']//div[@role='alert']")
	private WebElement messagePostSubmit;

	public String getmessagePostSubmit() throws InterruptedException
	{
		Thread.sleep(5000L);
		String msg=messagePostSubmit.getText();
		return msg;
	}
	@FindBy(xpath="//a[@class='sign-in']")
	private WebElement loginRegisterlink;

	public void ClickloginRegisterlink() {
		this.loginRegisterlink.click(); 
	}

	@FindBy(xpath="//input[@id='user_login']")
	private WebElement userLogin;

	public void senduserLogin() throws IOException {
		this.userLogin.clear(); 

		FileInputStream fis = new FileInputStream("./resources/others.properties");
		Properties pro=new Properties();
		pro.load(fis);
		String userID=pro.getProperty("UserID");
		this.userLogin.sendKeys(userID); 


	}


}