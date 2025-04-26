package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.TestBaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends TestBaseClass{
	/*provided data provider 
	 * name and 
	 * class
	 *     - Because data provider class is not present in same package
	 *     - No need to specify class if data provider is present in the same package */

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verify_login(String email, String pwd, String exp) {

		try {
			logger.info("***** Starting TC_003_LoginDDT *****");

			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(email);
			loginPage.setPassword(pwd);
			loginPage.clickLogin();

			MyAccountPage myAccountPage = new MyAccountPage(driver);

			//without data driven test 
			//assertTrue(myAccountPage.myAccHeadingExists());

			Boolean targetPage = myAccountPage.myAccHeadingExists();

			if(exp.equalsIgnoreCase("valid")) {
				if(targetPage == true) {
					Assert.assertTrue(true);
					myAccountPage.clickLogout();
				}
				else {
					Assert.assertTrue(false);
				}
			}

			else if(exp.equalsIgnoreCase("invalid")) {
				if(targetPage==true) {
					myAccountPage.clickLogout();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			
		}
		logger.info("***** Starting TC_003_LoginDDT *****");
	}

}
