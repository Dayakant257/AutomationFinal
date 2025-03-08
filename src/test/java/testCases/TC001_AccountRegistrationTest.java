package testCases;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.TestBaseClass;

public class TC001_AccountRegistrationTest extends TestBaseClass {

	@Test
	public void verify_account_registration() throws InterruptedException {
		
		//Go to home page and click on My account -> Register
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickRegister();
		
		//Account registration page - Fill form and submit
		AccountRegistrationPage accountRegisterPage = new AccountRegistrationPage(driver);
		accountRegisterPage.setFirstName(getRandomString(5));
		accountRegisterPage.setLastName(getRandomString(5));
		accountRegisterPage.setPassword(getRandomString(5)+"@"+getRandomNumber(5));
		accountRegisterPage.setEmail(getRandomAlphanumeric(8)+"@mailinator.com");
		
		//Account registration page - check privacy check box and submit.
		Thread.sleep(5000);
		accountRegisterPage.checkPrivacyPolicy();
		Thread.sleep(5000);
		accountRegisterPage.clickContinue();
		
		//Assertions
		assertEquals(accountRegisterPage.getConfirmationMessage(), 
				"Your Account Has Been Created!");
		
		
		
	}
}
