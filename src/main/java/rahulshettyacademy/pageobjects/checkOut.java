package rahulshettyacademy.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class checkOut extends AbstractComponent {

	WebDriver driver;
	
	public checkOut(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	

	@FindBy (css=".form-group")
	WebElement country;
	
	@FindBy (xpath="//button[@class='ta-item list-group-item ng-star-inserted'] [2]")
	WebElement searchresult;
	
	@FindBy (css=".action__submit")
	WebElement submit;	
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String name)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country,name).build().perform();
		waitForElementToAppear(results);
		searchresult.click();		
	}
	
	public confirmationPage submitOrder()
	{
		submit.click();
		confirmationPage c= new confirmationPage(driver);
		return c;
		
	}

}
