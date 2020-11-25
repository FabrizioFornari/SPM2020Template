package pros.unicam.spm2020.practice.HeadlessBrowsers;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
//import org.junit.Test;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HeadlessChromeTest
{
	
	static String browser;
	static String projectPath;
	static String pathToMacDrivers;
	
	static WebDriver driver;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 projectPath = System.getProperty("user.dir");
		 
	}
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
	    System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/mac"+"/chromedriver");
	    
		ChromeOptions chromeOptions = new ChromeOptions();
	    chromeOptions.addArguments("--headless");

	    driver = new ChromeDriver(chromeOptions);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
	

  @Test
  @Disabled
  public void createChromeDriverHeadless() throws IOException
  {
      
	  driver.navigate().to("https://www.seleniumhq.org/");
      System.out.println("Title is: "+driver.getTitle());
      assertTrue(driver.getTitle().contains("Selenium"));
  }

  
  @Test
  void checkUserLoginGmail() throws InterruptedException {
		
		// navigate your driver to mail.google.com
		driver.get("http://mail.google.com");

		//findElement by id
		driver.findElement(By.id("identifierId")).sendKeys("john.luiz.2001@gmail.com");
			
		
		//Find Element by className
		driver.findElement(By.className("VfPpkd-RLmnJb")).click();
		
		Thread.sleep(3000);
		
		//Find Element by className
		driver.findElement(By.name("password")).sendKeys("12345");	
		//driver.findElement(By.name("password")).sendKeys(rightPassword);	
		
		Thread.sleep(3000);
		
		driver.findElement(By.className("VfPpkd-RLmnJb")).click();
		Thread.sleep(4000);
		
		String at = driver.getTitle();
		String et = "Inbox (1) - john.luiz.2001@gmail.com - Gmail";
		
		System.out.println(at);		
		Thread.sleep(4000);

		Assert.assertEquals(et,at);
	}
  



}