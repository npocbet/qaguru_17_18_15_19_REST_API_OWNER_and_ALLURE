package helpers;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class DriverUtils {
    //public static final Logger LOGGER = LoggerFactory.getLogger(DriverUtils.class);

//
//    public static String getSessionId() {
//        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
//    }

    public static byte[] getScreenshotAsBytes() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static byte[] getPageSourceAsBytes() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    public static String getConsoleLogs() { // todo refactor
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }
}