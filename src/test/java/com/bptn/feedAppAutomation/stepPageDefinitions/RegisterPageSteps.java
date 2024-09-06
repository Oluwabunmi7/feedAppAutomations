package com.bptn.feedAppAutomation.stepPageDefinitions;

import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bptn.feedAppAutomation.pageObjects.RegisterPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

public class RegisterPageSteps {

	@Autowired
	RegisterPage registerPage;

	@Given("User is on the register page {string}")
	public void user_is_on_the_register_page(String registerPageRoute) {
		this.registerPage.openRegisterPage(registerPageRoute);
	}

	@When("User registers to the application with the following user details:")
	public void user_registers_to_the_application_with_the_following_user_details(DataTable data) {

		this.registerPage.setFirstName(data.asMap().get("firstName"));
		this.registerPage.setLastName(data.asMap().get("lastName"));
		this.registerPage.setUsername(data.asMap().get("username"));
		this.registerPage.setPassword(data.asMap().get("password"));
		this.registerPage.setPhone(data.asMap().get("phone"));
		this.registerPage.setEmail(data.asMap().get("email"));
	}

	@And("User clicks 'Submit' on the register page")
	public void user_clicks_on_the_register_page() {
		this.registerPage.clickSubmit();
	}

	@Then("User should see the message {string} on the register page")
	public void user_should_see_the_message_on_the_register_page(String message) {
		assertEquals(message, this.registerPage.getMessage());
	}
	


}
