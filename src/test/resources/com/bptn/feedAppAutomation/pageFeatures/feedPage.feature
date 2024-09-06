Feature: Feed Page

 @WebTest
  Scenario: Add a new feed
    Given User is on the dashboard "/app/dashboard"
     When User clicks on the 'Upload Image' button with image "/assets/image.jpg" to add feed
     Then A cropper should appear to add feed
     When User clicks on the 'Crop' button to finalize the image for the feed
     And User enters the feed content "This is my new feed description"
     And User clicks on the 'Send' button to add feed
     Then The feed should be created successfully and User should see the message "Feed has been added."
     When User clicks on "My Feeds" which navigates to my feeds page
     Then User should see the newly created feed content "This is my new feed description" on my feeds page