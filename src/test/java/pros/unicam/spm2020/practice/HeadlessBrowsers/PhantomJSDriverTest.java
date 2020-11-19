package pros.unicam.spm2020.practice.HeadlessBrowsers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class PhantomJSDriverTest {

	static String projectPath;
	
	static WebDriver driver;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 projectPath = System.getProperty("user.dir");
		 
	}
	
	@Test
	void test() {
		System.setProperty("phantomjs.binary.path", projectPath+"/drivers/mac/phantomjs");

		PhantomJSDriver driver = new PhantomJSDriver();
		
		driver.get("http://seleniumhq.org/");
		
		System.out.println(driver.getTitle());
		assertEquals(driver.getTitle(), "SeleniumHQ Browser Automation");
		
		driver.quit();
		
	}

}


	

