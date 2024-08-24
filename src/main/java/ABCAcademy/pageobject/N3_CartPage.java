package ABCAcademy.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ABCAcademy.AbstractComponents.N1_AbstractComponenet;

public class N3_CartPage extends N1_AbstractComponenet {

	WebDriver driver;
	
	public N3_CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;		
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	

	public N4_CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new N4_CheckoutPage(driver);
	}
	
	
	
}
