package ABCAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ABCAcademy.pageobject.N3_CartPage;

public class N1_AbstractComponenet {

	WebDriver driver;
	
	public N1_AbstractComponenet(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css ="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css ="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public N3_CartPage goToCartPage() throws InterruptedException {
		Thread.sleep(1000);
		cartHeader.click();
		Thread.sleep(1000);
		N3_CartPage cartPage = new N3_CartPage(driver);
		return cartPage;
	}
	
	public N6_OrderPage goToOrderPage() throws InterruptedException {
		orderHeader.click();
		Thread.sleep(1000);
		N6_OrderPage orderPage = new N6_OrderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	
}
