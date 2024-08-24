package ABCAcademy.pageobject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ABCAcademy.AbstractComponents.N1_AbstractComponenet;

public class N4_CheckoutPage extends N1_AbstractComponenet{

WebDriver driver;
	
	public N4_CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".action__submit")
	private WebElement submit;
	
	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(xpath = "//button[contains(@class,'ta-item')][1]")
	private WebElement selectCountry;

	private By result = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
	
	public N5_ConfirmationPage submitOrder() {
		submit.click();
		return new N5_ConfirmationPage(driver);
	}
}
