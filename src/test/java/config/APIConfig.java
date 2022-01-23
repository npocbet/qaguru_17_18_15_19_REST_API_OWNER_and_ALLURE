package config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:auth.properties"
})
public interface APIConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("token")
    String getToken();

}
