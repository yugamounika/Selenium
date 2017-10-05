import org.testng.annotations.Test;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class testNG
{
	WebDriver driver;
	
	@BeforeTest
	public void initialise()
	{
		System.setProperty("webdriver.chrome.driver", "Y:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");
	}
	
	@Test(priority = 1)
	public void signinfunc()
	{
		//Scroll for signin button
		Actions signin = new Actions(driver);
		WebElement forsignin = driver.findElement(By.xpath(".//*[@id=\'nav-link-accountList\']/span[1]"));
		signin.moveToElement(forsignin).moveToElement(driver.findElement(By.xpath(".//*[@id=\'nav-flyout-ya-signin\']/a/span"))).click().build().perform();
	
		//Signin to webpage using credentials
		driver.findElement(By.id("ap_email")).sendKeys("*********@gmail.com");
		driver.findElement(By.id("ap_password")).sendKeys("*********");
		driver.findElement(By.id("signInSubmit")).click();
	
	}
	
	@Test (priority = 2)
	public void searchitemfunc()
	{
		//Search for an item
		driver.findElement(By.name("field-keywords")).sendKeys("Apple iphone 7");
		driver.findElement(By.name("field-keywords")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath(".//*[@id=\'result_0\']/div/div/div/div[2]/div[1]/div[1]/a/h2")).click();
	}

	@Test (priority = 3)
	public void addtocartfunc()
	{
		//Scroll down
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,200)", "");
		
		//Add item to cart
		driver.findElement(By.id("add-to-cart-button")).click();//*[@id="add-to-cart-button"]
		
		//Incase a popup window opens with Yes/No
		//Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		WebElement addcover = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id=\'siAddCoverage-announce\']")));
		addcover.click();
		
	}
	
	@Test (priority = 4)
	public void deletecartfunc() throws InterruptedException
	{
		//Delete
		//Delete the 2nd item first and then delete 1st item
		driver.findElement(By.xpath(".//*[@id=\'nav-cart\']/span[3]")).click();
		driver.findElement(By.xpath(".//*[@id=\'activeCartViewForm\']/div[2]/div[2]/div[4]/div/div[1]/div/div/div[2]/div/span[1]/span/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id=\'activeCartViewForm\']/div[2]/div[1]/div[4]/div/div[1]/div/div/div[2]/div/span[1]/span/input")).click();

		/*
		//Delete 1st item, wait, refresh so that second item becomes first(in position), then delete the remaining item
		driver.findElement(By.xpath(".//*[@id='nav-cart']/span[2]")).click();
		driver.findElement(By.xpath(".//*[@id=\'activeCartViewForm\']/div[2]/div[1]/div[4]/div/div[1]/div/div/div[2]/div/span[1]/span/input")).click();
		Thread.sleep(3000);
		driver.navigate().refresh();
		driver.findElement(By.xpath(".//*[@id=\'activeCartViewForm\']/div[2]/div[1]/div[4]/div/div[1]/div/div/div[2]/div/span[1]/span/input")).click();
		 */
	}
	
	@Test (priority = 5)
	public void signoutfunc()
	{	
		//Mouse over for account
		Actions logout = new Actions(driver); 
		WebElement sigout = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]/span[2]"));
		logout.moveToElement(sigout).moveToElement(driver.findElement(By.xpath("//*[@id=\"nav-item-signout\"]/span"))).click().build().perform();
		
		//Scroll down for signing out
		JavascriptExecutor signoutscroll = (JavascriptExecutor)driver;
		signoutscroll.executeScript("window.scrollBy(0,250)", "");
		//Click on Signout
		driver.findElement(By.xpath("//*[@id=\"nav-item-signout\"]/span")).click();
	}
	
	@AfterTest
	public void close()
	{
		driver.quit();
	}
	
}
