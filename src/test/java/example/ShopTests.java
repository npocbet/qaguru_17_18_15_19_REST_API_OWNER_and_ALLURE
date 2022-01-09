package example;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class ShopTests {
    private String cookie;
    private static final String MINIMAL_CONTENT_PATH = "/Themes/DefaultClean/Content/styles.css";

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        Configuration.baseUrl = "http://demowebshop.tricentis.com";
    }

    @BeforeEach
    void login() {
        cookie = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", "besfian@mail.ru")
                .formParam("Password", "dpride0930237p")
                .when()
                .post("/login")
                .then()
                .extract().cookie("NOPCOMMERCE.AUTH");
    }

    @Test
    void shouldAddAddresses() {
        given().log().all()
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .contentType("application/x-www-form-urlencoded")
                .body("Address.Id=0&Address.FirstName=test&Address.LastName=test&Address.Email=test%40maul.ru&Address.Company=&Address.CountryId=2&Address.StateProvinceId=63&Address.City=test&Address.Address1=test&Address.Address2=&Address.ZipPostalCode=123456&Address.PhoneNumber=284118&Address.FaxNumber=")
                .when()
                .post("/customer/addressadd")
                .then().log().all()
                .statusCode(302);

        open(MINIMAL_CONTENT_PATH);
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookie));

        open("/customer/addresses");
        $(".address-list").$(".name").shouldHave(text("test test"));
    }
}
