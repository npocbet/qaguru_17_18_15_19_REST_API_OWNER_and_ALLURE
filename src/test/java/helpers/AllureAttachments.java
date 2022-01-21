package helpers;

import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AllureAttachments {
    public static final Logger LOGGER = LoggerFactory.getLogger(AllureAttachments.class);

//    @Attachment(value = "{attachName}", type = "text/plain")
//    private static String addMessage(String attachName, String text) {
//        return text;
//    }

//    public static void addBrowserConsoleLogs() {
//        addMessage("Browser console logs", DriverUtils.getConsoleLogs());
//    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] addScreenshotAs(String attachName) {
        return DriverUtils.getScreenshotAsBytes();
    }

    @Attachment(value = "Page source", type = "text/html")
    public static byte[] addPageSource() {
        return DriverUtils.getPageSourceAsBytes();
    }


}