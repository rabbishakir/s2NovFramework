package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserCrudPageObject {
	WebDriver driver;
	
	@FindBy(xpath = "//span[text()='Users']/parent::a")
	public WebElement userButton;
	
	@FindBy(xpath = "//a[contains(@href, 'staff/users')]")
	public WebElement viewUserBtn;

	@FindBy(xpath = "//a[contains(@href,'staff/users/create')]")
	public WebElement addusers;
	
	@FindBy(xpath = "//h3[text()='Manage Users']/parent::div")
	public WebElement ManageUsers;
	
	@FindBy(xpath = "//h3[text()='Add User']/parent::div")
	public WebElement addUserFormPage;
	
	@FindBy(xpath = "//input[@placeholder='Enter name']")
	public WebElement namefield;

	@FindBy(xpath = "//input[@id='email']")
	public WebElement emailfield;

	@FindBy(xpath = "//input[@placeholder='Enter password']")
	public WebElement enterpasswordfield;

	@FindBy(xpath = "//input[@placeholder='Confirm password']")
	public WebElement confirmpasswordfield;

	@FindBy(xpath = "//ul[@class='select2-selection__rendered']")
	public WebElement selectroles;

	@FindBy(xpath = "//li[@role='option']")
	public WebElement selectadmin;

	@FindBy(xpath = "//label[@class='kt-radio kt-radio--success']")
	public WebElement activeradio;
	
	@FindBy(xpath = "//label[@class='kt-radio kt-radio--warning']")
	public WebElement disableradio;

	@FindBy(xpath = "//span[text()='Add User']")
	public WebElement adduserFormBtn;

	@FindBy(xpath = "//*[@class='kt-datatable__body']")
	WebElement parentTable;
	
	public UserCrudPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickUserbuttons() {
		userButton.click();
		viewUserBtn.click();
	}
	
	public void fillusesrsform(String name, String email, String password, String conpassword) {

		addusers.click();
		namefield.sendKeys(name);
		emailfield.sendKeys(email);
		selectroles.click();
		selectadmin.click();
		enterpasswordfield.sendKeys(password);
		confirmpasswordfield.sendKeys(conpassword);
		activeradio.click();
		adduserFormBtn.click();

	}
	public int countUsers() throws InterruptedException {
		Thread.sleep(3000);
		WebElement datatableBody = driver.findElement(By.xpath("//*[@class='kt-datatable__body']"));   // found the parent 
	    List<WebElement> rows = datatableBody.findElements(By.xpath("//*[@class='kt-datatable__row']"));
	    int rowCount = rows.size();
	    return rowCount;

	}
}
