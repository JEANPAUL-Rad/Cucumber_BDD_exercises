package cucumber_askomdch.hooks;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import cucumber_askomdch.factory.DriverFactory;
import cucumber_askomdch.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class Hooks {

    @Before
    public void setUp() {
        String browser = ConfigReader.get("browser");
        DriverFactory.initializeDriver(browser);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()){
            byte[] screenshoot= ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            //cuccumber report
            scenario.attach(screenshoot,"image/png",
                    "Failed cucumber screenshoot");

            //Allure report
            Allure.addAttachment("Failed screenshoot",
                    new ByteArrayInputStream(screenshoot));

            //Extent report
            String base64screenshoot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BASE64);

            ExtentCucumberAdapter.getCurrentStep().fail(
                    "Scenario",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64screenshoot)
                            .build()
            );
        }
        DriverFactory.quitDriver();
    }
}