package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:/tmp/auth.properties",
        "classpath:auth.properties"
})
public interface APIConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("http://demowebshop.tricentis.com")
    String getBaseUrl();

    @Key("login")
    String getLogin();

    @Key("password")
    String getPassword();

    @Key("browser")
    @DefaultValue("firefox")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("96.0")
    String getBrowserVersion();

}
