package rahulshettyacademy.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkOut;
import rahulshettyacademy.pageobjects.confirmationPage;
import rahulshettyacademy.pageobjects.loginPage;
import rahulshettyacademy.pageobjects.productCatalogue;

public class SubmitTest extends BaseTest{

	String productName="ZARA COAT 3";
	
@Test(dataProvider="getData",groups="Purchase")
public void submitTestOrder(HashMap<String,String> map) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
	String email,password,productName;
	email=map.get("email");
	password=map.get("password");
	productName=map.get("productName");
	
	
	String country="india";
	productCatalogue p=lp.loginApplication(email,password);
	
	
	p.addProductToCart(productName);
	cartPage c=p.cartClick();
	
	Boolean match=c.VerifyProductDisplay(productName);
	Assert.assertTrue(match);
	
	checkOut o=c.Checkoutprocess();
	o.selectCountry(country);
	confirmationPage last=o.submitOrder();
	
	String finalMsg=last.verifyConfirmationMessage();
	
	Assert.assertEquals(finalMsg,"THANKYOU FOR THE ORDER.");
	
	
	}

@Test(dependsOnMethods = {"submitTestOrder"})
	public void OrderHistoryTest()
	{
		productCatalogue p=lp.loginApplication("abcd@def.com","Axgh$9876");
		OrderPage os=p.orderClick();
		os.VerifyOrderDisplay(productName);
	}

@DataProvider
	public Object[][] getData() throws IOException
	{
	
//		//return new Object[][] {{"abcd@def.com","Axgh$9876","ZARA COAT 3"},{"zxcv@qwer.com","Axgh$9876","ADIDAS ORIGINAL"}};
//	
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "abcd@def.com");
//		map1.put("password","Axgh$9876");
//		map1.put("productName","ZARA COAT 3");
//		
//		HashMap<String,String> map2=new HashMap<String,String>();
//		map2.put("email", "zxcv@qwer.com");
//		map2.put("password","Axgh$9876");
//		map2.put("productName","ADIDAS ORIGINAL");
//		return new Object[][] {{map1},{map2}};
//		
	   List<HashMap<String,String>> data=getJsonData(new File(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json"));
	   return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
