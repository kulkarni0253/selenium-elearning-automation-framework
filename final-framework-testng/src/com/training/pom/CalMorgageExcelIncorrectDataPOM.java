package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.training.generics.GenericMethods;
import com.trianing.waits.WaitTypes;

public class CalMorgageExcelIncorrectDataPOM {
	
	  private WebDriver driver; 
	    private WaitTypes waitTypes;
	    private GenericMethods genericMethods = new GenericMethods(driver);


		public CalMorgageExcelIncorrectDataPOM(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}

		@FindBy(xpath="//ul[@id='responsive']//a[contains(text(),'New Launch')]")
		private WebElement newLaunch;

		public void mouseOverNewLaunchBtn() {
			genericMethods.hoverWebelement(newLaunch, driver);

		}

		@FindBy(xpath="//div[@id='wpmm-megamenu']/div/div[3]/div[2]/a")
		private WebElement prestigeimg;
		public void clickPrestigeImg() {
			this.prestigeimg.click(); 
		}

		@FindBy(name="amount")
		private WebElement saleprice;

		public void entersaleprice(String saleamount) {
			this.saleprice.clear(); 
			this.saleprice.sendKeys(saleamount); 
		}
		
		public String getenteredsaleprice() {
			this.saleprice.click();
			return this.saleprice.getText();
		}
			
		@FindBy(id="downpayment")
		private WebElement downpaymentfield;

		public void enterdownpayment(String downamount) {
			this.downpaymentfield.clear(); 
			this.downpaymentfield.sendKeys(downamount); 
		}
		
		public String getentereddownamt() {
			return this.downpaymentfield.getText();
		}
			
		@FindBy(id="years")
		private WebElement loantermyrs;

		public void enterloantermyears(String loanterm) {
			this.loantermyrs.clear(); 
			this.loantermyrs.sendKeys(loanterm); 
		}
		
		public String getenteredloanterms() {
			return this.loantermyrs.getText();
		}

		@FindBy(id="interest")
		private WebElement interestrate;

		public void enterinterestrate(String rate) {
			this.interestrate.clear(); 
			this.interestrate.sendKeys(rate); 
		}
		
		public String getenteredinterestrate() {
			return this.interestrate.getText();
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

	
}
