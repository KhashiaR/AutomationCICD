package ABCAcademy.AbstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ABCAcademy.pageobject.N4_CheckoutPage;

public class N6_OrderPage extends N1_AbstractComponenet{


WebDriver driver;
	
	public N6_OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;		
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName));
		return match;
	}
	

	public N4_CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new N4_CheckoutPage(driver);
	}
	

}
