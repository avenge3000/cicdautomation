package rahulshettyacademy.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.TestComponent.Retry;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.productCatalogue;

public class errorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void logincase()
	{
		lp.loginApplication("abcd@dgdffef.com","Axgh$9876");
		Assert.assertEquals(lp.getErrorMessage(),"Incorrect email or password.");
	}
	
	@Test
	public void prodcutErrorValidation()
	{
		String productName="ZARA COAT 3";
		productCatalogue p=lp.loginApplication("abcd@def.com","Axgh$9876");
		
		
		cartPage c=p.addProductToCart(productName);
		p.cartClick();
		
		Boolean match=c.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		
	}

}
