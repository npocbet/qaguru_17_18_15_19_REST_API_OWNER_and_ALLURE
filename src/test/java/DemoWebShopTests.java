import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DemoWebShopTests {


    @Test
    void addToWishlistTest() {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("addtocart_14.EnteredQuantity=1")
                        .cookie("Nop.customer=02a67bbd-9889-4cfb-8956-ff79ee99c061;")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/14/2")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .extract()
                        .response();
        System.out.println(response.asString());
    }

    @Disabled("Doesn't work")
    @Test
    void sendEmailTest() {

        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .body("FriendEmail=npocbet%40gmail.com" +
                                "&YourEmailAddress=svjato%40mail.ru" +
                                "&PersonalMessage=hey%21+what+a+nice+product" +
                                "&send-email=Send+email")
                        .cookie("Nop.customer=9a870205-2f39-4c60-b094-d8f9d23627af;")
                        .cookie("NOPCOMMERCE.AUTH=380F22C57865C85045F9DFA40168B3B04488E242988C3B3748FB4C1DAD2FA675BE0" +
                                "C98E6370EFD1D5E9B61F85DEF832F3E888FB153AC2C3C894A282346FFDFCE38BEC8B381D097CF746F1AF" +
                                "24F686F9651716AC7891B10AEBC4D11AE94B31DC058E59A0489632BD93B1DB89F0895384958EAFC1A6A19" +
                                "136D4BC92C9E4645BB308BDBF63679FEDFA3EBDFD9A4C1B3A47B")
                        .when()
                        .post("http://demowebshop.tricentis.com/productemailafriend/17")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        System.out.println(response.asString());
    }


    @Disabled("Doesnt work")
    @Test
    void addReviewTest() {
        String response =
                given()
                        .contentType("text/html; charset=utf-8")
                        .body("AddProductReview.Title=Svjato+review&" +
                                "AddProductReview.ReviewText=test%2C+svjato+veryveryverygood&" +
                                "AddProductReview.Rating=5&" +
                                "add-review=Submit+review")
                        .cookie("Nop.customer=9a870205-2f39-4c60-b094-d8f9d23627af;")
                        .cookie("__RequestVerificationToken=OTjg5XVxYzOGWXI6nbI0874K1h7WZTngIOiVJX0NSMgvCW8DoK-6w7xyMchBbJSLwG7u1RV3BOkzkT45DvpfMUUxTkq7-qiQIbPFS1UznUQ1")
                        .when()
                        .post("http://demowebshop.tricentis.com/productreviews/2")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response().asString();
        assertThat(response).contains("veryveryverygood");
    }


    @Disabled("qaguru example")
    @Test
    void addToCartTest() {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("product_attribute_16_5_4=14&product_attribute_16_6_5=15&" +
                                "product_attribute_16_3_6=19&product_attribute_16_4_7=44&" +
                                "product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/16/1")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                        .body("updatetopcartsectionhtml", is("(1)"))
                        .extract().response();

        System.out.println("Response: " + response.path("updatetopcartsectionhtml"));
    }


    @Disabled("qaguru example")
    @Test
    void addToCartWithCookieTest() {
        // todo get exist cart size

        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("product_attribute_16_5_4=14&product_attribute_16_6_5=15&" +
                                "product_attribute_16_3_6=19&product_attribute_16_4_7=44&" +
                                "product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1")
                        .cookie("Nop.customer=876119dc-dd7f-4867-8bd1-a2502d97224c;")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/16/1")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
//                        .body("updatetopcartsectionhtml", is("(15)"))
                        .extract().response();

        System.out.println("Response: " + response.path("updatetopcartsectionhtml"));
    }
}
