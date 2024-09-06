package com.bptn.feedAppAutomation.stepPageDefinitions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.bptn.feedAppAutomation.pageObjects.ProfilePage;

public class ProfilePageSteps {
	
	@Autowired
	ProfilePage profilePage;
	
	@Given("User is on the complete user profile page {string}")
	public void user_is_on_the_complete_user_profile_page(String profileRoute) {
	    assertEquals(profileRoute,this.profilePage.getPageRoute());
	}

	@When("User clicks on the 'Upload Photo' button with image {string}")
	public void user_clicks_on_the_upload_photo_button_with_image(String imagePath)  {
		this.profilePage.openImageSelector(imagePath);
	}

	@Then("A cropper should appear on the complete user profile page")
	public void a_cropper_should_appear_on_complete_user_profile_page()  {
		assertTrue(this.profilePage.validateCropperModal());
	}

	@When("User clicks on the 'Crop' button to finalize the image on the complete user profile page")
	public void user_clicks_on_the_crop_button_to_finalize_the_image_on_complete_user_profile_page() {
	    this.profilePage.clickCrop();
	}

	@And("User enters the new user profile details")
	public void user_enters_the_new_user_profile_details(DataTable data) {
	    this.profilePage.setBio(data.asMap().get("bio"));
	    this.profilePage.setCity(data.asMap().get("city"));
	    this.profilePage.setCountry(data.asMap().get("country"));
	    this.profilePage.setHeadline(data.asMap().get("headline"));
	}

	@And("User clicks 'Save' on the complete profile page")
	public void user_clicks_save_on_the_complete_profile_page() {
	    this.profilePage.clickSave();
	}

	@Then("User should navigate to {string} dashboard")
	public void user_should_navigate_to_dashboard(String dashboardRoute) {
	    assertEquals(dashboardRoute, this.profilePage.getPageRoute());
	}

}