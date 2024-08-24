package ABCAcademy.Tests;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ABCAcademy.AbstractComponents.N6_OrderPage;
import ABCAcademy.TestComponents.N1_BaseTest;
import ABCAcademy.pageobject.N2_ProductCatalogue;
import ABCAcademy.pageobject.N3_CartPage;
import ABCAcademy.pageobject.N4_CheckoutPage;
import ABCAcademy.pageobject.N5_ConfirmationPage;



public class N2_SubmitOrderTest extends N1_BaseTest {

	//String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		N2_ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		//Get Product List
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		
		
		N3_CartPage cartPage =productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);		
		N4_CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("Pakistan");
		N5_ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	//to very zara coat 3 is displaying in orders page
	
	@Test(dependsOnMethods=("submitOrder"), dataProvider="getData")
	public void OrderHistoryTest(HashMap<String,String> input) throws InterruptedException {
		N2_ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		N6_OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(input.get("product")));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String,String>> data = getJsonDataToMap((System.getProperty("user.dir") + "//src//test//java//ABCAcademy//data//PurchaseOrder.json"));
		return new Object[][] {{data.get(0)},{data.get(1)}};

	}
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "KTest@gmail.com");
//	map.put("password", "Qwerty@123456");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "KTest1@gmail.com");
//	map1.put("password", "Qwerty@123456");
//	map1.put("product", "ADIDAS ORIGINAL");
//	return new Object[][] {{"KTest@gmail.com","Qwerty@123456","ZARA COAT 3"},{"KTest1@gmail.com","Qwerty@123456","ADIDAS ORIGINAL"}};
			
	
}
