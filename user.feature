 #Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: verify post,get, update and delete in the API

  Scenario: User will be able to create user, get all users , update and delete with the given URL
  
       #@GetAllUsers
      Given user get all users using base url with endpoint
      When User make a GET request to get all users with endpoint
      Then User get response with all the users and get status code 200
      
       
      #create an user1
      Given Admin create 1st user with base url
      When user make a post request with end point "/createusers"
      
      Then user should get response body and status code should be 201
      
      #create 2nd user2
      #Given user create 2nd user with base url and request body
      #When user make a post request for 2nd user with end point "/createusers"
      #Then user get response body 201
      
      #getuserbyuserId
      When user make a get request with an end point "/user/userId"
      Then user should get status code as "200" and responsebody 
        
      #getuserbyuserFirstName
      When user make a get request with an end point "/user/userfirstname"
      Then user should get status code as "200" and responsebody 
      
   #@updateuserbyuserId
    When User make a PUT request to update program by using userId(update 1st user) with endpoint "/updateuser/userId"
    Then User should get status code as "200" and updated response body
    
   
     #@DeleteuserbyuserFirstname
       When User make a DELETE request with an end point "/deleteuser/username/userfirstname"
      Then User gets status code "200" user will be deleted
   
  #@DeleteuserbyuserId
       When User make a DELETE request with an end point "/deleteuser/userId"
      Then User gets status code as "200" user will be deleted
   
 
    