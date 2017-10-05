package dataDriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class practiceDataDriven
{
	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void start()
	{
		System.setProperty("webdriver.chrome.driver", "Y:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://the-internet.herokuapp.com/hovers");
	}
	
	@DataProvider(name = "hover-xpath")
	public static String[] xpathInput()
	{
		return new String[] {".//*[@id=\'content\']/div/div[1]/img", ".//*[@id=\'content\']/div/div[2]/img", ".//*[@id=\'content\']/div/div[3]/img"};
	}	
	
	@Test(dataProvider = "hover-xpath")
	public void hovers(String xpathExp) throws InterruptedException
	{
		
		//driver.navigate().to("http://the-internet.herokuapp.com/hovers");
		WebElement element = driver.findElement(By.xpath(xpathExp));
		Thread.sleep(1000);
		Actions hover = new Actions(driver);
		hover.moveToElement(element).build().perform();
		Thread.sleep(1000);
        driver.findElement(By.linkText("View profile")).click();
        driver.navigate().back();
        //driver.quit();
	}
	
	@AfterTest
	public void close()
	{
		driver.quit();
	}
	
}
