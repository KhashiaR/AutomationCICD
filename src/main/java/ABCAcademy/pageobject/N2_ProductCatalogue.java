package ABCAcademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ABCAcademy.AbstractComponents.N1_AbstractComponenet;

	public class N2_ProductCatalogue extends N1_AbstractComponenet{
		
		WebDriver driver;
		public  N2_ProductCatalogue(WebDriver driver) {
			//Initialize
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}

		//PageFactory
		@FindBy(css=".mb-3")
		List<WebElement> products;
		
		@FindBy(css=".ng-animating")
		WebElement spinner;
		
		
		By productBy = By.cssSelector(".mb-3");
		By addToCart = By.cssSelector(".card-body button:last-of-type");
		By toastMessage = By.cssSelector("#toast-container");

		public List<WebElement> getProductList() {
			waitForElementToAppear(productBy);
			return products;
		}
		
		public WebElement getProductByName(String productName) {
			WebElement prod = getProductList().stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
			return prod;
		}
		
		public void addProductToCart(String productName) throws InterruptedException {
			WebElement prod = getProductByName(productName);
			prod.findElement(addToCart).click();
			waitForElementToAppear(toastMessage);
			waitForElementToDisappear(spinner);
		}
	}


