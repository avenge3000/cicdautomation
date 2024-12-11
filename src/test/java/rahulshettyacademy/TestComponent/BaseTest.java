package rahulshettyacademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.loginPage;

public class BaseTest {
	public WebDriver driver;
	public loginPage lp;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		
		Properties prop =new Properties();
		FileInputStream his=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(his);
		String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		System.out.println(browserName);
		
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions option=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
				option.addArguments("headless");
			driver= new ChromeDriver(option);
			
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{System.setProperty("webdriver.edge.driver", "C:/Users/panarasimhan/Documents/msedgedriver.exe");
		driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public String getScreenshot(String testcase, WebDriver driver) throws IOException
	{
		File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"\\reports\\"+testcase+".png");
		FileUtils.copyFile(screenshot,file);
		return System.getProperty("user.dir")+"\\reports\\"+testcase+".png";
		
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public loginPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		lp=new loginPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod(alwaysRun = true)
	public void closePage()
	{
		driver.close();
	}
	
	public  List<HashMap<String, String>> getJsonData(File filename) throws IOException
	{
		String content=FileUtils.readFileToString(filename,StandardCharsets.UTF_8);
		
	ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String,String>> data=mapper.readValue(content, new TypeReference<List<HashMap<String,String>>>(){});
	
	return data;
	}
	
	
}
