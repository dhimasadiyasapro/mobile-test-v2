package hooks;

import base.AppiumDriverSetup;
import base.AppiumDriverUtil;
import base.BaseStep;
import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;

import static base.AppiumDriverUtil.driverType;

public class AppiumHooks {

    private static Logger logger = BaseStep.getLogger();

    @Before(order = 1)
    public void initialize(Scenario scenario) throws MalformedURLException {
        if (AppiumDriverUtil.getDriver() != null) {
            try {
                AppiumDriverUtil.getDriver().getPageSource();
            } catch (Exception wde) {
//                logger.info("Some error in initialize: " + wde.getMessage());
//                wde.printStackTrace();
                AppiumDriverUtil.setDriver(null);
            }
        }

        if (AppiumDriverUtil.getDriver() == null) {
            logger.info("Open app: " + driverType);
            AppiumDriverUtil.setDriver(AppiumDriverSetup.setupDriver(driverType));
        }

        logger.info("Scenario '" + scenario.getName() + "' STARTED");
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.getStatus().equals("failed") || scenario.getStatus().equals("undefined")) {
            byte[] screen = AppiumDriverUtil.getScreenshot();
            scenario.embed(screen, "image/png");
            String screenshotName = AppiumDriverUtil.writeScreenshotToFileAbsolut(screen, scenario.getName().replaceAll(" ", "_"), null);
            logger.error("Scenario '" + scenario.getName() + "' FAILED, Screen shot: " + screenshotName);
            try {
                Reporter.addScreenCaptureFromPath(screenshotName);
            } catch (IOException e) {
            }
        } else {
            logger.info("Scenario '" + scenario.getName() + "' PASSED");
        }
        try {
            AppiumDriverUtil.getDriver().quit();
        } catch (WebDriverException we) {
            logger.info("Ignoring WebDriverException");
        }
    }
}
