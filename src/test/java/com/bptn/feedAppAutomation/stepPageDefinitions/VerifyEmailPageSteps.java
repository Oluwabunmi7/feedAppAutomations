package com.bptn.feedAppAutomation.stepPageDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.bptn.feedAppAutomation.jwt.JwtService;
import com.bptn.feedAppAutomation.pageObjects.VerifyEmailPage;
import com.bptn.feedAppAutomation.provider.ResourceProvider;

public class VerifyEmailPageSteps {

	@Autowired
	ResourceProvider resourceProvider;

	@Autowired
	VerifyEmailPage verifyEmailPage;

	@Autowired
	JwtService jwtService;

	String jwtToken;

	@Given("User {string} has an email with a valid JWT token")
	public void user_has_an_email_with_a_valid_JWT_token(String username) {

		this.jwtToken = this.jwtService.generateJwtToken(username, this.resourceProvider.getJwtExpiration());
	}

	@When("User opens the verify email page {string}")
	public void user_opens_the_verify_email_page(String verifyEmailPageRoute) {

		String verifyEmailPageRouteWithToken = verifyEmailPageRoute + "?token=" + this.jwtToken;
		this.verifyEmailPage.openVerifyEmailPage(verifyEmailPageRouteWithToken);
	}

	@Then("User should see the message {string} on the verify email page.")
	public void user_should_see_the_message_on_the_verify_email_page(String message) {

		assertEquals(message, this.verifyEmailPage.getMessage());
	}
}