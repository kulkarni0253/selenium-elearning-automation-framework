package com.training.pom;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnquiryDetailsPlotPOM {
	
    private WebDriver driver; 


	public EnquiryDetailsPlotPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@id='responsive']//a[contains(text(),'Plots')]")
	private WebElement plots;
	public void clickplots() {
		plots.click();
	}
	
	public String getplotpagetitle() {
		return driver.getTitle();
	}
	
	@FindBy(id="keyword_search")
	private WebElement addfield;

	public void enteraddress(String address) {
		this.addfield.sendKeys(address); 
		driver.findElement(By.xpath("//h4[@class='search-title']")).click();		
	}
	
	@FindBy(xpath="//a[@class='chosen-single chosen-default']")
	private WebElement propertytypedrop;
	
	@FindBy(xpath = "//*[@id='_property_type_chosen']/div/ul/li[4]")
	private WebElement propertytype;

	public void selectpropertytypePLOT() {
		propertytypedrop.click();
		WebDriverWait wait = new WebDriverWait(driver,100);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='_property_type_chosen']/div/ul/li[4]")));
	    propertytype.click();
	}
	
	@FindBy(xpath="//*[@id='realteo-search-form']/div[2]/div[2]/div/a/span")
	private WebElement anyregion;	
	
	
	@FindBy(xpath = "//*[@id='realteo-search-form']/div[2]/div[2]/div/div/ul/li[2]")
	private WebElement selectregion;
	
	public void selectregionCentralBengalore() {
		anyregion.click();	
		selectregion.click();
	}
	
	@FindBy(xpath="//button[@class='button fullwidth']")
	private WebElement searchbutton;
	
	public void searchbuttonclick() throws InterruptedException {
		searchbutton.click();
	}
	
	@FindBy(xpath="//h3[text( )=  'Recent Posts']")
	private WebElement Searchresultpage;

	
	public String searchresultspagecheck() {
		return Searchresultpage.getText();
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
