import com.codeborne.selenide.Condition;
import filters.CustomLogFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTestsHW19Allure extends TestBase{

    public String cookie;

    @Test
    @Tag("hw19")
    void addToWishlistTestLocalWithListener() {

        String cookie = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", apiConfig.getLogin()) // "npocbet@gmail.com"
                .formParam("Password", apiConfig.getPassword()) // "qwerpoiu"
                .when()
                .post("/login")
                .then()
                .extract().cookie("NOPCOMMERCE.AUTH");

        given()
                .filter(new AllureRestAssured())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .body("giftcard_1.RecipientName=Some" +
                        "&giftcard_1.RecipientEmail=man%40man.man" +
                        "&giftcard_1.SenderName=Svjato+Krravts" +
                        "&giftcard_1.SenderEmail=npocbet%40gmail.com" +
                        "&giftcard_1.Message=&" +
                        "addtocart_1.EnteredQuantity=1")
                .when()
                .log().uri()
                .log().headers()
                .post("/addproducttocart/details/1/2")
                .then()
                .log().body()
                .statusCode(200)
                .body("success", is(true));

        step("Open wishlist", () -> {
            open("/");
            getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookie));
            open("/wishlist");
        });

        step("check there is and item in the wishlist", () -> {
            $(".page-body").shouldHave(Condition.text("$5 Virtual Gift Card"));
        });
    }

    @Test
    @Tag("hw19")
    void addToWishlistTestLocalWithTemplates() {

        step("Get cookie", () -> {
            cookie = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", apiConfig.getLogin()) // "npocbet@gmail.com"
                .formParam("Password", apiConfig.getPassword()) // "qwerpoiu"
                .when()
                .post("/login")
                .then()
                .extract().cookie("NOPCOMMERCE.AUTH");
        });

        step("Send API request", () -> {
            given()
                .filter(new CustomLogFilter().withCustomTemplates())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .body("giftcard_1.RecipientName=Some" +
                        "&giftcard_1.RecipientEmail=man%40man.man" +
                        "&giftcard_1.SenderName=Svjato+Krravts" +
                        "&giftcard_1.SenderEmail=npocbet%40gmail.com" +
                        "&giftcard_1.Message=&" +
                        "addtocart_1.EnteredQuantity=1")
                .when()
                .log().uri()
                .log().headers()
                .post("/addproducttocart/details/1/2")
                .then()
                .log().body()
                .statusCode(200)
                .body("success", is(true));
        });

        step("Open wishlist", () -> {
            open("/");
            getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookie));
            open("/wishlist");
        });

        step("check there is and item in the wishlist", () -> {
            $(".page-body").shouldHave(Condition.text("$5 Virtual Gift Card"));
        });
    }

}
