package com.devlostinthecloud.example.functional.steps;

import com.devlostinthecloud.example.functional.ResponseWrapper;
import com.devlostinthecloud.example.functional.ServiceRestClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.assertj.core.api.Assertions.assertThat;

@Scope(SCOPE_CUCUMBER_GLUE)
public class HealthStepsDefinitions {
    @Autowired
    private ServiceRestClient client;

    private ResponseWrapper response;

    @Given("^the application is running$")
    public void theApplicationIsRunning() {
        // Intentionally empty. @SpringBootTest context loads
    }

    @When("a request is made to {string}")
    public void aRequestIsMadeTo(String relativeURL) {
        response = client.get(relativeURL);
    }

    @Then("the service returns a {int} status response")
    public void theServiceReturnsAStatusResponse(int expectedStatusCode) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(expectedStatusCode));
    }

    @And("the response body is {string}")
    public void theResponseBodyIs(String expectedBody) {
        assertThat(response.getBody()).isEqualTo(expectedBody);
    }
}
