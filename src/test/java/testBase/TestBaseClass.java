package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBaseClass {

	public WebDriver driver;
	public Logger logger;

	@Parameters({ "browser"})
	@BeforeClass
	public void setup(String browser ) {

		//creates the logger for the specified class, 
		// here this.getClass() helps to get the testcase class (As it extends TestBase) and initiates the logger for that particular test class.
		//System.setProperty("log4j.configurationFile","C:\\Users\\hp\\git\\repository\\OpencartV121\\src\\test\\resources\\log4j2.xml");
		logger = LogManager.getLogger(this.getClass());
		//Now this logger can be used in the test classes as setup will execute before each test (BeforeClass)

		//Setting up the Chrome driver

		//Use the browser parameter (This data comes from testng.xml file), switch to the specified browser
		switch (browser.toLowerCase()) {
		case "chrome": driver = new ChromeDriver(); break;
		case "firefox": driver = new FirefoxDriver(); break; 
		case "edge": driver = new EdgeDriver(); break;
		case "safari": driver = new SafariDriver(); break;
		case "ie": driver = new InternetExplorerDriver(); break;
		//case "newBrowser": driver = new newBrowser(); break;
		default: System.out.println("INVALID BROWSER: Invalid browser name specified in testng.xml file"); return;
		}

		//driver = new ChromeDriver();
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
