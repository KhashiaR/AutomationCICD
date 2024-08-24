package ABCAcademy.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ABCAcademy.AbstractComponents.N1_AbstractComponenet;

public class N1_LandingPage extends N1_AbstractComponenet {
	
	WebDriver driver;
	public  N1_LandingPage(WebDriver driver) {
		//Initialize
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));

	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement paasswordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public N2_ProductCatalogue loginApplication(String Email, String password) {
		userEmail.sendKeys(Email);
		paasswordEle.sendKeys(password);
		submit.click();
		N2_ProductCatalogue productCatalogue = new N2_ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
