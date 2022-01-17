import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.APIConfig;
import helpers.AllureAttachments;
import io.qameta.allure.junit5.AllureJunit5;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;



@ExtendWith({AllureJunit5.class})
public class TestBase {

    @AfterEach
    public void addAttachments() {
        APIConfig apiConfig = ConfigFactory.create(APIConfig.class, System.getProperties());

        Configuration.browser = apiConfig.getBrowser();
        Configuration.browserVersion = apiConfig.getBrowserVersion();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
//        AllureAttachments.attachNetwork(); // todo
//        AllureAttachments.addBrowserConsoleLogs();

        Selenide.closeWebDriver();

    }
}