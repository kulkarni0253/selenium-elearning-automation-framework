package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.training.generics.GenericMethods;
import com.trianing.waits.WaitTypes;

public class PostsPublishPOM {
	
	 private WebDriver driver; 
     private WaitTypes waitTypes;
     private GenericMethods genericMethods = new GenericMethods(driver);    


	public PostsPublishPOM(WebDriver driver) { 
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="sign-in")
	private WebElement login;
	
	@FindBy(id ="user_login")
	private WebElement username;
	
	@FindBy(id ="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginbtn;
	
	public void userloggedinasadmin(String Username, String Password) {
		login.click();
		username.sendKeys(Username);
		password.sendKeys(Password);
		loginbtn.click();
	}

	@FindBy(xpath="//ul[@id='responsive']//a[contains(text(),'New Launch')]")
	private WebElement newLaunch;
	public void clicknewlaunch() {
		newLaunch.click();
	}
	public String getdashboardtitle() {
		return driver.getTitle();
	}
	
	@FindBy(xpath = "//div[contains(text(), 'Posts')]")
	private WebElement posts;
	
	public void hoverposts(WebDriver driver) throws InterruptedException {
		genericMethods.hoverWebelement(posts, driver);
		
	}
	
	@FindBy(xpath = "//a[contains(text(),'All Posts')]")
	private WebElement Allposts;
	
	public void clickallposts(WebDriver driver) throws InterruptedException {
		genericMethods.hoverWebelement(posts, driver);
		Thread.sleep(2000);
		Allposts.click();	
	}
	
	public String hoverallpostsandgettext(WebDriver driver) throws InterruptedException {
		genericMethods.hoverWebelement(posts, driver);
		Thread.sleep(2000);
		return genericMethods.hoverWebelementandgettext(Allposts, driver);	
	}
	
	@FindBy(xpath = "//a[contains(text(),'Add New')]")
	private WebElement addnew;
	
	public String hoveraddnewandgettext(WebDriver driver) throws InterruptedException {
		genericMethods.hoverWebelement(posts, driver);
		Thread.sleep(2000);
		return genericMethods.hoverWebelementandgettext(addnew, driver);	
	}
	
	@FindBy(xpath = "//a[contains(text(),'Categories')]")
	private WebElement categories;
	
	public void clickoncategories() {
		categories.click();
	}
	
	
	public String hovercategoriesandgettext(WebDriver driver) throws InterruptedException {
		genericMethods.hoverWebelement(posts, driver);
		Thread.sleep(2000);
		return genericMethods.hoverWebelementandgettext(categories, driver);	
	}
	
	
	
	@FindBy(xpath = "//a[contains(text(),'Tags')]")
	private WebElement tags;
	
	public String hovertagsandgettext(WebDriver driver) throws InterruptedException {
		genericMethods.hoverWebelement(posts, driver);
		Thread.sleep(2000);
		return genericMethods.hoverWebelementandgettext(tags, driver);	
	}
	@FindBy(name = "tag-name")
	private WebElement catagname;
	@FindBy(name = "slug")
	private WebElement catyslugname;
	@FindBy(name ="parent")
	private WebElement parentcatselect;
	@FindBy(name = "description")
	private WebElement catdescriptionname;

	public void categorypagedetailsform(String categorytagname, String categoryslugname, String dropdownname, String description) {
		catagname.click();
		catagname.sendKeys(categorytagname);
		catyslugname.click();
		catyslugname.sendKeys(categoryslugname);
		genericMethods.selectdropdownbyvisibletext(parentcatselect, dropdownname);
		catdescriptionname.click();
		catdescriptionname.sendKeys(description);		
	}
	@FindBy(name = "submit")
	private WebElement newcatesubmit;
	public void newaddcategoryclick() {
		newcatesubmit.click();		
	}
	
	@FindBy(name = "s")
	private WebElement searchcategory;
	
	@FindBy(id="search-submit")
	private WebElement searchcategoryclick;
	
	public String checknewcategoryadded(String categorytagname, String descripname) {
		searchcategory.click();
		searchcategory.sendKeys(categorytagname);
		searchcategoryclick.click();
		return driver.findElement(By.xpath("//tr[contains(@id,'tag-')]/td[2]//p[contains(text(),'"+descripname+"')]")).getText();	
	}
	
	@FindBy(className = "page-title-action")
	private WebElement addnewbutton;
	
	public void clickonaddnew() {
		addnewbutton.click();
	}
	
	@FindBy(name="post_title")
	private WebElement posttitlename;
	@FindBy(name="content")
	private WebElement posttitledesc;
	@FindBy(id="in-category-552")
	private WebElement selectnewpostcategory;
	@FindBy(name="publish")
	private WebElement publishbutton;
	@FindBy(id="message")
	private WebElement getpublishmsg;
	
	
	
	public void enternewposttitle(String title) {
		posttitlename.click();
		posttitlename.sendKeys(title);
	}
	public void enternewposttitledesc(String desc) {
		posttitledesc.click();
		posttitledesc.sendKeys(desc);
	}
	public void selectnewpostcategory() throws InterruptedException {
		selectnewpostcategory.click();
		Thread.sleep(4000);
	}
	public void clickpublishbuttonforpost() {
		genericMethods.hoverWebelementandclick(publishbutton, driver);
		publishbutton.click();
	}
	
	public String getpulishedpostmsg() {
		return getpublishmsg.getText();
	}

}
