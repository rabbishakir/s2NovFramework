package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import maven.selenium.s2s.automation.BrowserController;
import pageObjects.*;
public class UserManagement {
	
	private BrowserController control;
	private UserCrudPageObject pageObj;
	private Authentication authObj;
	private Map<String,String> EnterData;
	private Scenario scenario;
	private WebElement userToDel;
	private String xpathUserSearchString;
	@Before("@Dashboard")
	void loadScenario(Scenario sc) {
		this.scenario = sc;
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Given("Admin is logged into the dashboard page")
	public void admin_is_logged_into_the_dashboard_page() {
	    // Write code here that turns the phrase above into concrete actions
		try {
			control = new BrowserController();
			authObj = new Authentication(control.getDriver());
			authObj.enteruserName("admin@admin.com");
			authObj.enterPassword("123456");
			authObj.clickrememberMe();
			authObj.clickLogin();
			control.waitFor(1000);
			if(control.isElementPresent("/html/head/title[contains(text(),'Dashboard')]", 10)) {
				pageObj = new UserCrudPageObject(control.getDriver());
				assert true;
			}else {
				assert false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("the admin clicks on the {string} menu")
	public void the_admin_clicks_on_the_menu(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    pageObj.userButton.click();
	    control.waitFor(500);
	}

	@When("selects {string} from the dropdown")
	public void selects_from_the_dropdown(String string) {
	    // Write code here that turns the phrase above into concrete actions
		pageObj.viewUserBtn.click();
	    control.waitFor(500);
	}

	@Then("the system should display the {string} page")
	public void the_system_should_display_the_page(String string) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("ManageUsers: "+ string);
		
	    assert pageObj.ManageUsers.getText().contains(string);
	}

	@Then("the page should show a list of existing users")
	public void the_page_should_show_a_list_of_existing_users() {
	    // Write code here that turns the phrase above into concrete actions
	    try {
			assert pageObj.countUsers() > 0;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("the page should have an {string} button")
	public void the_page_should_have_an_button(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    assert pageObj.addusers.getText().contains(string);
	}

	@When("the admin is on the {string} page")
	public void the_admin_is_on_the_page(String string) {
	    // Write code here that turns the phrase above into concrete actions
		pageObj.userButton.click();
	    control.waitFor(500);
	    pageObj.viewUserBtn.click();
	    control.waitFor(500);
	    assert pageObj.ManageUsers.getText().contains(string);
	}

	@Then("the admin clicks on the {string} button")
	public void the_admin_clicks_on_the_button(String string) {
	    // Write code here that turns the phrase above into concrete actions
		control.waitFor(1000);
		pageObj.addusers.click();
	}

	@Then("the {string} form should be displayed with fields:")
	public void the_form_should_be_displayed_with_fields(String string, io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		control.waitFor(1000);
	    assert pageObj.addUserFormPage.getText().contains(string);
	    List<WebElement> entries = control.getDriver().findElements(By.xpath("//div[@class='kt-portlet__body']/div[@class='form-group']/label"));
	    List<String> list= dataTable.asList();
	    for(int i=0;i<list.size();i++) {
	    	if(!entries.get(i).getDomProperty("innerText").contains(list.get(i))) assert false;
	    }
	    assert true;
	}

	@Then("the form should have status options:")
	public void the_form_should_have_status_options(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		control.waitFor(1000);
	    List<String> testData = dataTable.asList();
	    List<WebElement> entry = control.getDriver().findElements(
	    		By.xpath("//div[@class='kt-radio-inline']/label")
	    );
	    for(int i=0; i< testData.size();i++) {
//	    	System.out.println(entry.get(i).getText());
	    	if(!testData.get(i).contains(entry.get(i).getText())) assert false;
	    }
	    assert pageObj.addUserFormPage.getText().contains("Add User");
	}

	@When("the admin is on the {string} form")
	public void the_admin_is_on_the_form(String string) {
	    // Write code here that turns the phrase above into concrete actions
		control.waitFor(1000);
		pageObj.userButton.click();
	    control.waitFor(500);
	    pageObj.viewUserBtn.click();
	    control.waitFor(500);
	    pageObj.addusers.click();
		assert pageObj.addUserFormPage.getText().contains(string);
	}

	@Then("the admin enters:")
	public void the_admin_enters(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		control.waitFor(1000);
	    Map<String,String> entry = dataTable.asMap();
	    String name = entry.get("Name");
	    String email = entry.get("Email");
	    String Roles = entry.get("Roles");
	    String password = entry.get("Password");
	    String confirm = entry.get("Confirm Password");
	    String status = entry.get("Status");
	    pageObj.namefield.sendKeys(name);
	    pageObj.emailfield.sendKeys(email);
	    if(Roles.contains("Admin")) {
	    	pageObj.selectroles.click();
		    pageObj.selectadmin.click();
	    }
	    pageObj.enterpasswordfield.sendKeys(password);
		pageObj.confirmpasswordfield.sendKeys(confirm);
	}

	@When("selects status {string}")
	public void selects_status(String string) {
	    // Write code here that turns the phrase above into concrete actions
		if(string.contains("Active"))
			pageObj.activeradio.click();
		else pageObj.disableradio.click();
	}
	
	@Then("the admin clicks the {string} button")
	public void the_admin_clicks_the_button(String string) {
	    // Write code here that turns the phrase above into concrete actions
		control.waitFor(500);
		pageObj.adduserFormBtn.click();
	}
	
	@Then("A toast message should should appear celebrating")
	public void the_user_should_appear_in_the_user_list() {
	    // Write code here that turns the phrase above into concrete actions
		control.waitFor(1000);
	    WebElement toast= control.findByXPath("//div[@class='toast-message']");
	    assert toast != null;
	    System.out.println(toast.getText());
	    control.waitFor(1000);
	}

	@Given("a user named {string} exists in the list")
	public void a_user_named_exists_in_the_list(String string) {
	    // Write code here that turns the phrase above into concrete actions
		xpathUserSearchString = "//table[contains(@class,'kt-datatable__table')]/tbody/tr/td[text()='"+string+"']/parent::tr/td/div/button";
		userToDel = control.findByXPath(xpathUserSearchString);
		assert userToDel != null;
		
		
	}

	@When("the admin clicks the gear icon for {string}")
	public void the_admin_clicks_the_gear_icon_for(String string) {
	    // Write code here that turns the phrase above into concrete actions
		userToDel.click();
		control.waitFor(1000);
	}

	@When("selects the {string} option")
	public void selects_the_option(String string) {
	    // Write code here that turns the phrase above into concrete actions
		xpathUserSearchString = xpathUserSearchString + "/parent::div/div/button[contains(text(),'"+string+"')]";
		WebElement userRemoveBtn = control.findByXPath(xpathUserSearchString);
		userRemoveBtn.click();
	}

	@Then("the system should prompt for confirmation")
	public void the_system_should_prompt_for_confirmation() {
	    // Write code here that turns the phrase above into concrete actions
		control.waitFor(2000);
		String popupact = "/html/body/div[contains(@class,'swal2-container')]/div[contains(@class,'swal2-popup')]";
	    WebElement popup = control.findByXPath(popupact);
	    assert popup!=null;
	    popupact = popupact + "/div[@class='swal2-actions']/button[@class='swal2-confirm']";
	    WebElement confirmBtn = control.findByXPath(popupact);
	    control.waitFor(1000);
	    confirmBtn.click();
	}

	@When("the admin confirms the deletion")
	public void the_admin_confirms_the_deletion() {
	    // Write code here that turns the phrase above into concrete actions
		control.waitFor(200);
	    WebElement toast= control.findByXPath("//div[@class='toast-message']");
	    assert toast != null;
	    System.out.println(toast.getText());
	    control.waitFor(1000);
	}

	@Then("the user {string} should no longer appear in the list")
	public void the_user_should_no_longer_appear_in_the_list(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    String userSearch = "//table[contains(@class,'kt-datatable__table')]/tbody/tr/td[text()='"+string+"']";
	    control.setImplicitWait(0);
	    List<WebElement> findElem = control.findByXpathList(userSearch);
	    System.out.println("element size:"+ findElem.size());
	    assert findElem.size() ==0;
	}
	@After("@Dashboard")
	public void clean() {
		control.quitBrowser();
	}
}
