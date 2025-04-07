package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

	public static WebDriver driver; 
	/* Driver has to be static to avoid multiple driver instances creation in single test execution,
	 * TestBaseClass could be invoked multiple times as needed(Example - Invoked in ExtentReportManager.java to capture screenshot)
	 * Once driver is made static, all instances will share same driver object.
	 */
	public Logger logger;
	public Properties properties;

	@Parameters({ "browser"})
	@BeforeClass
	public void setup(String browser ) throws IOException {

		//creates the logger for the specified class, 
		// here this.getClass() helps to get the testcase class (As it extends TestBase) and initiates the logger for that particular test class.
		//System.setProperty("log4j.configurationFile","C:\\Users\\hp\\git\\repository\\OpencartV121\\src\\test\\resources\\log4j2.xml");
		logger = LogManager.getLogger(this.getClass());
		//Now this logger can be used in the test classes as setup will execute before each test (BeforeClass)
		
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		properties = new Properties();
		properties.load(file);

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

		driver.get(properties.getProperty("appUrl1"));
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
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
