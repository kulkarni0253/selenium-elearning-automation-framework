package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class EnquiryDetailsBlogPOM {
	
	  private WebDriver driver; 
	public EnquiryDetailsBlogPOM(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//ul[@id = 'responsive']//a[text()='Blog']")
		private WebElement blog;
		public void clickBLOG() {
			blog.click();
		}
		public String getblogpagetitle() {
			return driver.getTitle();
		}

		
		@FindBy(name="phrase")
		private WebElement searchpropbox;
		
		@FindBy(xpath="//a[@class='asl_res_url']")
		private WebElement searchpropclick;

		public void enterpropsearchbox(String propname) throws InterruptedException {
			this.searchpropbox.click();
			this.searchpropbox.sendKeys(propname); 
			Thread.sleep(2000);
			Actions act = new Actions(driver);
			act.moveToElement(searchpropclick).click().build().perform();
		}
		
		public String newlyopentabtitle() {
			return driver.getTitle();
		}
		
		@FindBy(xpath ="//a[@class='button fullwidth margin-top-20']")
		private WebElement dropAline;
		public void dropusaline(){
			dropAline.click();
		}
		
		@FindBy(name="name")
		private WebElement name;
		public void givename(String name) {
			this.name.sendKeys(name); 
		}
		
		@FindBy(name="email")
		private WebElement email;
		public void giveemail(String email) {
			this.email.sendKeys(email); 
		}
		
		@FindBy(name="subject")
		private WebElement subject;
		public void givesubject(String subject) {
			this.subject.sendKeys(subject); 
		}
		
		
		@FindBy(name="id:comments")
		private WebElement message;
		public void givemessage(String message) {
			this.message.sendKeys(message); 
		}
		
		@FindBy(xpath ="//input[@type='submit']")
		private WebElement sendbutton;
		public void sendbuttonclick(){
			sendbutton.click();
		}

		@FindBy(xpath ="//div[@class='wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ng']")
		private WebElement messagegenerated;
		public String getmessagePostSubmit() {
			
			return messagegenerated.getText();
		}
		
		
		
		
	
	

}
