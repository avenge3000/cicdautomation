package rahulshettyacademy.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkOut;
import rahulshettyacademy.pageobjects.confirmationPage;
import rahulshettyacademy.pageobjects.loginPage;
import rahulshettyacademy.pageobjects.productCatalogue;

public class StepDefnImpl extends BaseTest {

	public loginPage loginPageObject;
	public productCatalogue p;
	public confirmationPage lastpage;
	
	@Given("landed on Ecommerce page")
	public void landed_on_Ecommerce_page() throws IOException
	{
		loginPageObject= launchApplication();
	}
	
	@Given("^Logged in with username (.*) and password (.*)$ ")
	public void Logged_in_with_username_and_password(String username,String password)
	{
		System.out.println(username);
		p=loginPageObject.loginApplication(username,password);	
	}
	
	@When("^Add product (.*) to cart$")
	public void add_product_to_Cart(String productName)
	{
		p.addProductToCart(productName);	
	}
	
	@When("^checkout product (.*) and submit order$")
	public void checkout_and_submit_order(String productName) throws InterruptedException
	{
		cartPage c=p.cartClick();
		
		Boolean match=c.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		checkOut o=c.Checkoutprocess();
		o.selectCountry("india");
		lastpage=o.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfiramtionPage")
	public void message_display(String message)
	{
		String finalMsg=lastpage.verifyConfirmationMessage();
		
		Assert.assertEquals(finalMsg,message);
		
	}
    
	    
}
