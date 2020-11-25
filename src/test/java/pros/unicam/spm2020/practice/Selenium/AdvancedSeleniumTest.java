package pros.unicam.spm2020.practice.Selenium;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;

//import io.restassured.RestAssured;

class AdvancedSeleniumTest {

	static String browser;
	static String projectPath;
	
	static String rightPassword;
	static String wrongPassword;
	static String pathToMacDrivers;
	
	static WebDriver driver;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 projectPath = System.getProperty("user.dir");
		 
		 try (InputStream input = new FileInputStream( projectPath+"/resources/config.properties")) {

	            // load a properties file
	            Properties prop = new Properties();
	            prop.load(input);

	            // get the property value and print it out 
	            wrongPassword=prop.getProperty("wrongPassword");
	            rightPassword=prop.getProperty("rightPassword");
	            browser=prop.getProperty("browser");
	            pathToMacDrivers=prop.getProperty("pathToMacDrivers");

	    } catch (IOException ex) {
	            ex.printStackTrace();
	    }
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
		setBrowserConfig();
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
	void checkUserLoginGmail() throws InterruptedException {
		
		// navigate your driver to mail.google.com
		driver.get("http://mail.google.com");
		

		//findElement by id
		driver.findElement(By.id("identifierId")).sendKeys("john.luiz.2001@gmail.com");
			
		
		//Find Element by className
		driver.findElement(By.className("VfPpkd-RLmnJb")).click();

//Example Thread Sleep
//		Thread.sleep(3000);
//		driver.findElement(By.name("password")).sendKeys(wrongPassword);

//Example Implicit Wait
//		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
//		driver.findElement(By.name("password")).sendKeys(wrongPassword);

//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, 3);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		element.sendKeys(wrongPassword);
		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(wrongPassword);
		
	
		//Find Element by className
		//driver.findElement(By.name("password")).sendKeys(wrongPassword);	
		//driver.findElement(By.name("password")).sendKeys(rightPassword);	
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("VfPpkd-RLmnJb"))).click();

		WebDriverWait waitTen = new WebDriverWait(driver, 20);
		waitTen.until(ExpectedConditions.titleContains("Inbox "));

		String at = driver.getTitle();
		String et = "Inbox (2) - john.luiz.2001@gmail.com - Gmail";
		
		System.out.println(at);		
//		Thread.sleep(4000);

		Assert.assertEquals(et,at);
	}
	
	
	@Test
	@Disabled
	void takeScreenshot() {

        driver.get("http://pros.unicam.it");
               
		WebDriverWait waitTen = new WebDriverWait(driver, 10);
		waitTen.until(ExpectedConditions.titleContains("PROS "));
        
        TakesScreenshot scrShot =((TakesScreenshot)driver);

	    //Call getScreenshotAs method to create image file
	    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile=new File(projectPath+"/resources/screenshots/"+SrcFile.getName());

        try {
			Files.copy(SrcFile.toPath(), DestFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Disabled
	void checkLinkStatus() {

        System.out.println(RestAssured.get("http://pros.unicam.it").statusCode());  
        Assert.assertEquals(RestAssured.get("http://pros.unicam.it").statusCode(), 200);

	}
	
	
	@ParameterizedTest
	@Disabled
	@ValueSource(strings = {"http://pros.unicam.it", "https://www.google.com/"}) // six 
	void checkLinkListStatus(String links) {

        System.out.println(RestAssured.get(links).statusCode());  
        Assert.assertEquals(RestAssured.get(links).statusCode(), 200);

	}
	
	
	
	@Test
	@Disabled
	  public void testGuestSearchMetadataTypeChoregraphy() throws Exception {
	    driver.get("https://pros.unicam.it:4200/index?returnUrl=%2Fhome");
	    //I modified .LinkText to partialLinkText
	    driver.findElement(By.partialLinkText("ACCESS AS GUEST")).click();
	    driver.findElement(By.xpath("//li[3]/a/p")).click();
	    driver.findElement(By.xpath("//input[@value='Search Model']")).click();
	    driver.findElement(By.xpath("//mat-radio-button[@id='mat-radio-2']/label/div[2]")).click();
	    driver.findElement(By.id("type")).click();
	    driver.findElement(By.id("type")).clear();
	    driver.findElement(By.id("type")).sendKeys("Choreography");
	    driver.findElement(By.xpath("//div/div/button")).click();
	    
	    WebElement searchResult = driver.findElement(By.cssSelector("h3"));
	    
	    System.out.println(searchResult.getText());
	    String[] parts = searchResult.getText().split(":");
	    System.out.println(parts[1]);
	    //remove white spaces
	    parts[1] = parts[1].replaceAll("\\s+","");
	    //convert to int
	    int result =Integer.parseInt(parts[1]);
	    
	    assertTrue(result>0);

	  }
	
	
	

	
	
	public static void setBrowserConfig() {
		
		if(browser.contains("Chrome")) {
			
			//System.out.println("Setting Browser Configuration");
			
			//Chrome
			//This condition block sets config for Chrome browser
			System.setProperty("webdriver.chrome.driver", projectPath+pathToMacDrivers+"/chromedriver");
			driver = new ChromeDriver();
		}
		
		if(browser.contains("Firefox")) {
				
			//Firefox	
			//This condition block sets config for Firefox browser
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability("marionette", true);
			System.setProperty("webdriver.gecko.driver", projectPath+pathToMacDrivers+"/geckodriver");
			driver = new FirefoxDriver(options);

		}		
	}
	
	
	

}