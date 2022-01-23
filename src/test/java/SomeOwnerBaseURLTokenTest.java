import config.APIConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeOwnerBaseURLTokenTest {

    @Test
    void simpleOwnerTest() {
        APIConfig apiConfig = ConfigFactory.create(APIConfig.class, System.getProperties());

        assertEquals(apiConfig.getBaseUrl(), "somesite.com");
        assertEquals(apiConfig.getToken(), "oasdji34ujij2oij3oijeoijroi2jjiajsdijij2ijisjd");
    }

}
