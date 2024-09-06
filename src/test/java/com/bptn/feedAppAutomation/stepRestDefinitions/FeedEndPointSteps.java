package com.bptn.feedAppAutomation.stepRestDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bptn.feedAppAutomation.bean.Feed;
import com.bptn.feedAppAutomation.bean.User;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.bptn.feedAppAutomation.bean.FeedMetaData;

public class FeedEndPointSteps extends EndPointSteps{

	
	@Given("a valid user token for username {string}")
	public void a_valid_user_token(String username){

		this.jwtToken = this.context.getToken();
		assertNotNull(this.jwtToken);
		assertEquals(username,this.jwtService.getSubject(this.jwtToken));
	}

	@When("a 'POST' request is made to {string} API with the following details")
	public void a_post_request_is_made_to_api_with_the_following_details(String path, DataTable data) throws JsonProcessingException {

		this.feedEndPoint.createRequestWithTokenAndData(this.jwtToken, data);
		this.response = this.feedEndPoint.sendPostRequest(path);
	}

	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(Integer statusCode) {
		assertEquals(statusCode, this.response.getStatusCode());
	}

	@And("Feed JSON Data received matches latest feed posted by username {string} in feed DB")
	public void feed_json_data_received_matches_latest_feed_posted_by_username_in_feed_DB(String username) {

		User user = this.userDao.findByUsername(username);
		Feed latestFeed = this.feedDao.getLatestFeedByUser(user);

		assertEquals(latestFeed.getFeedId(), this.response.jsonPath().getInt("feedId"));
		assertEquals(latestFeed.getPicture(), this.response.jsonPath().getString("picture"));
		assertEquals(latestFeed.getContent(), this.response.jsonPath().getString("content"));
	}
	
	@Given("an expired user token for username {string}")
	public void an_expired_user_token(String username) {
		this.jwtToken = this.jwtService.generateJwtToken(username, 0);
	}

	@And("the response body should contain the error message {string}")
	public void the_response_body_should_contain_the_error_message(String message) {

		assertEquals(401, this.response.jsonPath().getInt("httpStatusCode"));
		assertEquals(UNAUTHORIZED, this.response.jsonPath().getString("httpStatus"));
		assertEquals(UNAUTHORIZED, this.response.jsonPath().getString("reason"));
		assertEquals(message, this.response.jsonPath().getString("message"));
	}
	
	@When("a 'GET' request is made to {string}")
	public void a_get_request_is_made_to(String path) {

		this.feedEndPoint.createRequestWithToken(this.jwtToken);

		this.response = this.feedEndPoint.sendGetRequest(path);
	}

	@And("Feed JSON Data received matches Feed DB Data for username {string}")
	public void feed_json_data_received_matches_feed_db_data_for_username(String username) {

		User user = this.userDao.findByUsername(username);
		Page<Feed> paged = this.feedDao.findByUser(user, PageRequest.of(0, 3));

		// Assert the values match the first item in the PageResponse
		assertEquals(paged.getContent().get(0).getFeedId(), this.response.jsonPath().getInt("content[0].feedId"));
		assertEquals(paged.getContent().get(0).getPicture(), this.response.jsonPath().getString("content[0].picture"));
		assertEquals(paged.getContent().get(0).getContent(), this.response.jsonPath().getString("content[0].content"));
	}
	
	@And("FeedMetaData JSON Data received matches FeedMetaData DB Data for username {string} and feedId {int}")
	public void feed_meta_data_json_data_received_matches_feed_meta_data_db_data_for_username(String username, int feedId ) {
	
		User user = this.userDao.findByUsername(username);
		FeedMetaData latestFeedMetaData= this.feedMetaDataDao.getLatestFeedMetaDataByUserForFeedId(user, feedId);
			
		assertEquals(latestFeedMetaData.getFeedMetaDataId(), this.response.jsonPath().getInt("feedMetaDataId"));
		assertEquals(latestFeedMetaData.getComment(), this.response.jsonPath().getString("comment"));
		assertEquals(latestFeedMetaData.getIsLike(), this.response.jsonPath().getBoolean("isLike"));		
	}
}