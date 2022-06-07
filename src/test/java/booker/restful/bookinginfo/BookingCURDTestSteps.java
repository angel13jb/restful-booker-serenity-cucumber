package booker.restful.bookinginfo;

import booker.restful.testbase.TestBase;
import booker.restful.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
@RunWith(SerenityRunner.class)
public class BookingCURDTestSteps extends TestBase{

        static String username = "admin";
        static String password = "password123";
        static String firstname = "sweety" + TestUtils.getRandomValue();
        static String lastname = "sugar" + TestUtils.getRandomValue();
        static int totalprice = 100;
        static Boolean depositpaid = true;
        static String additionalneeds = "Breakfast";
        static int bookingId;
        static String token = "88e559dde8838cb";

        @Steps
        BookingSteps bookingSteps;

        @Title("This is for authentication")
        @Test
        public void test001() {
            bookingSteps.getAuthentication(username, password).log().all().statusCode(200);
        }

        @Title("This will create a new Booking")
        @Test
        public void test002() {
            HashMap<Object, Object> bookingsDatesData = new HashMap<>();
            bookingsDatesData.put("checkin", "2018-01-01");
            bookingsDatesData.put("checkout", "2019-01-01");
            ValidatableResponse response = bookingSteps.createBooking(firstname, lastname, totalprice, depositpaid, bookingsDatesData, additionalneeds);
            response.log().all().statusCode(200);
            bookingId = response.extract().path("bookingid");
            System.out.println(bookingId);
        }


        @Title("Update the booking")
        @Test
        public void test004() {
            bookingId = 2044;
            firstname = "Tom";
            lastname = "Cat";
            additionalneeds = "Vegan";
            HashMap<Object, Object> bookingsDatesData = new HashMap<>();
            bookingsDatesData.put("checkin", "2018-01-01");
            bookingsDatesData.put("checkout", "2019-01-01");
            ValidatableResponse response = bookingSteps.updateBooking(bookingId, firstname, lastname, totalprice, depositpaid, bookingsDatesData, additionalneeds, token);
            response.log().all().statusCode(200);
            response.body("firstname", equalTo(firstname), "lastname", equalTo(lastname),
                    "additionalneeds", equalTo(additionalneeds));
        }

        @Title("Delete the booking")
        @Test
        public void test005() {
            bookingId = 755;
            ValidatableResponse response = bookingSteps.deleteBookingWithBookingId(bookingId, token);
            response.log().all().statusCode(201);
            bookingSteps.getBookingWithBookingId(bookingId).log().all().statusCode(404);
        }

    }