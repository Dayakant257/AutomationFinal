package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h1[normalize-space()='My Account']")
	WebElement myAccHeading;
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	
	//add logout element
	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Logout']")   
	WebElement lnkLogout;
	
	
	public boolean myAccHeadingExists() {
		try {
		return myAccHeading.isDisplayed();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//add action method for logout
	public void clickLogout() {
		lnkMyAccount.click();
		lnkLogout.click();
	}

}
