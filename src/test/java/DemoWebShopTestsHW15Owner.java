import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import config.APIConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTestsHW15Owner {

    @Test
    void addToWishlistTestLocal() {
        APIConfig apiConfig = ConfigFactory.create(APIConfig.class, System.getProperties());

        RestAssured.baseURI = apiConfig.getBaseUrl(); //"http://demowebshop.tricentis.com";
        Configuration.baseUrl = apiConfig.getBaseUrl(); //"http://demowebshop.tricentis.com";

        String cookie = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", apiConfig.getLogin()) // "npocbet@gmail.com"
                .formParam("Password", apiConfig.getPassword()) // "qwerpoiu"
                .when()
                .post("/login")
                .then()
                .extract().cookie("NOPCOMMERCE.AUTH");

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .body("giftcard_1.RecipientName=Some" +
                        "&giftcard_1.RecipientEmail=man%40man.man" +
                        "&giftcard_1.SenderName=Svjato+Krravts" +
                        "&giftcard_1.SenderEmail=npocbet%40gmail.com" +
                        "&giftcard_1.Message=&" +
                        "addtocart_1.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/1/2")
                .then()
                .statusCode(200)
                .body("success", is(true));

        step("Open wishlist", () -> {
            open("http://demowebshop.tricentis.com/");
            getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookie));
            open("http://demowebshop.tricentis.com/wishlist");
        });

        step("check there is and item in the wishlist", () -> {
            $(".page-body").shouldHave(Condition.text("$5 Virtual Gift Card"));
        });
    }

    @Test
    void addToWishlistTestRemote() throws IOException {

        String loginAndPassword = "login=npocbet@gmail.com\npassword=qwerpoiu";
        Path properties = Paths.get("/tmp/auth.properties");

        Files.write(properties, loginAndPassword.getBytes(StandardCharsets.UTF_8));

        APIConfig apiConfig = ConfigFactory.create(APIConfig.class, System.getProperties());

        RestAssured.baseURI = apiConfig.getBaseUrl(); //"http://demowebshop.tricentis.com";
        Configuration.baseUrl = apiConfig.getBaseUrl(); //"http://demowebshop.tricentis.com";

        String cookie = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", apiConfig.getLogin()) // "npocbet@gmail.com"
                .formParam("Password", apiConfig.getPassword()) // "qwerpoiu"
                .when()
                .post("/login")
                .then()
                .extract().cookie("NOPCOMMERCE.AUTH");

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .body("giftcard_1.RecipientName=Some" +
                        "&giftcard_1.RecipientEmail=man%40man.man" +
                        "&giftcard_1.SenderName=Svjato+Krravts" +
                        "&giftcard_1.SenderEmail=npocbet%40gmail.com" +
                        "&giftcard_1.Message=&" +
                        "addtocart_1.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/1/2")
                .then()
                .statusCode(200)
                .body("success", is(true));

        step("Open wishlist", () -> {
            open("http://demowebshop.tricentis.com/");
            getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookie));
            open("http://demowebshop.tricentis.com/wishlist");
        });

        step("check there is and item in the wishlist", () -> {
            $(".page-body").shouldHave(Condition.text("$5 Virtual Gift Card"));
        });

        Files.delete(properties);
    }
}
