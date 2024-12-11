package rahulshettyacademy.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalontest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver= new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.get("https://rahulshettyacademy.com/client");
	
	driver.manage().window().maximize();
	
	driver.findElement(By.id("userEmail")).sendKeys("abcd@def.com");
	driver.findElement(By.id("userPassword")).sendKeys("Axgh$9876");
	driver.findElement(By.id("login")).click();
	
	List<WebElement> cards=driver.findElements(By.cssSelector(".mb-3"));
		
	WebElement item=cards.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);

	item.findElement(By.cssSelector(".card-body button:last-of-type")).click();	
	
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	List<WebElement> cartitems=driver.findElements(By.cssSelector(".cartSection h3"));
	
	Boolean matchfound=cartitems.stream().anyMatch(cartitem->cartitem.getText().equals("ZARA COAT 3"));
	Assert.assertTrue(matchfound);
	
	
	JavascriptExecutor js= (JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,700)");
	Thread.sleep(1000);

	
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	Actions a=new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector(".form-group")),"india").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
	driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'] [2]")).click();
	driver.findElement(By.cssSelector(".action__submit")).click();
	
	String finalMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertEquals(finalMsg,"THANKYOU FOR THE ORDER.");
	
	
	
	
	}

}
