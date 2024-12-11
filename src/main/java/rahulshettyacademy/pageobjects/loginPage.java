package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class loginPage extends AbstractComponent {

		WebDriver driver;
		productCatalogue pc;
		
		public loginPage(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
		@FindBy(id="userEmail")
		WebElement username;
		
		@FindBy(id="userPassword")
		WebElement pwd;
		
		@FindBy(id="login")
		WebElement button;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement errormsg;
		
		public productCatalogue loginApplication(String uname, String passwd)
		{
			username.sendKeys(uname);
			pwd.sendKeys(passwd);
			button.click();
			pc=new productCatalogue(driver);
			return pc;
		}
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		public String getErrorMessage()
		{
			waitForPageElementToAppear(errormsg);
			return errormsg.getText();
		}
}
