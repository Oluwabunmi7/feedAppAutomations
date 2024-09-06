@FeedTest
Feature: Application Feed and FeedMetaData EndPoints

  @CreateFeedValidTest
  Scenario: Success - Create Feed API Call
    Given a valid user token for username "carlos"
     When a 'POST' request is made to "/feeds" API with the following details
       | content | My first feed |
       | picture | coding.png    |
     Then the response status code should be 200
      And Feed JSON Data received matches latest feed posted by username "carlos" in feed DB
      
  @CreateFeedInValidTest
  Scenario: Failure - Create Feed API Call - Expired Token
    Given an expired user token for username "carlos"
     When a 'POST' request is made to "/feeds" API with the following details
       | content | My first feed |
       | picture | coding.png    |
     Then the response status code should be 401
      And the response body should contain the error message "Token has Expired"
      
      
  @GetFeedValidTest
  Scenario: Success - Get Current User Feeds API Call
    Given a valid user token for username "carlos"
     When a 'GET' request is made to "/feeds/user/0/3"
     Then the response status code should be 200
      And Feed JSON Data received matches Feed DB Data for username "carlos"
 
 
 @GetFeedInValidTest
 Scenario: Failure - Get Current User Feeds API Call - Expired Token
    Given an expired user token for username "carlos"
     When a 'GET' request is made to "/feeds/user/0/3"
     Then the response status code should be 401
      And the response body should contain the error message "Token has Expired"
      
      
  @CreateFeedMetaDataValidTest
  Scenario: Success - Create FeedMetaData API Call
    Given a valid user token for username "carlos"
     When a 'POST' request is made to "/feeds/meta/1" API with the following details
       | isLike  | false                                                                           |
       | comment | Coding requires attention to detail, logical reasoning, and problem-solving skills |
     Then the response status code should be 200
      And FeedMetaData JSON Data received matches FeedMetaData DB Data for username "carlos" and feedId 1
      
  
  @CreateFeedMetaDataInValidTest
  Scenario: Failure - Create FeedMetaData API call - Expired Token
    Given an expired user token for username "carlos"
     When a 'POST' request is made to "/feeds/meta/1" API with the following details
       | isLike  | false                                                                           |
       | comment | Coding requires attention to detail, logical reasoning, and problem-solving skills |
     Then the response status code should be 401
      And the response body should contain the error message "Token has Expired"