package stepDef;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import utils.DataHelper;

public class StepDef_Test1 {
	HomePage homePage = new HomePage();
	LoginPage loginPage = new LoginPage();
	
	@Given("user open the application")
	public void user_open_the_application() {
	   System.out.println("Launched the application......");
	   HashMap<String, String>  textData =  DataHelper.readDataRowWise("src/test/resources/TestData/CRM.xlsx","Test1","CRM");
	System.out.println(textData.isEmpty());
	   System.out.println(textData.get("Title_Text"));
	}

	@When("user validate the title")
	public void user_validate_the_title() {
	  String actualTitle = Hooks.driver.getTitle();
	  String expectedTitle = homePage.Title_Text;
	  System.out.println("Actual Title is : "+actualTitle);
	  Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Then("user validate the Sign up elements")
	public void user_validate_the_sign_up_elements() {
	   Hooks.driver.findElement(By.xpath(homePage.signUpBtn_Locator)).isDisplayed();
	}

	@When("user click on Login button")
	public void user_click_on_login_button() {
	    Hooks.driver.findElement(By.xpath(loginPage.loginBtn_Locator)).click();
	}

	@Then("user validate the login page")
	public void user_validate_the_login_page() {
		Hooks.driver.findElement(By.xpath(loginPage.login_Locator)).isDisplayed();
	}

	@When("user enter the username")
	public void user_enter_the_username() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hooks.driver.findElement(By.xpath(loginPage.username_Locator)).sendKeys(loginPage.username);
	}

	@Then("user enter the password")
	public void user_enter_the_password() {
		Hooks.driver.findElement(By.xpath(loginPage.password_Locator)).sendKeys(loginPage.password);
	}

	@Then("use click on login button")
	public void use_click_on_login_button() {
		Hooks.driver.findElement(By.xpath(loginPage.login_Locator)).click();
	}

	@Then("validate the login")
	public void validate_the_login() {
	    
	}

}
