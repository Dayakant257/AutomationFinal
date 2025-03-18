package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	WebDriver driver;

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkBoxPolicy;
	
	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}
	
	public void setLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void checkPrivacyPolicy() {
		chkBoxPolicy.click();
	}
	
	//btnContinue is being used as a reference to scroll, actions.scrollToElement(btnContinue) 
	public WebElement getContinueButton() {
		return btnContinue;
	}
	public void clickContinue() {
		btnContinue.click();
	}
	
	public String getConfirmationMessage() {
		try {
			return (msgConfirmation.getText());
		}
		catch (Exception e) {
			return (e.getMessage());
		}
	}

}
