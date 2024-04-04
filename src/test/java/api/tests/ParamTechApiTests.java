package api.tests;

import api.models.Booking;
import api.models.BookingDates;
import api.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ParamTechApiTests extends BaseTest {

    private static int bookingId = 0;

    //Test Case 1
    @Test(priority = 1, description = "Create Booking Test")
    public void createBookingTest() {

        final var FIRST_NAME = "Sezai";
        final var LAST_NAME = "Celik";
        final var TOTAL_PRICE = 100;
        final var DEPOSIT_PAID = true;
        final var ADDITIONAL_NEEDS = "Breakfast";
        final var CHECK_IN_DATE = "2024-04-15";
        final var CHECK_OUT_DATE = "2024-04-30";

        BookingDates bookingDates = new BookingDates(CHECK_IN_DATE, CHECK_OUT_DATE);

        Booking booking = new Booking(FIRST_NAME, LAST_NAME, TOTAL_PRICE, DEPOSIT_PAID, bookingDates, ADDITIONAL_NEEDS);

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        System.out.printf(bookingResponse + " The Booking response was saved.");

        bookingId = bookingResponse.getBookingid();
        System.out.printf("Booking id: " + bookingId);

        Assert.assertTrue(bookingId > 0);
        Assert.assertEquals(bookingResponse.getBooking().getFirstname(), FIRST_NAME);
        Assert.assertEquals(bookingResponse.getBooking().getLastname(), LAST_NAME);
        Assert.assertEquals(bookingResponse.getBooking().getTotalprice(), TOTAL_PRICE);
        Assert.assertTrue(bookingResponse.getBooking().isDepositpaid());
        Assert.assertEquals(bookingResponse.getBooking().getBookingdates().getCheckin(), CHECK_IN_DATE);
        Assert.assertEquals(bookingResponse.getBooking().getBookingdates().getCheckout(), CHECK_OUT_DATE);
        Assert.assertEquals(bookingResponse.getBooking().getAdditionalneeds(), ADDITIONAL_NEEDS);
    }

    //Test Case 2
    @Test(priority = 2, description = "Delete Booking Test", dependsOnMethods = {"createBookingTest"})
    public void deleteBookingTest() {

        var tokenId = createToken();

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + tokenId)
                .when()
                .delete("/booking/" + bookingId);

        response
                .then()
                .statusCode(201);

        Assert.assertTrue(response.statusLine().contains("Created"));

        Response response2 = given(spec)
                .when()
                .get("/booking/" + bookingId);

        response2
                .then()
                .statusCode(404);

        Assert.assertTrue(response2.statusLine().contains("404 Not Found"));
    }
}
