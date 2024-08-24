package ABCAcademy.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ABCAcademy.TestComponents.N1_BaseTest;
import ABCAcademy.pageobject.N1_LandingPage;
import ABCAcademy.pageobject.N2_ProductCatalogue;
import ABCAcademy.pageobject.N3_CartPage;
import ABCAcademy.pageobject.N4_CheckoutPage;
import ABCAcademy.pageobject.N5_ConfirmationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination extends N1_BaseTest {
	
	public N1_LandingPage landingpage;
	public N2_ProductCatalogue productCatalogue;
	public N5_ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		//code
		landingpage = launchApplication();
	}

   @Given ("^Logged in with username (.+) and password (.+)$")
   public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
   }
   
   @When ("^I add the product (.+) to Cart$")
   public void I_add_product_to_cart(String productName) throws InterruptedException {
	   List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
   }
   
   @When ("^Checkout (.+) and submit the order$")
   public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
	    
	    N3_CartPage cartPage =productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);		
		N4_CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("Pakistan");
		confirmationPage = checkoutPage.submitOrder();
   }
   
   @Then ("{string} message is displayed on ConfrimationPage")
   public void message_displayed_confirmationPage(String string) {
	   String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
   }
   
   @Then ("{string} message is displayed")
   public void message_is_displayed(String string) throws Throwable {
		
	   Assert.assertEquals(string, landingPage.getErrorMessage());
	   driver.close();		
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
