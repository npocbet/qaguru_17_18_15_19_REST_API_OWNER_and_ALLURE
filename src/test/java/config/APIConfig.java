package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties"
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
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("96.0")
    String getBrowserVersion();

    @Key("remoteDriverUrl")
    String getRemoteDriverUrl();

}
