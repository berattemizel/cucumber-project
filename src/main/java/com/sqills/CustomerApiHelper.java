package com.sqills;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.Map;

public class CustomerApiHelper {
    private String baseUrl = Constants.BASE_URL;

    // Send an HTTP request with the specified method, endpoint, and data
    private Response sendRequest(String method, String endpoint, Map<String, String> data) {
        return given()
                .contentType("application/json")
                .body(data)
                .when()
                .request(method, baseUrl + endpoint);
    }

    // Send a POST request to create a customer
    public Response createCustomer(Map<String, String> customerData) {
        return sendRequest("POST", Constants.CREATE_CUSTOMER_ENDPOINT, customerData);
    }

    // Send a PATCH request to update a customer
    public Response updateCustomer(Map<String, String> customerData) {
        return sendRequest("PATCH", Constants.UPDATE_CUSTOMER_ENDPOINT, customerData);
    }

    // Send a GET request to retrieve a customer by first name
    public Response getCustomer(String firstName) {
        return given()
                .when()
                .get(baseUrl + Constants.GET_CUSTOMER_ENDPOINT + "/" + firstName);
    }

    // Send a DELETE request to remove a customer by first name
    public Response deleteCustomer(String firstName) {
        return given()
                .when()
                .delete(baseUrl + Constants.DELETE_CUSTOMER_ENDPOINT + "/" + firstName);
    }
}
