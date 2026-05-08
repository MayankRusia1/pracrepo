package basic_codes;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest 
{
	WebDriver driver;
	
	@BeforeClass
	public void openBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@Test
	public void loginAttempt()
	{
		driver.get("https://the-internet.herokuapp.com/login");
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		WebElement LgnBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		LgnBtn.click();
		
		SoftAssert sa = new SoftAssert();
		WebElement lgout = driver.findElement(By.xpath("//a[@href='/logout']"));
		WebElement successmsg = driver.findElement(By.xpath("//h4[contains(text(),'Welcome')]"));
		
		sa.assertTrue(lgout.isDisplayed(),"login unsuccessful, logout Button not present on page");
		sa.assertTrue(successmsg.isDisplayed(),"login unsuccessful, success message not displayed on page");
		
		sa.assertAll();
	}
	
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
	}
}
