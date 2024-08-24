package ABCAcademy.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ABCAcademy.AbstractComponents.N1_AbstractComponenet;

public class N5_ConfirmationPage extends N1_AbstractComponenet {

WebDriver driver;
	
	public N5_ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css= ".hero-primary")
	WebElement confirmationMessage;

	public String getConfirmationMessage() {
		return confirmationMessage.getText();
	}

}
