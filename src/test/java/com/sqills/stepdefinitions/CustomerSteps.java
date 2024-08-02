package com.sqills.stepdefinitions;

import com.sqills.Constants;
import com.sqills.CustomerApiHelper;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;

public class CustomerSteps {
    private CustomerApiHelper apiHelper;
    private Response response;
    private static String savedFirstName;


    // Initialize the API helper before each test scenario
    @Before
    public void setUp() {
        apiHelper = new CustomerApiHelper();
    }

    // Define the start point of each test scenario
    @Given("I have a corresponding endpoint")
    public void start_Point() {
    }

    // Validate the response status code
    @Then("the response status code should be {int}")
    public void check_Status_Code(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    // Send a POST request to create a customer
    @When("I send a POST request with the following data")
    public void create_Customer_POST(Map<String, String> data) {
        response = apiHelper.createCustomer(data);
        savedFirstName = data.get("first_name");
    }

    // Validate the response contains the expected customer details
    @Then("the response should contain the customer details")
    public void response_Customer_Details(Map<String, String> data) {
        response.then()
                .body("first_name", equalTo(data.get("first_name")))
                .body("last_name", equalTo(data.get("last_name")));
    }

    // Validate the response contains the duplicate error message
    @Then("the response should contain the duplicate data error message")
    public void response_Duplicate_Customer_Error_Message() {
        response.then()
                .body("code", equalTo(Constants.DUPLICATE_ERROR_MESSAGE));
    }

    // Send a PATCH request to update a customer
    @When("I send a PATCH request with the following data")
    public void update_Customer_PATCH(Map<String, String> data) {
        response = apiHelper.updateCustomer(data);
    }

    // Send a GET request to retrieve a customer
    @When("I send a GET request for the customer")
    public void get_Customer_GET() {
        response = apiHelper.getCustomer(savedFirstName);
    }

    // Send a DELETE request to remove a customer
    @When("I send a DELETE request for the customer")
    public void delete_Customer_DEL() {
        response = apiHelper.deleteCustomer(savedFirstName);
    }


    // // Validate the response contains the duplicate error message
    // @Then("the response should contain the duplicate data error message")
    // public void response_Duplicate_Customer_Error_Message() {
    //     // assertEquals(Constants.DUPLICATE_ERROR_MESSAGE, response.jsonPath().getString("code"));
    //     response.then()
    //             .body("code", equalTo(Constants.DUPLICATE_ERROR_MESSAGE));
    // }

    // Validate the response contains the expected customer details
    // @Then("the response should contain the customer details")
    // public void response_Customer_Details(Map<String, String> data) {
    //     String firstName = data.get("first_name");
    //     String lastName = data.get("last_name");
    //     assertEquals(firstName, response.jsonPath().getString("first_name"));
    //     assertEquals(lastName, response.jsonPath().getString("last_name"));
    // }

    // // Send a GET request to retrieve a customer
    // @When("I send a GET request for the customer {string}")
    // public void get_Customer_GET(String firstName) {
    //     response = apiHelper.getCustomer(firstName);
    // }

    // // Send a DELETE request to remove a customer
    // @When("I send a DELETE request for the customer {string}")
    // public void delete_Customer_DEL(String firstName) {
    //     response = apiHelper.deleteCustomer(firstName);
    // }

    //New Methods
    // Validate the response status code and customer details
    @Then("the response contains status code {int} and customer details")
    public void check_Status_Code_and_Customer_Details(int statusCode, Map<String, String> expectedData) {
        response.then()
                .statusCode(statusCode)
                .body("first_name", equalTo(expectedData.get("first_name")))
                .body("last_name", equalTo(expectedData.get("last_name")));
    }
}
