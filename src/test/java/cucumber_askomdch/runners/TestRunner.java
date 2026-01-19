package cucumber_askomdch.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/cucumber_askomdch/features",
        glue = {"cucumber_askomdch.stepdefinitions", "cucumber_askomdch.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@registration",
        monochrome = true
)
public class TestRunner {
}