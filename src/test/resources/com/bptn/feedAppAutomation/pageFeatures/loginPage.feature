Feature: Login Page

  # LoginPageSteps
  @WebTest
  Scenario: Failure - User Login - Email Not Verified
    Given User is on the login page "/user/login"
    When User logs into the application with username "iyuvraajsingh" and password "Hello@123"
    And User clicks 'Submit' on the login page
    Then User should see the message "Email requires verification, me@iyuvraajsingh.com" on the login page

  # VerifyEmailPageSteps
  @WebTest
  Scenario: Success - Verify Email - New User
    Given User "iyuvraajsingh" has an email with a valid JWT token
    When User opens the verify email page "/user/verifyEmail"
    Then User should see the message "Your account has been verified." on the verify email page.

  # LoginPageSteps
  @WebTest
  Scenario Outline: Failure - User Login - Invalid Credentials
    Given User is on the login page "/user/login"
    When User logs into the application with username "<username>" and password "<password>"
    And User clicks 'Submit' on the login page
    Then User should see the message "Username or Password is Incorrect. Please try again" on the login page

    Examples: 
      | username | password     |
      | bad-user | bad-password |
      | bad-user |          123 |

  @WebTest
  Scenario: Success - User Login - New User
    Given User is on the login page "/user/login"
    When User logs into the application with username "iyuvraajsingh" and password "Hello@123"
    And User clicks 'Submit' on the login page
    Then User should navigate to "/app/completeProfile" to complete their user profile

  # ProfilePageSteps
  @WebTest
  Scenario: Success - User Login - Complete New User Profile
    Given User is on the complete user profile page "/app/completeProfile"
    When User clicks on the 'Upload Photo' button with image "/assets/image.jpg"
    Then A cropper should appear on the complete user profile page
    When User clicks on the 'Crop' button to finalize the image on the complete user profile page
    And User enters the new user profile details
      | bio      | This is a sample bio      |
      | city     | Vancouver                 |
      | country  | Canada                    |
      | headline | This is a sample headline |
    And User clicks 'Save' on the complete profile page
    Then User should navigate to "/app/dashboard" dashboard
