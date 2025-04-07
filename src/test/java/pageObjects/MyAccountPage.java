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
	
	public boolean myAccHeadingExists() {
		try {
		return myAccHeading.isDisplayed();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
