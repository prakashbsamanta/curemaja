package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest {
    private RequestSpecification httpRequest;
    private Response response;
    private int responseCode;

    private ResponseBody body;

    private JSONObject requestBody;

    @Given("I hit the url of get products api endpoint")
    public void i_hit_the_url_of_get_products_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url of products in the request")
    public void i_pass_the_url_of_products_in_the_request() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("products");
    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {
        responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, 200);
    }

    @Then("I verify that the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is(String expectedRate) {
        JsonPath jsonPath = response.jsonPath();
        String actualRate = jsonPath.getJsonObject("rating[0].rate").toString();
        Assert.assertEquals(actualRate, expectedRate);
    }

    @Given("I hit the url of post product api endpoint")
    public void iHitTheUrlOfPostProductApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = RestAssured.given();


    }

    @And("I pass the request body of product title {}")
    public void iPassTheRequestBodyOfProductTitleProductTitle(String title) {
        requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("price", 22.8);
        requestBody.put("description", "best Keyboard");
        requestBody.put("image", "i.sd.sa.da");
        requestBody.put("category", "electronic");
        httpRequest.body(requestBody.toJSONString());

        response = httpRequest.post("products");
        body = response.getBody();
        System.out.println(response.getStatusCode());
        System.out.println(body.asString());
    }

    @And("I receive the response body id as {}")
    public void iReceiveTheResponseBodyIdAsId(String id) {
        JsonPath jsonPath = response.jsonPath();
        String actualId = jsonPath.getJsonObject("id").toString();
        Assert.assertEquals(actualId, id);
    }
}
