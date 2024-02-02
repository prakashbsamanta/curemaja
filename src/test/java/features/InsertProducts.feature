Feature: Insert product using post api

  Scenario Outline: Verify the post api for inserting products works correctly
    Given I hit the url of post product api endpoint
    When I pass the url of products in the request
    And I pass the request body of product title <productTitle>
    Then I receive the response code as 200
    And I receive the response body id as <id>
    Examples:
      | productTitle | id |
      | keychron     | 21 |