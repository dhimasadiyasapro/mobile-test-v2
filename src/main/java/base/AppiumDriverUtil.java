package base;

import constants.PropertyConfigs;
import helpers.ConfigReader;
import helpers.DateHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppiumDriverUtil {

    private static final ThreadLocal<WebDriver> drivers = ThreadLocal.withInitial(() -> null);
    private static final int implicitWait = Integer.parseInt(ConfigReader.getInstance().getValue(PropertyConfigs.APPIUM_IMPLICIT_WAIT));
    public static final String driverType = ConfigReader.getInstance().getValue(PropertyConfigs.PLATFORM_NAME);

    public static WebDriver getDriver() {
        switch (driverType) {
            case "Android":
                return (AndroidDriver) drivers.get();
            case "iOS":
                return (IOSDriver) drivers.get();
            default:
                return drivers.get();
        }
    }

    public static void setDriver(WebDriver driver) {
        drivers.set(driver);
    }

    public static void sleep(Integer miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
        }
    }

    public static MobileElement getElement(By locator) {
        return getDriver().findElement(locator);
    }

    public static MobileElement getElement(MobileElement parent, By locator) {
        return parent.findElement(locator);
    }

    public static List<MobileElement> getElements(By locator) {
        return getDriver().findElements(locator);
    }

    public static List<MobileElement> getElements(MobileElement parent, By locator) {
        return parent.findElements(locator);
    }

    public static List<MobileElement> getElementsOrEmpty(By locator) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<MobileElement> temp = getDriver().findElements(locator);
        getDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        return temp;
    }

    public static List<MobileElement> getElementsOrEmpty(WebElement parent, By locator) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<MobileElement> temp = parent.findElements(locator);
        getDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        return temp;
    }

    public static String writeScreenshotToFile(byte[] screen, String nameTemplate, String path) {
        try {
            if (path == null) {
                path = "./target/screenshots/" + nameTemplate + "_" + DateHelper.getTodaysDateTime() + ".png";
            }
            FileUtils.writeByteArrayToFile(new File(path), screen);

        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }

        return path;
    }

    public static String writeScreenshotToFileAbsolut(byte[] screen, String nameTemplate, String path) {
        String filePath = writeScreenshotToFile(screen, nameTemplate, path);
        return new File(filePath).getAbsolutePath();
    }

    public static byte[] getScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
