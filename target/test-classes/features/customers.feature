Feature: Customers API Testing

  Background:
    Given I have a corresponding endpoint

  Scenario: Create a new customer
    When I send a POST request with the following data
      | first_name | Berat   |
      | last_name  | Temizel |
    Then the response status code should be 201
    Then the response should contain the customer details
      | first_name | Berat   |
      | last_name  | Temizel |


  Scenario: Create a customer with duplicate data
    When I send a POST request with the following data
      | first_name | Berat   |
      | last_name  | Temizel |
    Then the response status code should be 409
    Then the response should contain the duplicate data error message


  Scenario: Update an existing customer
    When I send a PATCH request with the following data
      | first_name | Berat     |
      | last_name  | Temizel32 |
    Then the response status code should be 204


  Scenario: Get an existing customer
    When I send a GET request for the customer
    Then the response status code should be 200
    Then the response should contain the customer details
      | first_name | Berat     |
      | last_name  | Temizel32 |


  Scenario: Delete an existing customer
    When I send a DELETE request for the customer
    Then the response status code should be 204
