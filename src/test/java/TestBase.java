import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.APIConfig;
import helpers.AllureAttachments;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith({AllureJunit5.class})
public class TestBase {
    public static APIConfig apiConfig = ConfigFactory.create(APIConfig.class, System.getProperties());

    @BeforeAll
    public static void setUp() {

       RestAssured.baseURI = apiConfig.getBaseUrl();

    }
}