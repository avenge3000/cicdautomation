package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
WebDriver driver;

@FindBy(css="tr td:nth-child(3)")
List<WebElement> orderitems;


	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	public boolean VerifyOrderDisplay(String productName)
	{
		Boolean matchfound=orderitems.stream().anyMatch(cartitem->cartitem.getText().equalsIgnoreCase(productName));
		return matchfound;
	}
	

}
