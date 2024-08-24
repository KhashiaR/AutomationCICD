package ABCAcademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ABCAcademy.TestComponents.N1_BaseTest;
import ABCAcademy.TestComponents.N3_Retry;
import ABCAcademy.pageobject.N2_ProductCatalogue;
import ABCAcademy.pageobject.N3_CartPage;



	public class N3_ErrorValidationsTest extends N1_BaseTest {
	
			@Test(groups = {"Error Handling"}, retryAnalyzer=N3_Retry.class)
			public void loginErrorValidation() throws IOException, InterruptedException {
				
				String productName = "ZARA COAT 3";
				landingPage.loginApplication("Kpo@gmail.com", "Qwerty@123456");
				Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
				
			}
			
			@Test
			public void ProductErrorValidation() throws IOException, InterruptedException {
				
				String productName = "ZARA COAT 3";
				N2_ProductCatalogue productCatalogue = landingPage.loginApplication("KTest@gmail.com", "Qwerty@123456");
				
				//Get Product List
				List<WebElement> products = productCatalogue.getProductList();
				productCatalogue.addProductToCart(productName);
				N3_CartPage cartPage =productCatalogue.goToCartPage();
				
				Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 333");
				Assert.assertFalse(match);		
				
	}
}


