package com.bptn.feedAppAutomation.stepPageDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.bptn.feedAppAutomation.pageObjects.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {
	
	@Autowired
	LoginPage loginPage;

	
	@Given("User is on the login page {string}")
	public void user_is_on_the_login_page(String loginPageRoute) {
		this.loginPage.openLoginPage(loginPageRoute);
	}
	

	@When("User logs into the application with username {string} and password {string}")
	public void user_logs_into_the_application_with_username_and_password(String username, String password) {
	    this.loginPage.setUsername(username);
	    this.loginPage.setPassword(password);
	}

	@And("User clicks 'Submit' on the login page")
	public void user_clicks_submit_on_the_login_page() {
		this.loginPage.clickSubmit();
	}
	

	@Then("User should see the message {string} on the login page")
	public void user_should_see_the_message_on_the_login_page(String message) {
		assertEquals(message,this.loginPage.getMessage());
	}
	
	@Then("User should navigate to {string} to complete their user profile")
	public void user_should_navigate_to_complete_user_profile(String profileRoute) {
	    assertEquals(profileRoute,this.loginPage.getPageRoute());
	}
}