package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.TestBaseClass;

public class TC_002_Login_Test extends TestBaseClass{
	
	@Test
	public void verify_login() {
		logger.info("***** Starting TC_002_Login_Test *****");
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(properties.getProperty("email"));
		loginPage.setPassword(properties.getProperty("password"));
		loginPage.clickLogin();
		
		MyAccountPage myAccountPage = new MyAccountPage(driver); 
	    assertTrue(myAccountPage.myAccHeadingExists());
		
	}

}
