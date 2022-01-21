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


        Configuration.browser = apiConfig.getBrowser();
        Configuration.browserVersion = apiConfig.getBrowserVersion();
        RestAssured.baseURI = apiConfig.getBaseUrl(); //"http://demowebshop.tricentis.com";
        Configuration.baseUrl = apiConfig.getBaseUrl(); //"http://demowebshop.tricentis.com";

       // open("/content/images/thumbs/0000015_25-virtual-gift-card_300.jpeg");
    }


    @AfterEach
    public void addAttachments() {

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();

        Selenide.closeWebDriver();

    }
}