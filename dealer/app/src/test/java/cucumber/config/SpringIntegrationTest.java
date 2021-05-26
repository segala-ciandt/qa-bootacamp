package cucumber.config;

import com.ciandt.dealer.App;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.config.utils.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles({
        "cucumber"
})
@AutoConfigureWireMock
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {
        App.class,
        TestContext.class,
        WireMockConfig.class,
        RestUtils.class},
        initializers = ConfigDataApplicationContextInitializer.class)
public class SpringIntegrationTest {
    @Autowired
    public TestContext testContext;

    @Autowired
    public ObjectMapper objectMapper;
}
