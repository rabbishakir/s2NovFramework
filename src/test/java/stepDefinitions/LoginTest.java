package stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.openqa.selenium.WebElement;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import maven.selenium.s2s.automation.*;
public class LoginTest {
	private Scenario scenario;
	private String testType;
	public BrowserController browser;
	public static Logger logger;
	@Before("@Login")
	public void beforeScenario(Scenario sc) {
		this.scenario = sc;
		ArrayList<String> tags = (ArrayList<String>) this.scenario.getSourceTagNames();
		testType = tags.get(0);
	}
	@Given("Automation tools are loaded and running")
	public void automation_tools_are_loaded_and_running() {
	    // Write code here that turns the phrase above into concrete actions
		logger = Logger.getLogger(getClass().getName());
		try {
			browser = new BrowserController();
			browser.waitFor(500);
			assert true;
		}catch(Exception e) {
			assert false;
		}
	}
	@And("User is on the login page")
	public boolean user_is_on_the_login_page() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement e = browser.findByXPath("/html/head/title[contains(text(),'Login')]");
		boolean LoginContains = e.getDomProperty("innerText").contains("Login");
		return LoginContains;
	}
	@When("the user enters username {string} and password {string}")
	public void the_user_enters_username_and_password(String email, String password) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Started! With User: "+ email + " Password :"+password!=null?password:"(null)");
		WebElement emailElem = browser.findByXPath("//input[@name='email']");
		WebElement passwElem = browser.findByXPath("//input[@name='password']");
		browser.type(emailElem, email);
		browser.type(passwElem, password);
		browser.waitFor(1500);
	}

	@When("clicks the {string} button")
	public void clicks_the_button(String string) {
	    // Write code here that turns the phrase above into concrete actions
		WebElement rememElem = browser.findByXPath("//label[@class='kt-checkbox']");
		WebElement loginElem = browser.findByXPath("//button[@id='kt_login_signin_submit']");
		browser.waitFor(200);
		browser.click(rememElem);
		browser.waitFor(1500);
		browser.click(loginElem);
	}

	@Then("the system should display {string}.")
	public void the_system_should_display(String string) {
	    // Write code here that turns the phrase above into concrete actions
		browser.waitFor(2000);
		try {
			if(testType.contains("@positive")) {
				WebElement dashboard = browser.findByXPath("//h3[text()='Dashboard']");
				System.out.println(dashboard.getText());
				assertEquals(dashboard.getText(),"Dashboard");
			}else if (testType.contains("@negative")) {
				assertEquals(errorLogin(string), string);
			}
		}catch(Exception e) {
			fail();
		}
	}
	public String errorLogin(String error) {
		browser.setImplicitWait(10);
		if(browser.isElementPresent("//div[contains(text(),'"+error+"')]", 10)) {
			WebElement elem = browser.findByXPath("//div[contains(text(),'do not match our records')]");
			return elem.getText();
		}else {
			return null;
		}
	}
	@After("@Login")
	public void closeBrowser() {
		browser.waitFor(2000);
		browser.quitBrowser();
	}
}
