package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.cartPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css="[routerlink*='cart'")
	WebElement cart;
	
	@FindBy(css="[routerlink*='orders'")
	WebElement order;
	

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForPageElementToAppear(WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public cartPage cartClick()
	{
		cart.click();
		cartPage c=new cartPage(driver);
		return c;
	}
	
	public OrderPage orderClick()
	{
		order.click();
		OrderPage o=new OrderPage(driver);
		return o;
	}
}
