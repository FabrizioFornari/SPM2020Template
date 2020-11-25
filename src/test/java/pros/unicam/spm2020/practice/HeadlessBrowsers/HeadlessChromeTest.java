package pros.unicam.spm2020.practice.HeadlessBrowsers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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


  



}