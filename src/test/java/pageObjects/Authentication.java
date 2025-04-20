package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Authentication {

	WebDriver driver;

	@FindBy(xpath = "//input[@name='email']")
	WebElement userNameInputAField;

	@FindBy(xpath = "//input[@name='password']")
	WebElement paswordInputField;

	@FindBy(xpath = "//input[@name='remember' and @type='checkbox']")
	WebElement rememberMeCheckbox;

	@FindBy(xpath = "//button[text()='Sign In']")
	WebElement signInButton;

	@FindBy(xpath = "//div[contains(@data-toggle, 'dropdown')]")
	WebElement profileDropdown;

	@FindBy(xpath = "//a[text()='Log Out']")
	WebElement logoutButton;
	
	@FindBy(xpath = "//div[text()='These credentials do not match our records.']")
	WebElement errorMessage;
	
	@FindBy(xpath = "//div[@class='kt-login__logo']")
	WebElement logo;

	public Authentication(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// methods

	public void enteruserName(String useremail) {
		userNameInputAField.sendKeys(useremail);
	}

	public void enterPassword(String password) {
		paswordInputField.sendKeys(password);
	}

	public void clickrememberMe() {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click()", rememberMeCheckbox);
	}

	public void clickLogin() {
		signInButton.click();
	}

	public void applicationLogout() {
		profileDropdown.click();
		logoutButton.click();
	}
	
	public String returnerrorMessage(){
		return errorMessage.getText();
		
	}
	
	public boolean checkLogo(){
		boolean islogoDisplayed =  logo.isDisplayed();  // true  // false
		return islogoDisplayed;
	}

}
