package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBaseClass {
	
	public WebDriver driver;
	@BeforeClass
	public void setup() {
		
		//Setting up the Chrome driver
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://localhost/opencart/upload/index.php");
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public String getRandomString(int size) {
		String generatedString = RandomStringUtils.randomAlphabetic(size);
		return generatedString;
	}
	
	public String getRandomNumber(int size) {
		String generatedNumber = RandomStringUtils.randomNumeric(size);
		return generatedNumber;
	}
	
	public String getRandomAlphanumeric(int size) {
		String generatedAlphaNum = RandomStringUtils.randomAlphanumeric(size);
		return generatedAlphaNum;
	}

}
