package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent {
WebDriver driver;

@FindBy(css=".cartSection h3")
List<WebElement> cartitems;

@FindBy(css=".totalRow button")
WebElement checkoutbutton;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	public boolean VerifyProductDisplay(String productName)
	{
		Boolean matchfound=cartitems.stream().anyMatch(cartitem->cartitem.getText().equalsIgnoreCase(productName));
		return matchfound;
	}
	
	
	public checkOut Checkoutprocess() throws InterruptedException
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
		Thread.sleep(1000);
		checkoutbutton.click();
		checkOut c= new checkOut(driver);
		return c;

	}
}
