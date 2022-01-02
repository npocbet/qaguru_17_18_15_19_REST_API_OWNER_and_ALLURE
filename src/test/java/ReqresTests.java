import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class ReqresTests {
    @BeforeAll
    static void setUP(){
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Description("Total users should be equal 12")
    @Test
    void totalUsers(){
        Integer response = given()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .path("total");
        assertThat(response).isEqualTo(12);
    }

    @Description("Single user not found")
    @Test
    void singleUserNotFound(){
        given()
                .when()
                .get("/api/users/23")
                .then()
                .statusCode(404);
    }

    @Description("Trying to create a user, date of creation should be current date")
    @Test
    void createAUser(){
    String newUserData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        Date date = new Date();
        String response = given()
                .contentType(ContentType.JSON)
                .body(newUserData)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .path("createdAt");
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

        assertThat(response).contains(formatForDateNow.format(date));

    }

    @Description("Trying to register a new user, should be response 200")
    @Test
    void registerUser(){
    String newUserData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";
        given()
                .contentType(ContentType.JSON)
                .body(newUserData)
                .when()
                .post("/api/register")
                .then()
                .statusCode(200);

    }

    @Description("Trying to get request with delay")
    @Test
    void delayedResponse(){
        given()
                .when()
                .get("/api/users?delay=3")
                .then()
                .statusCode(200)
                .body("total_pages", is(2));
    }
}
