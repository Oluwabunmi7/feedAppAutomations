package com.bptn.feedAppAutomation.stepRestDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bptn.feedAppAutomation.bean.User;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class UserEndPointSteps extends EndPointSteps {
	
	@When("User calls the {string} Signup API with the following signup details")
	public void user_calls_the_api_with_the_following_signup_details(String path, DataTable data) throws JsonProcessingException {
		this.userEndPoint.createRequestWithData(data);
		this.response = this.userEndPoint.sendPostRequest(path);
	}

	@Then("Status code is {int}")
	public void status_code_is(Integer statusCode) {
		
		assertEquals(statusCode, this.response.getStatusCode());    
	}

	@And("User JSON Data received matches User DB Data for username {string}")
	public void user_json_data_received_matches_user_db_data_for_username(String username) {

		User user = this.userDao.findByUsername(username);
		
		assertEquals(user.getUserId(),this.response.jsonPath().getInt("userId"));
		assertEquals(user.getFirstName(),this.response.jsonPath().getString("firstName"));
		assertEquals(user.getLastName(),this.response.jsonPath().getString("lastName"));
		assertEquals(user.getUsername(),this.response.jsonPath().getString("username"));
		assertEquals(user.getPhone(),this.response.jsonPath().getString("phone"));
		assertEquals(user.getEmailId(),this.response.jsonPath().getString("emailId"));
		assertEquals(user.getEmailVerified(),this.response.jsonPath().getBoolean("emailVerified"));
	}
	
	@And("JSON error message is")
	public void json_error_message_is(DataTable data) {
		 
     assertEquals(data.asMap().get("httpStatus"),this.response.jsonPath().getString("httpStatus"));
	 assertEquals(data.asMap().get("reason"),this.response.jsonPath().getString("reason"));
	 assertEquals(data.asMap().get("message"),this.response.jsonPath().getString("message"));
	}
	
	@Given("User has username {string} and password {string}")
	public void user_has_username_and_password(String username, String password) throws JsonProcessingException {
	    
		this.userEndPoint.createLoginRequest(username, password);
	}

	@When("User calls the {string} Login API")
	public void user_calls_the_login_api(String path) {
	    
		this.response = this.userEndPoint.sendPostRequest(path);
	}
	
	@Given("User {string} has email with a valid JWT token")
	public void user_has_email_with_a_valid_jwt_token(String username) throws JsonProcessingException {
	    this.jwtToken = this.jwtService.generateJwtToken(username, this.resourceProvider.getJwtExpiration());
	    this.userEndPoint.createRequestWithToken(this.jwtToken);
	}
	
	@When("User calls the {string} Verify Email API")
	public void user_calls_the_verify_email_api(String path) {
		this.response = this.userEndPoint.sendGetRequest(path);
	}
	
	@And("User {string} is active in DB.")
	public void user_is_active_in_db(String username) {
		User user = this.userDao.findByUsername(username);
		assertTrue(user.getEmailVerified(),"Email should be verified for User: " + username);
	}
	
	@And("Authorization Header exists in the Response")
	public void authorization_header_exists_in_the_response() {
	                                    	assertTrue(this.response.getHeaders().hasHeaderWithName(AUTHORIZATION),"Authorization Header should exist.");
			
		this.context.setToken(this.response.getHeader(AUTHORIZATION));
	}
	
	@Given("User {string} has a valid JWT token")
	public void user_has_a_valid_jwt_token(String username) throws JsonProcessingException {
		this.jwtToken = this.context.getToken();
		
		assertNotNull(this.jwtToken);
		assertEquals(username,this.jwtService.getSubject(this.jwtToken));
		this.userEndPoint.createRequestWithToken(this.jwtToken);
	}

	@When("User calls the {string} Get User API")
	public void user_calls_the_get_user_api(String path) {
		this.response = this.userEndPoint.sendGetRequest(path);
	}
}