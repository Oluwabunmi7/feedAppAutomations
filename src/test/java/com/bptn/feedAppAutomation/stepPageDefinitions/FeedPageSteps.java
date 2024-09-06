package com.bptn.feedAppAutomation.stepPageDefinitions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.bptn.feedAppAutomation.pageObjects.FeedPage;

public class FeedPageSteps {
	
	@Autowired
	FeedPage feedPage;

	
	@Given("User is on the dashboard {string}")
	public void user_is_on_the_dashboard(String dashboardPageRoute) {
	    assertEquals(dashboardPageRoute,this.feedPage.getPageRoute());
	}

	@When("User clicks on the 'Upload Image' button with image {string} to add feed")
	public void user_clicks_on_the_upload_image_button_with_image_to_add_feed(String imagePath)  {
	    this.feedPage.openImageSelector(imagePath);
	}

	@Then("A cropper should appear to add feed")
	public void a_cropper_should_appear_to_add_feed()  {
	    assertTrue(this.feedPage.validateCropperModal());
	}

	@And("User clicks on the 'Crop' button to finalize the image for the feed")
	public void user_clicks_on_the_crop_button_to_finalize_the_image_for_the_feed() {
	    this.feedPage.clickCrop();
	}

	@When("User enters the feed content {string}")
	public void user_enters_the_feed_content(String content) {
	    this.feedPage.setFeedContent(content);
	}

	@And("User clicks on the 'Send' button to add feed")
	public void users_clicks_on_the_send_button_to_add_feed()  {
	    this.feedPage.clickSend();
	}

	
	@Then("The feed should be created successfully and User should see the message {string}")
	public void the_feed_should_be_created_successfully_and_user_should_see_the_message(String message) {
		assertEquals(message,this.feedPage.getMessage());
	}

	@When("User clicks on {string} which navigates to my feeds page")
	public void user_clicks_on_my_feeds_and_navigate_to_my_feeds_page(String myFeedsTitle) {
	    this.feedPage.navigateToMyFeeds(myFeedsTitle);
	}

	@Then("User should see the newly created feed content {string} on my feeds page")
	public void user_should_see_the_newly_created_feed_on_my_feeds_page(String content) {
	    assertTrue(this.feedPage.getFeedContent().contains(content));
	}
}