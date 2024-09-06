@RestUserTest
Feature: Application User EndPoints

  # UserEndPointSteps  
  @RestTest
	Scenario: Success - SignUp API Call
	   When User calls the "/user/signup" Signup API with the following signup details
       | firstName | Carlos          |
       | lastName  | Zegarra         |
       | username  | carlos          |
       | password  | 123             |
       | phone     | 6471234567      |
       | emailId   | carlos@bptn.com |
     Then Status code is 200
	   And User JSON Data received matches User DB Data for username "carlos"
	   
	   
  	@RestTest
	Scenario: Failure - SignUp API Call - User Already Exists
	   When User calls the "/user/signup" Signup API with the following signup details
       | firstName | Carlos          |
       | lastName  | Zegarra         |
       | username  | carlos          |
       | password  | 123             |
       | phone     | 6471234567      |
       | emailId   | carlos@bptn.com |
	   Then Status code is 400
	   And JSON error message is 
       | httpStatus | BAD_REQUEST                     |
       | reason     | BAD REQUEST                     |
       | message    | Username already exists, carlos |
        
  @RestTest
	Scenario: Failure -  SignUp API Call - Email Already Exists
	   When User calls the "/user/signup" Signup API with the following signup details
       | firstName | Carlos          |
       | lastName  | Zegarra         |
       | username  | carlos1         |
       | password  | 123             |
       | phone     | 6471234567      |
       | emailId   | carlos@bptn.com |
	   Then Status code is 400
	   And JSON error message is
       | httpStatus | BAD_REQUEST                           |
       | reason     | BAD REQUEST                           |
       | message    | Email already exists, carlos@bptn.com |  
       
       
  @RestTest
	Scenario: Failure - User Login API Call - Email Not Verified
	  Given User has username "carlos" and password "123"
	   When User calls the "/user/login" Login API
	   Then Status code is 400
	    And JSON error message is
       | httpStatus     | BAD_REQUEST                                  |
       | reason         | BAD REQUEST                                  |
       | message        | Email requires verification, carlos@bptn.com |
       
 
  @RestTest
  Scenario: Success - Verify Email API Call
    Given User "carlos" has email with a valid JWT token
	   When User calls the "/user/verify/email" Verify Email API
	   Then Status code is 200    
	    And User "carlos" is active in DB.    

  @RestTest
  Scenario: Failure - Verify Email API Call - User doesn't exist
    Given User "carlos1" has email with a valid JWT token
	   When User calls the "/user/verify/email" Verify Email API
	   Then Status code is 400    
	    And JSON error message is   
       | httpStatus     | BAD_REQUEST                     |
       | reason         | BAD REQUEST                     |
       | message        | Username doesn't exist, carlos1 |
       
       
  @RestTest
	Scenario: Failure - User Login API Call - Invalid Credentials
	  Given User has username "carlos" and password "1234"
	   When User calls the "/user/login" Login API
	   Then Status code is 400    
	    And JSON error message is   
       | httpStatus     | BAD_REQUEST                                         |
       | reason         | BAD REQUEST                                         |
       | message        | Username or Password is Incorrect. Please try again |
       
  @RestTest
	Scenario: Success - User Login API Call
	  Given User has username "carlos" and password "123"
	   When User calls the "/user/login" Login API
	   Then Status code is 200
	    And Authorization Header exists in the Response
	    And User JSON Data received matches User DB Data for username "carlos"
	    
	@RestTestOutline
  Scenario Outline: Success - User Login API Call for "<username>"
	  Given User has username "<username>" and password "<password>"
	   When User calls the "/user/login" Login API
	   Then Status code is 200
	    And Authorization Header exists in the Response
	    And User JSON Data received matches User DB Data for username "<username>"
    Examples:
       |username  |password |
       |carlos    |123      | 
       
  @RestTest
	Scenario: Success - Get User API Call
    Given User "carlos" has a valid JWT token	  
	   When User calls the "/user/get" Get User API
	   Then Status code is 200
	    And User JSON Data received matches User DB Data for username "carlos" 