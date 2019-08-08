package com.training.pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.trianing.waits.WaitTypes;

public class PropertysearchPOM {

      private WebDriver driver; 
      private WaitTypes waitTypes;


	public PropertysearchPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//ul[@id='responsive']//a[contains(text(),'New Launch')]")
	private WebElement newLaunch;
	public void clicknewlaunch() {
		newLaunch.click();
	}
	public String getfindyourhometitle() {
		return driver.getTitle();
	}

	@FindBy(id="keyword_search")
	private WebElement addfield;

	public void enteraddress(String address) {
		this.addfield.sendKeys(address, Keys.TAB); 
	}
	
	@FindBy(xpath="//a[@class='chosen-single chosen-default']")
	private WebElement propertytypedrop;
	
	@FindBy(xpath = "//*[@id='_property_type_chosen']/div/ul/li[4]")
	private WebElement propertytype;

	public void selectpropertytypePLOT() {
		propertytypedrop.click();
		waitTypes.elementToBeClickable(this.propertytype, 100);
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
}