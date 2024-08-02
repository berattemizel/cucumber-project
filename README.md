# Customer API Cucumber Test Suite

This project is a Cucumber test suite for testing a REST microservice that manages customer records. 
The tests are written in Java and use Rest-Assured for making HTTP requests. 

## Prerequisites

1. **Docker**: Make sure Docker is installed and running on your PC.
2. **Java**: Ensure you have Java installed.
3. **Maven**: Ensure you have Maven installed.

## Setup

### Step 1: Run the Docker Container

1. Extract the ZIP file attached to the provided directory.
2. Open your Terminal and navigate to the `quarkus` directory.
3. Run the following commands:

    ```bash
    docker build -f Dockerfile.jvm -t quarkus/quarkus-jvm .
    docker run --name customers -i --rm -p 8080:8080 quarkus/quarkus-jvm
    ```

The first command will build the Docker image, which might take a few minutes. The second command will start the Docker container.

Once the container is up and running, the APIs will be accessible at `http://localhost:8080/customers`.

### Step 2: Clone the Test Suite Repository

1. Clone this repository to your local machine:

    ```bash
    download the code from drive
    cd MyCucumberProject
    ```

### Step 3: Build the Project

1. Build the project using Maven:

    ```bash
    mvn clean install
    ```

### Step 4: Run the Tests

1. Run the Cucumber tests:

    ```bash
    mvn test
    ```

## Project Structure

- **src/main/java/com/sqills**
  - `Constants.java`: Defines constants used throughout the project.
  - `CustomerApiHelper.java`: Contains methods for sending HTTP requests to the Customer API.

- **src/test/java/com/sqills/runners**
  - `RunCucumberTest.java`: Configures and runs the Cucumber tests.

- **src/test/java/com/sqills/stepdefinitions**
  - `CustomerSteps.java`: Defines step definitions for the Cucumber tests.

- **src/test/resources/features**
  - `customers.feature`: Contains the feature scenarios for testing the Customer API.

## Feature File: customers.feature

This file contains the feature scenarios for testing the Customer API.

```gherkin
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


