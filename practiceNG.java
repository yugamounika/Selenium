
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class practiceNG
{
	WebDriver driver;
	WebDriverWait wait;
	//Alert alert;
	@BeforeTest
	public void start()
	{
		System.setProperty("webdriver.chrome.driver", "Y:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://the-internet.herokuapp.com/");
	}
	
/*
	@Test (priority = 1)
	public void basicAuth()
	{
		driver.findElement(By.cssSelector("#content > ul > li:nth-child(2) > a")).click();
		//wait = new WebDriverWait(driver, 3000);
		//Switching to 'Alert'
		Alert alertPopUp = driver.switchTo().alert();
		alertPopUp = wait.until(ExpectedConditions.alertIsPresent());
		alertPopUp.authenticateUsing(new UserAndPassword("admin", "admin"));
		//alertPopUp.setCredentials(new UserAndPassword("admin", "admin"));
		alertPopUp.accept();
		//driver.switchTo().alert().accept();
	}
*/
	@Test (priority = 2)
	public void checkBoxes()
	{
		driver.navigate().to("http://the-internet.herokuapp.com/");
		driver.findElement(By.xpath(".//*[@id=\'content\']/ul/li[5]/a")).click();
		driver.findElement(By.cssSelector("#checkboxes > input[type=\"checkbox\"]:nth-child(1)")).click();
		driver.findElement(By.cssSelector("#checkboxes > input[type=\"checkbox\"]:nth-child(3)")).click();

	}
	
	@Test (priority = 3)
	public void dropDown()
	{
		driver.navigate().back();
		driver.findElement(By.xpath(".//*[@id=\'content\']/ul/li[9]/a")).click();
		Select dropdown = new Select(driver.findElement(By.id("dropdown")));
		dropdown.selectByValue("2");
	}
	
	@Test (priority = 4)
	public void dragAndDrop() throws InterruptedException
	{
		driver.navigate().back();
		driver.findElement(By.xpath(".//*[@id=\'content\']/ul/li[8]/a")).click();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		WebElement From = driver.findElement(By.id("column-a"));
		WebElement To = driver.findElement(By.id("column-b"));
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(From).
								moveToElement(To).
								release(To).
								build();
		dragAndDrop.perform();
							/* Method 2
							//builder.dragAndDrop(Source, Target).build().perform();
							*/
		
							/* Method 3
							//builder.clickAndHold(From).build().perform();
							//builder.moveToElement(To).build().perform();
							//builder.release(To).build().perform();
							*/
	}
	
	@Test (priority = 5)
	public void formAuthentication()
	{
		driver.navigate().to("http://the-internet.herokuapp.com/login");
		//driver.findElement(By.xpath(".//*[@id=\'content\']/ul/li[18]/a")).click();
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		//driver.findElement(By.xpath("//*[@id=\"login\"]/button/i")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();
		driver.findElement(By.xpath(".//*[@id='content']/div/a/i")).click();
	}
	
	@Test (priority = 6)
	public void forgotPassword()
	{
		driver.navigate().to("http://the-internet.herokuapp.com/");
		driver.findElement(By.cssSelector("#content > ul > li:nth-child(17) > a")).click();
		driver.findElement(By.id("email")).sendKeys("*********@gmail.com");
		driver.findElement(By.id("form_submit")).click();
	}
		
	@Test (priority = 7)
	public void hovers()
	{
		driver.navigate().to("http://the-internet.herokuapp.com/hovers");
		//driver.findElement(By.xpath(".//*[@id=\'content\']/ul/li[22]/a")).click();
		//User1
		WebElement element1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));
		Actions hover1 = new Actions(driver);
		hover1.moveToElement(element1).build().perform();
        	driver.findElement(By.linkText("View profile")).click();
        	//User2
        	driver.navigate().back();
        	WebElement element2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/img"));
        	Actions hover2 = new Actions(driver);
        	hover2.moveToElement(element2).build().perform();
        	driver.findElement(By.linkText("View profile")).click();
        	//User3
        	driver.navigate().back();
        	WebElement element3 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/img"));
        	Actions hover3 = new Actions(driver);
        	hover3.moveToElement(element3).build().perform();
        	driver.findElement(By.linkText("View profile")).click();
		//Redundancy in code can be avoided using @DataProvider
	}
	
	@Test (priority = 8)
	public void infiniteScroll() throws InterruptedException
	{
		driver.navigate().to("http://the-internet.herokuapp.com/");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,200)", "");
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[23]/a")).click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		/*
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		*/
		driver.findElement(By.xpath("//*[@id=\"page-footer\"]/div/div/a")).click();	
	}
	
	@Test (priority = 9)
	public void geoLocation()
	{
		driver.navigate().to("http://the-internet.herokuapp.com/");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,200)", "");
		driver.findElement(By.xpath(".//*[@id=\'content\']/ul/li[20]/a")).click();
		driver.findElement(By.xpath(".//*[@id=\'content\']/div/button")).click();
	}
	
	@Test (priority = 10)
	public void horizontalSlide() throws InterruptedException
	{
		driver.navigate().to("http://the-internet.herokuapp.com/");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,200)", "");
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[21]/a")).click();
		
		WebElement slider = driver.findElement(By.xpath(".//*[@id=\'content\']/div/div/input"));
		Actions slide = new Actions(driver);
		slide.click(slider).build().perform();
		Thread.sleep(1000);
		
		for (int i = 0; i < 10; i++)
		{
		    slide.sendKeys(Keys.ARROW_RIGHT).build().perform();
		    //System.out.println("Slider offset value : " + driver.findElement(By.id("range")).getText() + "\n");
		    Thread.sleep(1000);
		}
		System.out.println("Slider offset value : " + driver.findElement(By.id("range")).getText() + "\n");
	}
	
	@AfterTest
	public void close()
	{
		driver.quit();
	}
	
}
