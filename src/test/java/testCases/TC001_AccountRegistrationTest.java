package testCases;

import static org.testng.Assert.assertEquals;

import java.io.Console;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.TestBaseClass;

public class TC001_AccountRegistrationTest extends TestBaseClass {

	@Test
	public void verify_account_registration() throws InterruptedException {
		
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		System.out.println("***** Starting TC001_AccountRegistrationTest *****");
		
		try {
		//Go to home page and click on My account -> Register
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("Clicked on my account link");
		System.out.println("Clicked on my account link");
		homePage.clickRegister();
		logger.info("Clicked on my Register link");
		System.out.println("Clicked on my Register link");

		
		//Account registration page - Fill form and submit
		logger.info("Filing account registration form details");
		System.out.println("Filing account registration form details");
		AccountRegistrationPage accountRegisterPage = new AccountRegistrationPage(driver);
		accountRegisterPage.setFirstName(getRandomString(5));
		accountRegisterPage.setLastName(getRandomString(5));
		accountRegisterPage.setPassword(getRandomString(5)+"@"+getRandomNumber(5));
		accountRegisterPage.setEmail(getRandomAlphanumeric(8)+"@mailinator.com");
		
		//Account registration page - check privacy check box and submit.
		logger.info("Selecting the privacy policy checkbox");
		System.out.println("Selecting the privacy policy checkbox");
		Thread.sleep(2000);
		accountRegisterPage.checkPrivacyPolicy();
		
		logger.info("Clicking on continue button to submit the account registrationb form.");
		System.out.println("Clicking on continue button to submit the account registration form.");
		Thread.sleep(2000);
		accountRegisterPage.clickContinue();
		
		//Assertions
		logger.info("Validating the confirmation message after Submit");
		System.out.println("Validating the confirmation message after Submit");
		String confmsg = accountRegisterPage.getConfirmationMessage();
		assertEquals(confmsg, "Your Account Has Been Created!");
		
		logger.info("***** Finished TC001_AccountRegistrationTest Successfully*****");
		System.out.println("***** Finished TC001_AccountRegistrationTest Successfully*****");
		}
		
		catch(Exception e) {
			logger.error("Test case failed - TC001_AccountRegistrationTest");
			System.out.println("Test case failed - TC001_AccountRegistrationTest");
			e.printStackTrace();
			logger.debug("Debug logs..,");
			Assert.fail();
		}
		
		
	}
}
