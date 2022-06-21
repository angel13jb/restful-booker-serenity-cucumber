package booker.restful.cucumber;


import booker.restful.bookinginfo.BookingSteps;
import booker.restful.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

public class bookingcurdsetdef {
    static String firstName = "sweety" + TestUtils.getRandomValue();
    static String lastName = "patel";
    static int price = 30;
    static boolean result = true;
    String token;
    static HashMap<Object,Object> dates;
    static String needs = "breakfast";
    static int bookingId;
    ValidatableResponse response;
    @Steps
    BookingSteps bookInfoTest;

    @When("^user send a post request to creat booking$")
    public void userSendAPostRequestToCreatBooking() {
        dates = new HashMap<>();
        dates.put("checkin", "2018-01-01");
        dates.put("checkout", "2019-01-01");
        response= bookInfoTest.createBooking(firstName,lastName,price,result,dates,needs);
    }

    @And("^user insert name, lastname, price, result, dates, needs$")
    public void userInsertNameLastnamePriceResultDatesNeeds() {
    }

    @Then("^user should be able to creat booking successfully$")
    public void userShouldBeAbleToCreatBookingSuccessfully() {
        response.statusCode(200);
        bookingId=response.extract().path("bookingid");
        System.out.println(bookingId);
    }

    @When("^user send get request to fatch record by single id$")
    public void userSendGetRequestToFatchRecordBySingleId() {
        response=bookInfoTest.getBookingWithBookingId(bookingId);

    }

    @Then("^user should be able to get booking in responsebody$")
    public void userShouldBeAbleToGetBookingInResponsebody() {
        response.statusCode(200);
    }

    @And("^verify booking name$")
    public void verifyBookingName() {
        response.body("firstname",equalTo(firstName));

    }

    @When("^user send Put patch request for updating booking$")
    public void userSendPutPatchRequestForUpdatingBooking() {
        firstName=firstName+"updated001";
        dates = new HashMap<>();
        dates.put("checkin", "2018-01-01");
        dates.put("checkout", "2019-01-01");

        response=bookInfoTest.updateBooking(bookingId,firstName,lastName,price,result,dates,needs,token);

    }

    @And("^user add name in name field$")
    public void userAddNameInNameField() {
    }

    @Then("^user should be able to update booking succefully$")
    public void userShouldBeAbleToUpdateBookingSuccefully() {
        response.statusCode(200);

    }

    @When("^user send delete request for deleting booking$")
    public void userSendDeleteRequestForDeletingBooking() {
        response=bookInfoTest.deleteBookingWithBookingId(bookingId,token);

    }

    @Then("^booking should be succesffuly deleted$")
    public void bookingShouldBeSuccesffulyDeleted() {
        response.statusCode(200);
    }

    @And("^to verify booking is deleted$")
    public void toVerifyBookingIsDeleted() {
        response=bookInfoTest.deleteBookingWithBookingId(bookingId,token);
        response.statusCode(404);

    }
}
