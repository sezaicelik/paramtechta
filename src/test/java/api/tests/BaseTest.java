package api.tests;

import api.models.Auth;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTest {

    RequestSpecification spec;

    public static final String API_URL = "https://restful-booker.herokuapp.com";

    @BeforeMethod
    public void setup() {

        spec = new RequestSpecBuilder()
                .setBaseUri(API_URL)
                .addFilter(new AllureRestAssured())
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();
    }

    protected String createToken() {

        Auth authBody = new Auth("admin", "password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");

        return response.jsonPath().getJsonObject("token");
    }
}
