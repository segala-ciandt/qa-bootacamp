package cucumber.config;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        glue = { "classpath:cucumber.step.definitions", "classpath:cucumber.step.hooks" },
        strict = true)

public class CucumberIntegrationTest {
}
