package cucumber.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.contract.wiremock.WireMockConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cucumber")
public class WireMockConfig {
    @Value("${wiremock.port:9999}")
    private int wireMockPort;

    @Value("${wiremock.host:localhost}")
    private String wireMockHost;

    public static String WIREMOCK_ADDRESS = "http://localhost:9999";

    @Bean
    public WireMockConfigurationCustomizer wireMockConfigurationCustomizer() {
        WIREMOCK_ADDRESS = String.format("http://%s:%d", wireMockHost, wireMockPort);
        return config -> config.port(wireMockPort).bindAddress(wireMockHost);
    }
}
