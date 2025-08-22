Feature: Login functionality
 Scenario:
	: Successful Login
   Given I open the browser and launch the login page
   When I login with username "<username>" and password "<password>"
   Then I should see the home page
  
   Examples: Successful Login
	|username|password|
	|sup123  |hi123  |
	|supraja |1234   |


  Scenario: Failed login
   Given I open the browser and launch the login page
   When I login with username "<username>" and password "<password>"
   Then I should see an error message
  Examples: Failed login
 	|username  |password|
	|Supraja   |S123	|
	|Indraja   |I120    |
