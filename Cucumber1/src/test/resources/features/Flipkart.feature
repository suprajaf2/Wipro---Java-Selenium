@flipkart 
Feature: Flipkart Search Functionality
 @positive 
 Scenario: Successful Product Search
   Given launch Flipkart website
   When  search for product "Laptop"
   Then  should see the search results


 @negative
 Scenario: Invalid Product Search
   Given launch Flipkart website
   When search for product "asdfghjklzxcvbnm"
   Then should see no results message