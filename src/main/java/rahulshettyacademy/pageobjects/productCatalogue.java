package rahulshettyacademy.pageobjects;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class productCatalogue extends AbstractComponent{

		WebDriver driver;
		
		public productCatalogue(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
		@FindBy(css=".mb-3")
		List<WebElement> cards;
		
		//By.cssSelector(".ng-animating")
		@FindBy(css=".ng-animating")
		WebElement animate;
		
		By card=By.cssSelector(".mb-3");
		By addToCart=By.cssSelector(".card-body button:last-of-type");
		By toastMessage=By.id("toast-container");
		
		public List<WebElement> getProducts()
		{
			waitForElementToAppear(card);
			return cards;	
		}
		
		public WebElement getProductByName(String name)
		{
			WebElement item=getProducts().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(name)).findFirst().orElse(null);
			return item;
		}
		
		public cartPage addProductToCart(String name)
		{
			WebElement prod= getProductByName(name);
			prod.findElement(addToCart).click();	
			waitForElementToAppear(toastMessage);
			waitForElementToDisappear(animate);
			cartPage c= new cartPage(driver);
			return c;
		}
		
}
